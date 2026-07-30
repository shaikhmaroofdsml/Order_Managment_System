package com.ecommerce.order_management_system.service.Impl;

import com.ecommerce.order_management_system.dto.OrderItemRequestDTO;
import com.ecommerce.order_management_system.dto.OrderRequestDTO;
import com.ecommerce.order_management_system.dto.OrderResponseDTO;
import com.ecommerce.order_management_system.entity.*;
import com.ecommerce.order_management_system.repo.CustomerRepository;
import com.ecommerce.order_management_system.repo.OrderRepository;
import com.ecommerce.order_management_system.repo.PaymentRepository;
import com.ecommerce.order_management_system.repo.ProductRepository;
import com.ecommerce.order_management_system.service.OrderService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {


    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final OrderRepository orderRepository;
    private final PaymentRepository paymentRepository;



    @Override
    @Transactional // Begin transaction
    public OrderResponseDTO placeOrder(OrderRequestDTO request) {

        // validate customer
        Customer customer = customerRepository.findById(request.getCustomerId())
                .orElseThrow(()->
                        new RuntimeException(("Customer not found")));

        // validate empty order
        if(request.getItems() == null || request.getItems().isEmpty())
        {
        throw new RuntimeException("Order must contain at least one item .");

        }
        // create order object

        Order order = new Order();
        order.setCustomer(customer);
        order.setOrderDate(LocalDateTime.now());
        order.setStatus("Placed .....");

        // create variables
        double totalAmount = 0.0;
        List<OrderItem> orderItems = new ArrayList<>();

        //Loop Through Request Products

        for(OrderItemRequestDTO itemRequest: request.getItems())
        {
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(()->
                            new RuntimeException("product not found ...."));


            if (product.getActive() != null && !product.getActive()) {
                throw new RuntimeException("Product is inactive.");
            }

            if (product.getStock() < itemRequest.getQuantity()) {

                throw new RuntimeException("Insufficient stock for product: "
                        + product.getName());
            }
            // Reduce Stock
            product.setStock(product.getStock() - itemRequest.getQuantity());

            productRepository.save(product);

            // Create OrderItems

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setProduct(product);
            orderItem.setQty(itemRequest.getQuantity());
            orderItem.setPrice(product.getPrice());

            orderItem.setOrder(order);

            //calculate total

            totalAmount  += product.getPrice() * itemRequest.getQuantity();

            orderItems.add(orderItem);
        }

        // Set Order Details
        order.setTotalAmount(totalAmount);
        order.setOrderItems(orderItems);

        // save order
        order  = orderRepository.save(order);


        // Create Payment
        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setAmount(totalAmount);
        payment.setPaymentType(request.getPaymentType());
        payment.setStatus("Success");
        payment.setLocalDateTime(LocalDateTime.now());

        // save payment
        paymentRepository.save(payment);

        return OrderResponseDTO.builder()
                .orderId(order.getId())
                .totalAmount(totalAmount)
                .status(order.getStatus())
                .build();
    }


}
