package com.ecommerce.order_management_system.repo;

import com.ecommerce.order_management_system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
