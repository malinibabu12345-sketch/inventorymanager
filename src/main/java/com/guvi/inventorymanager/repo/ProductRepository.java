package com.guvi.inventorymanager.repo;

import com.guvi.inventorymanager.model.Product;
import com.guvi.inventorymanager.model.ProductStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

    Page<Product> findByCategoriesId(Long categoryId, Pageable pageable);

    Page<Product> findByStockQuantityLessThan(Integer stockQuantity, Pageable pageable);

    Page<Product> findByStatus(ProductStatus status, Pageable pageable);

    Page<Product> findByNameContainingIgnoreCase(String name, Pageable pageable);
}
