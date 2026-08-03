package com.ecommerce.order_management_system.repo;

import com.ecommerce.order_management_system.entity.Order;
import com.ecommerce.order_management_system.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrder(Order order);
}
