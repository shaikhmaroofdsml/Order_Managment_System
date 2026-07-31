package com.ecommerce.order_management_system.repo;

import com.ecommerce.order_management_system.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {


    List<Order> findByCustomerId(Long customerId);
    // ALT + SHIFT + ENTER to import

}
