package com.guvi.inventorymanager.dto;

import com.guvi.inventorymanager.model.ProductStatus;

public class ProductResponse {

    private Long id;

    private String name;

    private String description;

    private double price;

    private Integer stockQuantity;

    private ProductStatus status;

    public ProductResponse() {}

    public Long getId() {
        return id;
    }

    public void setId(Long Id) { this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public ProductStatus getStatus() {
        return status;
    }

    public void setStatus(ProductStatus status) {
      this.status = status;
    }
}
