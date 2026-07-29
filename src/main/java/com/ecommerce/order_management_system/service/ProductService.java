package com.ecommerce.order_management_system.service;

import com.ecommerce.order_management_system.dto.ProductRequest;
import com.ecommerce.order_management_system.dto.ProductResponse;
import com.ecommerce.order_management_system.entity.Product;

import java.util.List;

public interface ProductService {

    // Actual requirement is here only.
    ProductResponse create(ProductRequest request);

    List<ProductResponse> getALl();

    ProductResponse getById(Long id);

    ProductResponse update(Long id,ProductRequest request);

    void delete(Long id);

    // Other than CRUD
    public  List<ProductResponse> findByName(String name);
    public List<ProductResponse> findByNameContaining(String keyword);
}
