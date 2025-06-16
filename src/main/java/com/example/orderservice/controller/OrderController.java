package com.example.orderservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.common.dto.ApiResponse;
import com.example.orderservice.client.InventoryClient;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final InventoryClient inventoryClient; // 🔸 필드 선언

    public OrderController(OrderService orderService, InventoryClient inventoryClient) {
        this.orderService = orderService;
        this.inventoryClient = inventoryClient; // 🔸 생성자 주입
    }

    @GetMapping("/test")
    public ApiResponse<String> test() {
        return new ApiResponse<>("Order service is running!", "order-ok");
    }

    @GetMapping("/check-inventory")
    public ApiResponse<String> checkInventory() {
        String response = inventoryClient.checkInventory();
        return new ApiResponse<>(response, "inventory-response");
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrder(@PathVariable Long id) {
        return orderService.getOrderById(id);
    }

    @PostMapping
    public Order createOrder(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}
