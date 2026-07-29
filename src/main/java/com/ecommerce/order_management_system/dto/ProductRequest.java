package com.ecommerce.order_management_system.dto;

import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.Data;
import lombok.NonNull;

@Data
public class ProductRequest {

    @NonNull
    private String name;

    @NonNull
    private Double price;

    @NonNull
    private Integer stock;

    @NonNull
    private String description;

    @NonNull
    private Long category_Id;

    //The client should not send the entire Category object.
    // We only need its ID to associate the product.

}
