package com.ecommerce.order_management_system.service.Impl;

import com.ecommerce.order_management_system.dto.CustomerRequestDTO;
import com.ecommerce.order_management_system.dto.CustomerResponseDTO;
import com.ecommerce.order_management_system.entity.Customer;
import com.ecommerce.order_management_system.repo.CustomerRepository;
import com.ecommerce.order_management_system.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {


    private final CustomerRepository customerRepository;

    @Override
    public CustomerResponseDTO create(CustomerRequestDTO request) {
        Customer customer = Customer.builder()
                .name(request.getName())
                .email(request.getEmail())
                .build();

        customer = customerRepository.save(customer);
        return mapToResponse(customer);
    }

    @Override
    public List<CustomerResponseDTO> getAll() {
        return customerRepository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    @Override
    public CustomerResponseDTO getById(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Customer not found"));

        return mapToResponse(customer);
    }

    private CustomerResponseDTO mapToResponse(Customer customer) {

        return CustomerResponseDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .email(customer.getEmail())
                .build();
    }
}
