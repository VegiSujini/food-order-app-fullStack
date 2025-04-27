package com.example.food_order_app.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.example.food_order_app.model.FoodOrder;
import com.example.food_order_app.repository.FoodOrderRepository;

@Service
public class OrderService {

    private final FoodOrderRepository repository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public OrderService(FoodOrderRepository repository) {
        this.repository = repository;
    }

    public FoodOrder placeOrder(FoodOrder order) {
        order.setStatus("Placed");
        return repository.save(order);
    }

    public List<FoodOrder> getAllOrders() {
        return repository.findAll();
    }

    public Optional<FoodOrder> getOrderById(Long id) {
        return repository.findById(id);
    }

    public FoodOrder updateOrderStatus(Long id, String status) {
        FoodOrder order = repository.findById(id).orElseThrow();
        order.setStatus(status);
        return repository.save(order);
    }

    public String uploadImage(Long orderId, MultipartFile file) throws IOException {
        FoodOrder order = repository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found with id: " + orderId));

        // Upload path
        Path uploadPath = Paths.get(uploadDir);

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        if (fileName.contains("..")) {
            throw new IOException("Invalid file name: " + fileName);
        }

        Path filePath = uploadPath.resolve(fileName);
        file.transferTo(filePath);

        // Save only file name
        order.setImagePath(fileName);
        repository.save(order);

        return "File uploaded successfully: " + fileName;
    }

    public byte[] downloadImage(Long id) throws IOException {
        FoodOrder order = repository.findById(id).orElseThrow();
        // Combine upload directory and filename
        Path filePath = Paths.get(uploadDir).resolve(order.getImagePath());

        if (!Files.exists(filePath)) {
            throw new IOException("Image not found");
        }

        return Files.readAllBytes(filePath);
    }

}