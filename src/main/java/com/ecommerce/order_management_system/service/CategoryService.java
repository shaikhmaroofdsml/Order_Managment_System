package com.ecommerce.order_management_system.service;

import com.ecommerce.order_management_system.dto.CategoryRequest;
import com.ecommerce.order_management_system.dto.CategoryResponse;

import java.util.List;

public interface CategoryService {

    CategoryResponse  create(CategoryRequest request);

    List<CategoryResponse> getAll();

    CategoryResponse getById(Long id);

    CategoryResponse update(Long id,CategoryRequest request);

    void delete(Long id);
}
