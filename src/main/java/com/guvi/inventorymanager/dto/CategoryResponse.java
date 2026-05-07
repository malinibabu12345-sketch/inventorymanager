package com.guvi.inventorymanager.dto;

public class CategoryResponse {

    private  Long id;

    private String name;

    public CategoryResponse() {
    }

    public Long getId() {

        return id;
    }

    public String getName() {

        return name;
    }

    public void setId(Long id) {

        this.id = id;
    }

    public void setName(String name) {

        this.name = name;
    }
}
