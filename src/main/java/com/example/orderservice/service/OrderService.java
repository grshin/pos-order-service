package com.example.orderservice.service;

import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.orderservice.dto.InventoryItemResponse;
import com.example.orderservice.model.Order;

@Service
public class OrderService {

    private final Map<Long, Order> orders = new HashMap<>();
    private long nextId = 1;

    private final RestTemplate restTemplate;

    @Value("${inventory.url}")
    private String inventoryUrl;

    public OrderService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    public Order getOrderById(Long id) {
        return orders.get(id);
    }

    public Order createOrder(Order order) {
        // inventory-service에서 재고 확인
        String url = inventoryUrl + "/api/inventory/" + order.getItemName();
        InventoryItemResponse inventory =
                restTemplate.getForObject(url, InventoryItemResponse.class);

        if (inventory == null || inventory.getQuantity() < order.getQuantity()) {
            throw new RuntimeException("재고 부족 또는 품목 없음: " + order.getItemName());
        }

        // 주문 저장
        order.setId(nextId++);
        orders.put(order.getId(), order);
        return order;
    }

    public boolean deleteOrder(Long id) {
        return orders.remove(id) != null;
    }
}
