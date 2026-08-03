package com.ecommerce.order_management_system.exception;

public class CustomerNotFoundException extends RuntimeException{

    public CustomerNotFoundException(String message)
    {
        super(message);
    }
}
