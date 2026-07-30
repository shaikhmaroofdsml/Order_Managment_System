package com.ecommerce.order_management_system.controller;

import com.ecommerce.order_management_system.dto.CategoryRequestDTO;
import com.ecommerce.order_management_system.dto.CategoryResponseDTO;
import com.ecommerce.order_management_system.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/categories")
public class CategoryController {

    private final CategoryService categoryService;


    @PostMapping
    public ResponseEntity<CategoryResponseDTO> createCategory(
            @Valid @RequestBody CategoryRequestDTO request)
    {
        return  new ResponseEntity<>(categoryService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CategoryResponseDTO> getALl()
    {
        return  categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryResponseDTO getById(@PathVariable Long id)
    {
        return categoryService.getById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponseDTO updateCategory(@PathVariable Long id, @RequestBody CategoryRequestDTO request)
    {
        return categoryService.update(id,request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id)
    {
        categoryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
