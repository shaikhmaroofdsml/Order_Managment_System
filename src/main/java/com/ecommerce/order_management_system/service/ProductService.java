package com.ecommerce.order_management_system.service;

import com.ecommerce.order_management_system.dto.ProductRequest;
import com.ecommerce.order_management_system.dto.ProductResponse;
import com.ecommerce.order_management_system.entity.Product;

import java.util.List;

public interface ProductService {

    // @Modyfying examples ...


    void increaseStock(Long id, Integer qty);

    void softDelete(Long id);

    void restore(Long id);

//    void decreaseStock(Long id, Integer qty);
//
//    void updatePrice(Long id, Double price);
//
//    void applyFestivalDiscount();
//
//    void refillStock(Long categoryId);












    // Actual requirement is here only.
    ProductResponse create(ProductRequest request);

    List<ProductResponse> getALl();

    ProductResponse getById(Long id);

    ProductResponse update(Long id,ProductRequest request);

    void delete(Long id);

    // Other than CRUD
    public  List<ProductResponse> findByName(String name);

    List<ProductResponse> findByNameContaining(String keyword);

    List<ProductResponse> findByStockLessThan(Integer stock);
}
