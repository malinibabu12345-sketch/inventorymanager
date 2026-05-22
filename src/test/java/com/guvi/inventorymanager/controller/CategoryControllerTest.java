package com.guvi.inventorymanager.controller;

import com.guvi.inventorymanager.dto.CategoryRequest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CategoryControllerTest {

    @Test
    void testCategoryRequest() {

        CategoryRequest request = new CategoryRequest();

        request.setName("Electronics");

        assertEquals("Electronics", request.getName());
    }
}
