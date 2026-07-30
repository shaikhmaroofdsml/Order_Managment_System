package com.ecommerce.order_management_system.controller;

import com.ecommerce.order_management_system.dto.CustomerRequestDTO;
import com.ecommerce.order_management_system.dto.CustomerResponseDTO;
import com.ecommerce.order_management_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customers")
public class CustomerController {


    private final CustomerService customerService;

    @PostMapping
    public CustomerResponseDTO create(
            @RequestBody CustomerRequestDTO request) {

        return customerService.create(request);
    }

    @GetMapping
    public List<CustomerResponseDTO> getAll() {

        return customerService.getAll();
    }

    @GetMapping("/{id}")
    public CustomerResponseDTO getById(
            @PathVariable Long id) {

        return customerService.getById(id);
    }

}
