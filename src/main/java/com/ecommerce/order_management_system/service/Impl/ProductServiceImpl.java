package com.ecommerce.order_management_system.service.Impl;

import com.ecommerce.order_management_system.dto.ProductRequestDTO;
import com.ecommerce.order_management_system.dto.ProductResponseDTO;
import com.ecommerce.order_management_system.dto.ProductSearchRequestDTO;
import com.ecommerce.order_management_system.entity.Category;
import com.ecommerce.order_management_system.entity.Product;
import com.ecommerce.order_management_system.exception.CategoryNotFoundException;
import com.ecommerce.order_management_system.exception.ProductNotFoundException;
import com.ecommerce.order_management_system.repo.CategoryRepository;
import com.ecommerce.order_management_system.repo.ProductRepository;
import com.ecommerce.order_management_system.service.ProductService;
import com.ecommerce.order_management_system.specification.ProductSpecification;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;



    // JPASpecificationExecutor

    @Override
    public Page<ProductResponseDTO> search(ProductSearchRequestDTO request) {
        Specification<Product> specification = (root, query, cb) -> cb.conjunction();

        if(request.getName()!=null  && !request.getName().isBlank())
        {
            specification = specification.and(ProductSpecification.hasName(request.getName()));
        }
        if(request.getCategory() !=null && !request.getCategory().isBlank())
        {
            specification = specification.and(ProductSpecification.hasCategory(request.getCategory()));
        }

        if (request.getMinPrice() != null) {
            specification = specification.and(ProductSpecification.minPrice(request.getMinPrice()));
        }

        if(request.getMaxPrice()!=null){

            specification= specification.and(ProductSpecification.maxPrice(request.getMaxPrice()));
        }

        if(request.getStock()!=null){

            specification= specification.and(ProductSpecification.stockGreaterThan(request.getStock()));

        }

        if(request.getActive()!=null){

            specification= specification.and(ProductSpecification.active(request.getActive()));

        }

//        Sort sort = request.getDirection().equalsIgnoreCase("desc")
//                        ?
//                        Sort.by(request.getField())
//                                .descending()
//                        : Sort.by(request.getField()).ascending();
//
//        int page = request.getPage() != null ? request.getPage():0;
//        int size = request.getSize() !=null && request.getSize() >0
//                ? request.getSize() : 5;
//
//        Pageable pageable =
//                PageRequest.of(
//                        page,size, sort
//                );

        int page = request.getPage() != null ? request.getPage() : 0;
        int size = (request.getSize() != null && request.getSize() > 0)
                ? request.getSize() : 10;

        String field = request.getField() != null
                ? request.getField()
                : "id";

        String direction = request.getDirection() != null
                ? request.getDirection()
                : "asc";

        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        Pageable pageable = PageRequest.of(page, size, sort);

        return productRepository.findAll(specification,pageable)
                .map(this::mapToResponse);

    }







//    Phase 6: Pagination, Sorting & Filtering


    // Pagination
    @Override
    public Page<ProductResponseDTO> getProducts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Product> products = productRepository.findAll(pageable);
        return products.map(this::mapToResponse);
    }

    // Sorting
    @Override
    public List<ProductResponseDTO> sort(String field, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();
        return productRepository.findAll(sort)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // Pagination + Sorting
    @Override
    public Page<ProductResponseDTO> getProductWithSorting(int page, int size, String field, String direction) {
        Sort sorting = direction.equalsIgnoreCase("desc")
                ? Sort.by(field).descending()
                : Sort.by(field).ascending();

        Pageable pageable = PageRequest.of(page,size);

        return productRepository
                .findAll(pageable)
                .map(this::mapToResponse);
    }

    // Filtering



    @Override
    public Page<ProductResponseDTO> getProductsByCategory(
            String category,
            int page,
            int size) {

        Pageable pageable =
                PageRequest.of(page, size);

        return productRepository
                .findByCategory_Name(category, pageable)
                .map(this::mapToResponse);
    }




    // @Modifying
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
    public ProductResponseDTO create(ProductRequestDTO request) {

        Category category = categoryRepository.findById(request.getCategory_Id())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + request.getCategory_Id()));

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
    public List<ProductResponseDTO> getALl() {

        return productRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public ProductResponseDTO getById(Long id) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        return mapToResponse(product);
    }

    @Override
    public ProductResponseDTO update(Long id, ProductRequestDTO request) {

        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(
                        "Product not found with id: " + id));

        Category category = categoryRepository.findById(request.getCategory_Id())
                .orElseThrow(() -> new CategoryNotFoundException(
                        "Category not found with id: " + request.getCategory_Id()));

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
                .orElseThrow(() ->new ProductNotFoundException(
                        "Product not found with id: " + id));

        productRepository.delete(product);
    }
    /// /////////////////////// FindBY*** /////////////////////////

    // findByName
    @Override
    public List<ProductResponseDTO> findByName(String name) {

        return productRepository.findByName(name)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }
    // findByNameContaining()
    @Override
    public List<ProductResponseDTO> findByNameContaining(String keyword) {

        return productRepository.findByNameContaining(keyword)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public List<ProductResponseDTO> findByStockLessThan(Integer stock) {
        return productRepository.findByStockLessThan(stock)
                .stream()
                .map(this::mapToResponse)
                .toList();

    }


    /// /////////////////////// Helper method /////////////////////////
    ///
    private ProductResponseDTO mapToResponse(Product product) {

        return ProductResponseDTO.builder()
                .id(product.getId())
                .name(product.getName())
                .price(product.getPrice())
                .stock(product.getStock())
                .description(product.getDescription())
                .categoryName(product.getCategory().getName())
                .build();
    }
}