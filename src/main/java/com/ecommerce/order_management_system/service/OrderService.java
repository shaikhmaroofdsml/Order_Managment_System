package com.ecommerce.order_management_system.service;


import com.ecommerce.order_management_system.dto.OrderRequestDTO;
import com.ecommerce.order_management_system.dto.OrderResponseDTO;

public interface OrderService {

    OrderResponseDTO placeOrder(OrderRequestDTO request);
}
