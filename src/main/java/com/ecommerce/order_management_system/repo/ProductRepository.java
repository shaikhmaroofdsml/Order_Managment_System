package com.ecommerce.order_management_system.repo;

import com.ecommerce.order_management_system.entity.Category;
import com.ecommerce.order_management_system.entity.Product;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product,Long>,
        JpaSpecificationExecutor<Product> {

    //  ********* JpaSpecificationExecutor<Product>  *********
    // Dynamic Search using JpaSpecificationExecutor (Specifications), where users can combine filters like:
    //Category = Electronics
    //Price between 100 and 1000
    //Stock > 10
    //Name contains "Laptop"
    //
    //all in a single API without creating dozens of repository methods.

    // *********







    // Category Filter
    Page<Product> findByCategory_Name(
            String name,
            Pageable pageable);





    // @Modifying + @Transactional

    @Modifying
    @Transactional
    @Query("""
        UPDATE Product
        SET stock = stock + :qty
        WHERE id = :id
    """)
    int increaseStock(@Param("id") Long id,
                      @Param("qty") Integer qty);

        @Modifying
    @Transactional
    @Query("""
        UPDATE Product
        SET active = false
        WHERE id = :id
    """)
    int softDelete(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("""
        UPDATE Product
        SET active = true
        WHERE id = :id
    """)
    int restore(@Param("id") Long id);

//
//@Modifying
//@Transactional
//@Query("""
//        UPDATE Product
//        SET stock = stock - :qty
//        WHERE id = :id
//    """)
//int decreaseStock(@Param("id") Long id,
//                  @Param("qty") Integer qty);
//
//    @Modifying
//    @Transactional
//    @Query("""
//        UPDATE Product
//        SET price = :price
//        WHERE id = :id
//    """)
//    int updatePrice(@Param("id") Long id,
//                    @Param("price") Double price);
//

//
//    @Modifying
//    @Transactional
//    @Query("""
//        DELETE FROM Product
//        WHERE id = :id
//    """)
//    int deleteProduct(@Param("id") Long id);
//
//    @Modifying
//    @Transactional
//    @Query("""
//        UPDATE Product
//        SET price = price * 0.90
//    """)
//    int applyFestivalDiscount();
//
//    @Modifying
//    @Transactional
//    @Query("""
//        UPDATE Product
//        SET stock = stock + 100
//        WHERE category.id = :categoryId
//    """)
//    int refillStock(@Param("categoryId") Long categoryId);
     //



    ///         ==================================

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
    List<Product> findByStockLessThan(Integer stock);

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
