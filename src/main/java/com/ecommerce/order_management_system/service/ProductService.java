package com.ecommerce.order_management_system.service;

import com.ecommerce.order_management_system.dto.ProductRequestDTO;
import com.ecommerce.order_management_system.dto.ProductResponseDTO;
import com.ecommerce.order_management_system.dto.ProductSearchRequestDTO;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ProductService {


    //JPASpecification
    Page<ProductResponseDTO> search(ProductSearchRequestDTO request);




//    Phase 6: Pagination, Sorting & Filtering

    // Filtering
    Page<ProductResponseDTO> getProductsByCategory(
            String category,
            int page,
            int size);

    // Pagination
    Page<ProductResponseDTO> getProducts(int page, int size);

    // Sorting
    List<ProductResponseDTO> sort(String field, String direction);

    // Pagination + Sorting here
    Page<ProductResponseDTO> getProductWithSorting(
            int page,
            int size,
            String field,
            String direction);






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
    ProductResponseDTO create(ProductRequestDTO request);

    List<ProductResponseDTO> getALl();

    ProductResponseDTO getById(Long id);

    ProductResponseDTO update(Long id, ProductRequestDTO request);

    void delete(Long id);

    // Other than CRUD
    public  List<ProductResponseDTO> findByName(String name);

    List<ProductResponseDTO> findByNameContaining(String keyword);

    List<ProductResponseDTO> findByStockLessThan(Integer stock);
}
