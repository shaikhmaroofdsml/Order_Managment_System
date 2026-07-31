package com.ecommerce.order_management_system.service;


import com.ecommerce.order_management_system.dto.OrderRequestDTO;
import com.ecommerce.order_management_system.dto.OrderResponseDTO;

import java.util.List;

public interface OrderService {

    OrderResponseDTO placeOrder(OrderRequestDTO request);

    OrderResponseDTO getOrder(Long id);

    List<OrderResponseDTO> getCustomerOrders(Long customerId);
}
