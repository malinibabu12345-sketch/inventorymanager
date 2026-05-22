package com.guvi.inventorymanager.service;

import com.guvi.inventorymanager.model.Product;
import com.guvi.inventorymanager.repo.ProductRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductService productService;

    @Test
    void testGetProducts() {

        Product product = new Product();
        product.setName("Laptop");

        when(productRepository.findAll())
                .thenReturn(List.of(product));

        List<Product> result = productRepository.findAll();

        assertEquals(1, result.size());
    }
}
