package com.example.orderservice.controller;

import java.util.List;

import org.springframework.web.bind.annotation.*;

import com.example.common.dto.ApiResponse;
import com.example.orderservice.client.InventoryClient;
import com.example.orderservice.model.Order;
import com.example.orderservice.service.OrderService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "주문 API", description = "주문 등록 및 조회 기능")
@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;
    private final InventoryClient inventoryClient; // 🔸 필드 선언

    public OrderController(OrderService orderService, InventoryClient inventoryClient) {
        this.orderService = orderService;
        this.inventoryClient = inventoryClient; // 🔸 생성자 주입
    }

    @Operation(summary = "테스트 API", description = "Order 서비스가 정상적으로 작동하는지 확인합니다.")
    @GetMapping("/test")
    public ApiResponse<String> test() {
        return new ApiResponse<>("Order service is running!", "order-ok");
    }

    @Operation(summary = "인벤토리 상태 확인", description = "Inventory 서비스와의 연동을 통해 현재 재고 상태를 확인합니다.")
    @GetMapping("/check-inventory")
    public ApiResponse<String> checkInventory() {
        String response = inventoryClient.checkInventory();
        return new ApiResponse<>(response, "inventory-response");
    }

    @Operation(summary = "주문 목록 조회", description = "등록된 모든 주문을 반환합니다.")
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }
    
    @Operation(summary = "주문 상세 조회", description = "주문 ID를 이용해 특정 주문 정보를 조회합니다.")
    @GetMapping("/{id}")
    public Order getOrder(
        @Parameter(description = "주문 ID", example = "1")
        @PathVariable Long id
    ) {
        return orderService.getOrderById(id);
    }

    @Operation(summary = "주문 생성", description = "신규 주문을 등록합니다.")
    @PostMapping
    public Order createOrder(
        @Parameter(description = "주문 정보 JSON", required = true)
        @RequestBody Order order
    ) {
        return orderService.createOrder(order);
    }

    @Operation(summary = "주문 삭제", description = "주문 ID를 이용해 특정 주문을 삭제합니다.")
    @DeleteMapping("/{id}")
    public void deleteOrder(
        @Parameter(description = "삭제할 주문 ID", example = "1")
        @PathVariable Long id
    ) {
        orderService.deleteOrder(id);
    }
}
