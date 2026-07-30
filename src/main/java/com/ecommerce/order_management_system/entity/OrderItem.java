package com.ecommerce.order_management_system.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private Integer qty;

    private  Double price;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private  Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


}
