package com.ecommerce.order_management_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class OrderRequestDTO {

    private Long customerId;

    private List<OrderItemRequestDTO> items;

    private String paymentType;
}
