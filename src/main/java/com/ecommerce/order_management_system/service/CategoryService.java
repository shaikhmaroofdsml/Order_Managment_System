package com.ecommerce.order_management_system.service;

import com.ecommerce.order_management_system.dto.CategoryRequestDTO;
import com.ecommerce.order_management_system.dto.CategoryResponseDTO;

import java.util.List;

public interface CategoryService {

    CategoryResponseDTO create(CategoryRequestDTO request);

    List<CategoryResponseDTO> getAll();

    CategoryResponseDTO getById(Long id);

    CategoryResponseDTO update(Long id, CategoryRequestDTO request);

    void delete(Long id);
}
