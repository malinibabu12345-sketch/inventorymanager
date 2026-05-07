package com.guvi.inventorymanager.repo;

import com.guvi.inventorymanager.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
