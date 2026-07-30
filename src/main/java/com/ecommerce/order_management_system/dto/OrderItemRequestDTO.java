package com.ecommerce.order_management_system.dto;

import lombok.Data;

@Data
public class OrderItemRequestDTO {

    private Long productId;

    private Integer quantity;
}
