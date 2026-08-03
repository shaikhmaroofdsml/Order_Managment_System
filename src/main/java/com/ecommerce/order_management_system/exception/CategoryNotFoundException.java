package com.ecommerce.order_management_system.exception;

public class CategoryNotFoundException extends RuntimeException{

    public CategoryNotFoundException(String message)
    {
        super(message);
    }
}
