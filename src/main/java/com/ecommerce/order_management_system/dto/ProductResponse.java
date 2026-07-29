package com.ecommerce.order_management_system.dto;

import com.ecommerce.order_management_system.entity.Category;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ProductResponse {

    private Long id;

    private String name;

    private Double price;

    private Integer stock;

    private String description;

    private String categoryName;
}
