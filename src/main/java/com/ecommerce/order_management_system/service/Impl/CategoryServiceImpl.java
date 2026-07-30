package com.ecommerce.order_management_system.service.Impl;

import com.ecommerce.order_management_system.dto.CategoryRequestDTO;
import com.ecommerce.order_management_system.dto.CategoryResponseDTO;
import com.ecommerce.order_management_system.entity.Category;
import com.ecommerce.order_management_system.repo.CategoryRepository;
import com.ecommerce.order_management_system.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    @Override
    public CategoryResponseDTO create(CategoryRequestDTO request) {
        Category category = Category.builder()
                .name(request.getName())
                .description(request.getDescription())
                .build();

        Category saved =  categoryRepository.save(category);

        return CategoryResponseDTO.builder()
                .id(saved.getId())
                .name(saved.getName())
                .description(saved.getDescription())
                .build();
    }

    @Override
    public List<CategoryResponseDTO> getAll() {
       return categoryRepository.findAll()
                .stream()
                .map(c-> CategoryResponseDTO.builder()
                        .id(c.getId())
                        .name(c.getName())
                        .description(c.getDescription())
                        .build()).toList();

    }

    @Override
    public CategoryResponseDTO getById(Long id) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not Found ....."));

        return CategoryResponseDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .build();
    }

    @Override
    public CategoryResponseDTO update(Long id, CategoryRequestDTO request) {
        Category category =  categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category Not Found..."));
        category.setName(request.getName());
        category.setDescription(request.getDescription());

        Category updated = categoryRepository.save(category);
        return CategoryResponseDTO.builder()
                .id(updated.getId())
                .name(updated.getName())
                .description(updated.getDescription())
                .build();
    }

    @Override
    public void delete(Long id) {

        if(categoryRepository.existsById(id))
        {
            throw new RuntimeException("Category deleted");
        }
        categoryRepository.deleteById(id);
    }
}
