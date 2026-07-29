package com.ecommerce.order_management_system.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "categories")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(length = 500)
    private String description;

    @OneToMany(mappedBy = "category", // / One category belongs to multiple products
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY)  // If we fetch category then category details only fetch
    private List<Product> product;

}