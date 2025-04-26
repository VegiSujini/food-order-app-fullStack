package com.example.food_order_app.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.food_order_app.model.FoodOrder;
import com.example.food_order_app.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService service;

    public OrderController(OrderService service) {
        this.service = service;
    }

    // 1. Place a new order
    @PostMapping
    public ResponseEntity<FoodOrder> placeOrder(@RequestBody FoodOrder order) {
        FoodOrder savedOrder = service.placeOrder(order);
        return ResponseEntity.ok(savedOrder);
    }

    // 2. Get all orders
    @GetMapping
    public ResponseEntity<List<FoodOrder>> getAllOrders() {
        List<FoodOrder> orders = service.getAllOrders();
        return ResponseEntity.ok(orders);
    }

    // 3. Get specific order by ID
    @GetMapping("/{id}")
    public ResponseEntity<FoodOrder> getOrder(@PathVariable Long id) {
        return service.getOrderById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 4. Update status of the order
    @PutMapping("/{id}/status")
    public ResponseEntity<FoodOrder> updateStatus(@PathVariable Long id, @RequestParam String status) {
        FoodOrder updatedOrder = service.updateOrderStatus(id, status);
        return ResponseEntity.ok(updatedOrder);
    }

    // 5. Upload image for an order
    @PostMapping("/{orderId}/upload-image")
    public ResponseEntity<String> uploadImage(
            @PathVariable Long orderId,
            @RequestParam("file") MultipartFile file) {

        try {
            String message = service.uploadImage(orderId, file);
            return ResponseEntity.ok(message);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to upload image");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 6. Download image for an order
    @GetMapping("/{orderId}/download-image")
    public ResponseEntity<Resource> downloadImage(@PathVariable Long orderId) {
        try {
            byte[] imageData = service.downloadImage(orderId);
            ByteArrayResource resource = new ByteArrayResource(imageData);

            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"image.jpg\"")
                    .body(resource);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}