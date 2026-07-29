package com.ecommerce.order_management_system.repo;

import com.ecommerce.order_management_system.entity.Category;
import com.ecommerce.order_management_system.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long> {


    List<Product> findByName(String name);

    // FindByName // select * from products where name = ?; we will write method here
    //if we want to perform crud operations only then we can write it in service. other than service we will
//    declare here.

    //findByNameContaining(String keyword); // SELECT * FROM products WHERE name LIKE '%Book%';

    List<Product> findByNameContaining(String keyword); // GET /api/products/search?keyword=Book

    //findByPriceGreaterThan(Double price) // SELECT * FROM products WHERE price > ?;
//    List<Product> findByPriceGreaterThan(Double price);


//    List<Product> findByPriceLessThan(Double price);
//
//    List<Product> findByPriceBetween(Double min, Double max);
//
//    List<Product> findByStockLessThan(Integer stock);
//
//    List<Product> findByCategory(Category category);
//
//    List<Product> findByCategoryName(String categoryName);
//
//    List<Product> findByCategoryNameAndPriceLessThan(
//            String category,
//            Double price);
//
//    List<Product> findByOrderByPriceAsc();
//
//    List<Product> findByOrderByPriceDesc();
//
//    List<Product> findTop5ByOrderByPriceDesc();
//
//    Product findFirstByOrderByPriceAsc();
//
//    long countByCategoryName(String category);
//
//    boolean existsByName(String name);

}
