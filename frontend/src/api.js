import axios from 'axios';

const API_BASE = 'http://localhost:8080/api/orders';

export const placeOrder = (order) => axios.post(API_BASE, order);
export const getOrders = () => axios.get(API_BASE);
export const updateStatus = (id, status) => 
    axios.put(`${API_BASE}/${id}/status`, null, { params: { status } });

export const uploadImage = (id, file) => {
    const formData = new FormData();
    formData.append('file', file);
    return axios.post(`${API_BASE}/${id}/upload-image`, formData, {
        headers: {
            'Content-Type': 'multipart/form-data'
        }
    });
};

export const downloadImage = (id) => 
    axios.get(`${API_BASE}/${id}/download-image`, { responseType: 'blob' });
