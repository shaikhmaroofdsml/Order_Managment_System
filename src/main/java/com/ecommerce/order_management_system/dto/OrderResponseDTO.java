package com.ecommerce.order_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderResponseDTO {

    private Long orderId;

    private Double totalAmount;

    private String status;

    private String customerName;

    private LocalDateTime orderDate;

    private List<OrderItemResponseDTO> items;
}
