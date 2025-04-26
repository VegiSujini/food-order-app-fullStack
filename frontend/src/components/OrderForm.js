import { useState } from 'react';
import { placeOrder } from '../api';

function OrderForm({ onOrderPlaced }) {
    const [customerName, setCustomerName] = useState('');
    const [foodItem, setFoodItem] = useState('');

    const handleSubmit = async (e) => {
        e.preventDefault();
        await placeOrder({ customerName, foodItem });
        setCustomerName('');
        setFoodItem('');
        onOrderPlaced();
    };

    return (
        <form onSubmit={handleSubmit}>
            <h2>Place Order</h2>
            <input
                type="text"
                placeholder="Customer Name"
                value={customerName}
                onChange={(e) => setCustomerName(e.target.value)}
                required
            /><br/><br/>
            <input
                type="text"
                placeholder="Food Item"
                value={foodItem}
                onChange={(e) => setFoodItem(e.target.value)}
                required
            /><br/><br/>
            <button type="submit">Place Order</button>
        </form>
    );
}

export default OrderForm;
