package com.ecommerce.order_management_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {

    private Long id;

    private String name;

    private Double price;

    private Integer stock;

    private String description;

    private String categoryName;
}
