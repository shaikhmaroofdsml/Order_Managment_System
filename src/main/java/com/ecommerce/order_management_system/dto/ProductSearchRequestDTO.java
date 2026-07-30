package com.ecommerce.order_management_system.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductSearchRequestDTO { // request means application to client(postman).

    private String name;
    private String category;
    private Double minPrice;
    private Double maxPrice;
    private Integer stock;
    private Boolean active;
    private Integer page= 0;
    private Integer size= 5;
    private String field= "id";
    private String direction = "asc";


    // requirement for search
    //WHERE category='Electronics'
    //AND price>100
    //AND price<2000
    //AND stock>10
    //AND name LIKE '%Laptop%'



}
