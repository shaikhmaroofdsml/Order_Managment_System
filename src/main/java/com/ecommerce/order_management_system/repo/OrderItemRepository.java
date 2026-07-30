package com.ecommerce.order_management_system.repo;

import com.ecommerce.order_management_system.entity.Order;
import com.ecommerce.order_management_system.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
