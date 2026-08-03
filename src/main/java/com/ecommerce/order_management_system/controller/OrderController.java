package com.ecommerce.order_management_system.controller;


import com.ecommerce.order_management_system.dto.OrderRequestDTO;
import com.ecommerce.order_management_system.dto.OrderResponseDTO;
import com.ecommerce.order_management_system.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/orders")
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponseDTO placeOrder(@RequestBody OrderRequestDTO request){


        return  orderService.placeOrder(request);

    }

    @GetMapping("/{id}")
    public OrderResponseDTO getOrder(@PathVariable Long id) {

        return orderService.getOrder(id);
    }

    @GetMapping("/customer/{customerId}")
    public List<OrderResponseDTO> getCustomerOrders(
            @PathVariable Long customerId) {

        return orderService.getCustomerOrders(customerId);
    }
    // Cancel Order

    @PutMapping("/{id}/cancel")
    public String cancelOrder(@PathVariable Long id)
    {
        orderService.cancelOrder(id);
        return "order cancelled successfully";
    }

}
