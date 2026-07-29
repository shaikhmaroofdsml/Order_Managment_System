package com.ecommerce.order_management_system.controller;

import com.ecommerce.order_management_system.dto.CategoryRequest;
import com.ecommerce.order_management_system.dto.CategoryResponse;
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
    public ResponseEntity<CategoryResponse> createCategory(
            @Valid @RequestBody CategoryRequest request)
    {
        return  new ResponseEntity<>(categoryService.create(request), HttpStatus.CREATED);
    }

    @GetMapping
    public List<CategoryResponse> getALl()
    {
        return  categoryService.getAll();
    }

    @GetMapping("/{id}")
    public CategoryResponse getById(@PathVariable Long id)
    {
        return categoryService.getById(id);
    }

    @PutMapping("/{id}")
    public CategoryResponse updateCategory(@PathVariable Long id, @RequestBody CategoryRequest request)
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
