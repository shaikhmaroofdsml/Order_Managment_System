package com.ecommerce.order_management_system.controller;


import com.ecommerce.order_management_system.dto.OrderRequestDTO;
import com.ecommerce.order_management_system.dto.OrderResponseDTO;
import com.ecommerce.order_management_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDTO placeOrder(@RequestBody OrderRequestDTO request){


        return  orderService.placeOrder(request);

    }


}
