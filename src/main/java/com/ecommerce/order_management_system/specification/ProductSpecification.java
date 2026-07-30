package com.ecommerce.order_management_system.specification;

import com.ecommerce.order_management_system.entity.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecification {

    // Search by name
    public static Specification<Product> hasName(String name)
    {
        return  ((root, query, criteriaBuilder) ->
                criteriaBuilder.like(criteriaBuilder.lower(root.get("name")),
                        "%"+name.toLowerCase() + "%")
                );
    }

    // search by category

    public static Specification<Product> hasCategory(String category)
    {
        return ((root, query, criteriaBuilder) ->
                criteriaBuilder.equal(
                        root.get("category").get("name"),category)
                );
    }

    // search by min price
    public static Specification<Product>

    minPrice(Double price){

        return (root,query,cb)->

                cb.greaterThanOrEqualTo(

                        root.get("price"),

                        price

                );

    }

    // search by max price
    public static Specification<Product>

    maxPrice(Double price){

        return (root,query,cb)->

                cb.lessThanOrEqualTo(

                        root.get("price"),

                        price

                );

    }


    // search by stock
    public static Specification<Product>

    stockGreaterThan(Integer stock){

        return (root,query,cb)->

                cb.greaterThan(

                        root.get("stock"),

                        stock

                );

    }


    public static Specification<Product>

    active(Boolean active){

        return (root,query,cb)->

                cb.equal(

                        root.get("active"),

                        active

                );

    }




}
