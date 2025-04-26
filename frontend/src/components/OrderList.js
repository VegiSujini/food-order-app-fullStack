import { useEffect, useState } from 'react';
import { getOrders, updateStatus, downloadImage, uploadImage } from '../api';

function OrderList() {
    const [orders, setOrders] = useState([]);
    const [status, setStatus] = useState({});
    const [files, setFiles] = useState({});

    const fetchOrders = async () => {
        const res = await getOrders();
        setOrders(res.data);
    };

    const handleStatusChange = (id, value) => {
        setStatus(prev => ({ ...prev, [id]: value }));
    };

    const handleUpdateStatus = async (id) => {
        if (status[id]) {
            await updateStatus(id, status[id]);
            fetchOrders();
        }
    };

    const handleFileChange = (id, file) => {
        setFiles(prev => ({ ...prev, [id]: file }));
    };

    const handleUploadImage = async (id) => {
        const file = files[id];
        if (!file) {
            alert('Please select a file first!');
            return;
        }
        try {
            await uploadImage(id, file);
            alert('Image uploaded successfully!');
            fetchOrders();
        } catch (error) {
            console.error('Upload failed:', error);
            alert('Image upload failed.');
        }
    };

    const handleDownloadImage = async (id) => {
        try {
            const res = await downloadImage(id);
            const url = window.URL.createObjectURL(new Blob([res.data]));
            const link = document.createElement('a');
            link.href = url;
            link.setAttribute('download', `food_${id}.jpg`);
            document.body.appendChild(link);
            link.click();
            link.remove();
        } catch (error) {
            console.error('Download failed:', error);
            alert('Image download failed.');
        }
    };
    

    useEffect(() => {
        fetchOrders();
    }, []);

    return (
        <div>
            <h2>Orders List</h2>
            <table border="1" style={{ width: '100%' }}>
                <thead>
                    <tr>
                        <th>ID</th>
                        <th>Customer</th>
                        <th>Food Item</th>
                        <th>Status</th>
                        <th>Update Status</th>
                        <th>Upload Image</th>
                        <th>Download Image</th>
                    </tr>
                </thead>
                <tbody>
                    {orders.map(order => (
                        <tr key={order.id}>
                            <td>{order.id}</td>
                            <td>{order.customerName}</td>
                            <td>{order.foodItem}</td>
                            <td>{order.status}</td>
                            <td>
                                <input
                                    type="text"
                                    placeholder="New Status"
                                    value={status[order.id] || ''}
                                    onChange={(e) => handleStatusChange(order.id, e.target.value)}
                                />
                                <button onClick={() => handleUpdateStatus(order.id)}>Update</button>
                            </td>
                            <td>
                                <input
                                    type="file"
                                    onChange={(e) => handleFileChange(order.id, e.target.files[0])}
                                />
                                <button onClick={() => handleUploadImage(order.id)}>Upload</button>
                            </td>
                            <td>
                                <button onClick={() => handleDownloadImage(order.id)}>Download</button>
                            </td>
                        </tr>
                    ))}
                </tbody>
            </table>
        </div>
    );
}

export default OrderList;
