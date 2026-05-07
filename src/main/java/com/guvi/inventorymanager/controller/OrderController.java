package com.guvi.inventorymanager.controller;

import com.guvi.inventorymanager.dto.OrderRequest;
import com.guvi.inventorymanager.model.Order;
import com.guvi.inventorymanager.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping
    public ResponseEntity<Order> placeOrder(@Valid @RequestBody
                                                OrderRequest request,
                                            Authentication authentication) {
        return ResponseEntity.ok(orderService.placeOrder(request,
                authentication.getName()));
    }

    @PreAuthorize("hasRole('USER')")
    @GetMapping
    public ResponseEntity<Page<Order>> getMyOrders(
            Authentication authentication,
            @RequestParam (defaultValue = "0") int page,
            @RequestParam (defaultValue = "5") int size) {

        return ResponseEntity.ok(orderService.getMyOrders(
                authentication.getName(),page, size));
    }

    @PreAuthorize("hasRole('USER')")
    @PutMapping("/{orderId}/cancel")
    public ResponseEntity<Order> cancelOrder(@PathVariable Long orderId) {

        return ResponseEntity.ok(orderService.cancelOrder(orderId));
    }
}
