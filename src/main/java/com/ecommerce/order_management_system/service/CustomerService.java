package com.ecommerce.order_management_system.service;

import com.ecommerce.order_management_system.dto.CustomerRequestDTO;
import com.ecommerce.order_management_system.dto.CustomerResponseDTO;

import java.util.List;

public interface CustomerService {


    CustomerResponseDTO create(CustomerRequestDTO request);

    List<CustomerResponseDTO> getAll();

    CustomerResponseDTO getById(Long id);
}
