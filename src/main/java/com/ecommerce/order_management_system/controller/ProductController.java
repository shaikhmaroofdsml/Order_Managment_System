package com.ecommerce.order_management_system.controller;

import com.ecommerce.order_management_system.dto.ProductRequestDTO;
import com.ecommerce.order_management_system.dto.ProductResponseDTO;
import com.ecommerce.order_management_system.dto.ProductSearchRequestDTO;
import com.ecommerce.order_management_system.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ProductController {

    private final ProductService productService;


    // specification
    @PostMapping("/specificationSearch")
    public Page<ProductResponseDTO> search(@RequestBody ProductSearchRequestDTO request){

        return productService.search(request);

    }





//    Phase 6: Pagination, Sorting & Filtering

    // Pagination
    @GetMapping("/page")
    public Page<ProductResponseDTO> getProducts(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "5") int size)
    {
        return productService.getProducts(page,size);
    }


        // Sorting
    @GetMapping("/sort")
    public List<ProductResponseDTO> sort(@RequestParam String field, @RequestParam String direction)
    {
        return productService.sort(field,direction);

    }

    // Pagination + Sorting

    @GetMapping("/page-sort")
    public Page<ProductResponseDTO> getProducts(

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "5")
            int size,

            @RequestParam(defaultValue = "id")
            String field,

            @RequestParam(defaultValue = "asc")
            String direction) {

        return productService.getProductWithSorting(page, size, field, direction);
    }

//    Filtering

    @GetMapping("/category_filter") // GET /api/products/category_filter?name=Electronics&page=0&size=5
    public Page<ProductResponseDTO> category(

            @RequestParam String name,

            @RequestParam(defaultValue = "0")
            int page,

            @RequestParam(defaultValue = "10")
            int size) {

        return productService
                .getProductsByCategory(name, page, size);
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
    public ProductResponseDTO create(@Valid @RequestBody ProductRequestDTO request)
    {
        return productService.create(request);
    }

    @GetMapping
    public List<ProductResponseDTO> getAll()
    {
        return  productService.getALl();
    }

    @GetMapping("/{id}")
    public ProductResponseDTO getById(@PathVariable Long id)
    {
        return productService.getById(id);
    }

    @PutMapping("/{id}")
    public ProductResponseDTO update(@PathVariable Long id, @Valid @RequestBody ProductRequestDTO request)
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
    public List<ProductResponseDTO> findByName(@RequestParam String name) {
        return productService.findByName(name);
    }

//    @GetMapping("/search") //
//    public List<ProductResponseDTO> findByNameContaining(@RequestParam String keyword)
//    {
//
//        return productService.findByNameContaining(keyword);
//    }


//    @GetMapping("search") //
//    public List<ProductResponseDTO>  findByStockLessThan(@RequestParam Integer stock)
//    {
//        return productService.findByStockLessThan(stock);
//    }

        /// ////////////////////    Custom Queries *****     //////////////////////////





}