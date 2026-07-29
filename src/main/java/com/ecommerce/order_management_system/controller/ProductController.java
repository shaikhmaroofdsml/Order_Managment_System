package com.ecommerce.order_management_system.controller;

import com.ecommerce.order_management_system.dto.ProductRequest;
import com.ecommerce.order_management_system.dto.ProductResponse;
import com.ecommerce.order_management_system.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

    // create Product

    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductRequest request)
    {
        return productService.create(request);
    }

    @GetMapping
    public List<ProductResponse> getAll()
    {
        return  productService.getALl();
    }

    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable Long id)
    {
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ProductResponse update(@PathVariable Long id,@Valid @RequestBody ProductRequest request)
    {
        return productService.update(id,request);
    }

    @DeleteMapping("/{id}")
    public String deleteProduct(@PathVariable Long id)
    {
        productService.delete(id);
        return  "Product deleted successfully......";

    }


    /// ////////////////////findBy*****//////////////////////////

    @GetMapping("/search")
    public List<ProductResponse> findByName(@RequestParam String name) {
        return productService.findByName(name);
    }

    @GetMapping("/search")
    public List<ProductResponse> findByNameContaining(@RequestParam String keyword)
    {

        return productService.findByNameContaining(keyword);
    }
}