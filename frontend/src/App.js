import OrderForm from './components/OrderForm';
import OrderList from './components/OrderList';
import React, { useState } from 'react';
import './App.css';

function App() {
  const [refresh, setRefresh] = useState(false);

  const handleOrderPlaced = () => {
      setRefresh(prev => !prev);
  };

  return (
      <div>
          <h1>Food Order App</h1>
          <OrderForm onOrderPlaced={handleOrderPlaced} />
          <OrderList key={refresh} />
      </div>
  );
}

export default App;
