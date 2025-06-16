package com.example.orderservice.client;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryClient {

    private final RestTemplate restTemplate;

    public InventoryClient() {
        this.restTemplate = new RestTemplate();
    }

    public String checkInventory() {
        return restTemplate.getForObject("http://localhost:8082/api/inventory/test", String.class);
    }
}
