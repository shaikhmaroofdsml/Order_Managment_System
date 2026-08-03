package com.ecommerce.order_management_system.exception;

public class OrderNotFoundException extends RuntimeException{


    public OrderNotFoundException(String message)
    {
        super(message);
    }
}
