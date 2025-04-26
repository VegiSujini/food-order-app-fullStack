package com.example.food_order_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.food_order_app.model.FoodOrder;

public interface FoodOrderRepository extends JpaRepository<FoodOrder, Long>{

}
