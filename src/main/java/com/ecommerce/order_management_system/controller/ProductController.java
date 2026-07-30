package com.ecommerce.order_management_system.controller;

import com.ecommerce.order_management_system.dto.ProductRequest;
import com.ecommerce.order_management_system.dto.ProductResponse;
import com.ecommerce.order_management_system.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;

//    Phase 6: Pagination, Sorting & Filtering

    @GetMapping("/page")
    public Page<ProductResponse> getProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size)
    {
        return productService.getProducts(page,size);
    }


        // Sorting
    @GetMapping("/sort")
    public List<ProductResponse> sort(@RequestParam String field, @RequestParam String direction)
    {
        return productService.sort(field,direction);

    }


    // @Modyfying Example  ......

    @PatchMapping("/{id}/stock/add") // partially updating the Request.
    public String increaseStock(@PathVariable Long id,
                                @RequestParam Integer qty)
    {
        productService.increaseStock(id,qty);
        return  "stock increased successfully.......";
    }


    @PatchMapping("/{id}/soft-delete")
    public String softDelete(@PathVariable Long id) {

        productService.softDelete(id);
        return "Product soft deleted.";
    }



    @PatchMapping("/{id}/restore")
    public String restore(@PathVariable Long id) {

        productService.restore(id);
        return "Product restored.";
    }



//    @PatchMapping("/{id}/stock/remove")
//    public String decreaseStock(@PathVariable Long id,
//                                @RequestParam Integer qty) {
//
//        productService.decreaseStock(id, qty);
//        return "Stock decreased successfully.";
//    }

//    @PatchMapping("/{id}/price")
//    public String updatePrice(@PathVariable Long id,
//                              @RequestParam Double price) {
//
//        productService.updatePrice(id, price);
//        return "Price updated successfully.";
//    }



//    @PatchMapping("/refill")
//    public String refillStock(@RequestParam Long categoryId) {
//
//        productService.refillStock(categoryId);
//        return "Stock refilled.";
//    }




    /// ///////// CRUD OPERATIONS ///////////

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


    /// ////////////////////    findBy*****     //////////////////////////

    @GetMapping("/search")
    public List<ProductResponse> findByName(@RequestParam String name) {
        return productService.findByName(name);
    }

//    @GetMapping("/search") //
//    public List<ProductResponse> findByNameContaining(@RequestParam String keyword)
//    {
//
//        return productService.findByNameContaining(keyword);
//    }


//    @GetMapping("search") //
//    public List<ProductResponse>  findByStockLessThan(@RequestParam Integer stock)
//    {
//        return productService.findByStockLessThan(stock);
//    }

        /// ////////////////////    Custom Queries *****     //////////////////////////





}