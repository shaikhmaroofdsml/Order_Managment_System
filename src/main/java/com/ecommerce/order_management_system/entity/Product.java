package com.ecommerce.order_management_system.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "products")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder // Avoid using @Data in entity use it in DTO.
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private  Double price;

    @Column(nullable = false)
    private Integer stock;

    @Column(length = 100)
    private String description;

    @ManyToOne // Many Product belong to same category.
    @JoinColumn(name = "category_id")
    private Category category;
}
