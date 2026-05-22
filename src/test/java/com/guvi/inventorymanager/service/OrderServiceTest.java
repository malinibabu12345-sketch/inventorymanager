package com.guvi.inventorymanager.service;

import com.guvi.inventorymanager.model.Order;
import com.guvi.inventorymanager.repo.OrderRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void testGetOrders() {

        Order order = new Order();
        when(orderRepository.findAll())
                .thenReturn(List.of(order));

        List<Order> result = orderRepository.findAll();
        assertEquals(1, result.size());
    }
}
