import { uploadImage } from '../api';

const OrderList = () => {
  const handleUpload = async (id, event) => {
    event.preventDefault(); // 🔥 prevent form submit
    const fileInput = document.getElementById(`fileInput-${id}`);
    if (!fileInput.files.length) {
      alert('Please select a file first!');
      return;
    }

    const file = fileInput.files[0];
    try {
      await uploadImage(id, file);
      alert('Image uploaded successfully!');
    } catch (error) {
      console.error('Upload failed:', error);
      alert('Failed to upload image.');
    }
  };

  return (
    <div>
      {orders.map(order => (
        <div key={order.id}>
          <input type="file" id={`fileInput-${order.id}`} />
          <button onClick={(e) => handleUpload(order.id, e)}>Upload</button>
        </div>
      ))}
    </div>
  );
};
