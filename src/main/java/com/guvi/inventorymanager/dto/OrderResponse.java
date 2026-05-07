package com.guvi.inventorymanager.dto;

import com.guvi.inventorymanager.model.OrderStatus;

import java.time.LocalDateTime;

public class OrderResponse {

    private Long id;

    private LocalDateTime orderDate;

    private double totalAmount;

    private OrderStatus status;

    public OrderResponse() {}

    public OrderResponse(Long id, LocalDateTime orderDate,
                         double totalAmount, OrderStatus status) {
        this.id = id;
        this.orderDate = orderDate;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDateTime orderDate) {
        this.orderDate = orderDate;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() { return status; }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }
}
