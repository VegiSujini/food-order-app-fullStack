package com.example.food_order_app;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Optional;
import com.example.food_order_app.model.FoodOrder;
import com.example.food_order_app.repository.FoodOrderRepository;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.food_order_app.service.OrderService;
class OrderServiceTest {

    @Test
    void testPlaceOrder() {
        FoodOrderRepository repo = mock(FoodOrderRepository.class);
        OrderService service = new OrderService(repo);

        FoodOrder order = new FoodOrder();
        order.setCustomerName("John");
        order.setFoodItem("Burger");

        when(repo.save(any(FoodOrder.class))).thenReturn(order);

        FoodOrder savedOrder = service.placeOrder(order);
        assertEquals("John", savedOrder.getCustomerName());
    }

    @Test
    void testGetOrderById() {
        FoodOrderRepository repo = mock(FoodOrderRepository.class);
        OrderService service = new OrderService(repo);

        FoodOrder order = new FoodOrder();
        order.setId(1L);

        when(repo.findById(1L)).thenReturn(Optional.of(order));

        FoodOrder found = service.getOrderById(1L).orElseThrow();
        assertEquals(1L, found.getId());
    }
}