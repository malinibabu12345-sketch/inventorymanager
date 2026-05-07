package com.guvi.inventorymanager.service;

import com.guvi.inventorymanager.dto.ProductRequest;
import com.guvi.inventorymanager.model.Category;
import com.guvi.inventorymanager.model.Product;
import com.guvi.inventorymanager.model.ProductStatus;
import com.guvi.inventorymanager.repo.CategoryRepository;
import com.guvi.inventorymanager.repo.ProductRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductService(ProductRepository productRepository,
                          CategoryRepository categoryRepository) {
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    public Product createProduct(ProductRequest request) {
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : request.getCategoryIds()) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            categories.add(category);
        }

        Product product = new Product();

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategories(categories);
        product.setStatus(ProductStatus.ACTIVE);

        return productRepository.save(product);
    }

    public Product updateProduct(Long id, ProductRequest request) {
        Product product = getProductById(id);
        Set<Category> categories = new HashSet<>();
        for (Long categoryId : request.getCategoryIds()) {
            Category category = categoryRepository.findById(categoryId)
                    .orElseThrow(() -> new RuntimeException("Category not found"));
            categories.add(category);
        }

        product.setName(request.getName());
        product.setDescription(request.getDescription());
        product.setPrice(request.getPrice());
        product.setStockQuantity(request.getStockQuantity());
        product.setCategories(categories);

        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Page<Product> getAllProducts(int page, int size,
                                        String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ?
                Sort.by(sortBy).descending() :
                Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return productRepository.findAll(pageable);
    }

    public Page<Product> searchProducts(String name, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByNameContainingIgnoreCase(name, pageable);
    }

    public Page<Product> filterByCategory(Long categoryId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByCategoriesId(categoryId, pageable);
    }

    public Page<Product> lowStockProducts(Integer quantity, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return productRepository.findByStockQuantityLessThan(quantity, pageable);
    }

    public Product activateProduct(Long id) {
        Product product = getProductById(id);
        product.setStatus(ProductStatus.ACTIVE);
        return productRepository.save(product);
    }

    public Product deactivateProduct(Long id) {
        Product product = getProductById(id);
        product.setStatus(ProductStatus.INACTIVE);
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        productRepository.delete(product);
    }
}