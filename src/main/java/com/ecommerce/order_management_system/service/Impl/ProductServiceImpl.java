package com.ecommerce.order_management_system.service.Impl;

import com.ecommerce.order_management_system.dto.ProductRequest;
import com.ecommerce.order_management_system.dto.ProductResponse;
import com.ecommerce.order_management_system.entity.Category;
import com.ecommerce.order_management_system.entity.Product;
import com.ecommerce.order_management_system.repo.CategoryRepository;
import com.ecommerce.order_management_system.repo.ProductRepository;
import com.ecommerce.order_management_system.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public void increaseStock(Long id, Integer qty) {
        productRepository.increaseStock(id, qty);
    }

    @Override
    public void softDelete(Long id) {

        productRepository.softDelete(id);
    }

    @Override
    public void restore(Long id) {

        productRepository.restore(id);
    }



//    @Override
//    public void decreaseStock(Long id, Integer qty) {
//        productRepository.decreaseStock(id, qty);
//    }
//
//    @Override
//    public void updatePrice(Long id, Double price) {
//        productRepository.updatePrice(id, price);
//    }
//

//    }
//
//    @Override
//    public void applyFestivalDiscount() {
//        productRepository.applyFestivalDiscount();
//    }
//
//    @Override
//    public void refillStock(Long categoryId) {
//        productRepository.refillStock(categoryId);
//    }
//


    /// /////////  CRUD Operations started......   ///////////
    @Override
    public ProductResponse create(ProductRequest request) {

        Category category = categoryRepository.findById(request.getCategory_Id())
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        Product product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .stock(request.getStock())
                .description(request.getDescription())
                .category(category)
                .build();

        Product saved = productRepository.save(product);

        return mapToResponse(saved);
    }

    @Override
    public List<ProductResponse> getALl() {

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponse getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        return mapToResponse(product);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        Category category = categoryRepository.findById(request.getCategory_Id())
                .orElseThrow(() -> new RuntimeException("Category Not Found"));

        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setStock(request.getStock());
        product.setDescription(request.getDescription());
        product.setCategory(category);

        Product updatedProduct = productRepository.save(product);

        return mapToResponse(updatedProduct);
    }

    @Override
    public void delete(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product Not Found"));

        productRepository.delete(product);
    }
    /// /////////////////////// FindBY*** /////////////////////////

    // findByName
    @Override
    public List<ProductResponse> findByName(String name) {

        return productRepository.findByName(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    // findByNameContaining()
    @Override
    public List<ProductResponse> findByNameContaining(String keyword) {

        return productRepository.findByNameContaining(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ProductResponse> findByStockLessThan(Integer stock) {
        return productRepository.findByStockLessThan(stock)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }


    /// /////////////////////// Helper method /////////////////////////
    ///
    private ProductResponse mapToResponse(Product product) {

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .categoryName(product.getCategory().getName())
                .build();
    }
}