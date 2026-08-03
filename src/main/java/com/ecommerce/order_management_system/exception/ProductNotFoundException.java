package com.ecommerce.order_management_system.exception;

public class ProductNotFoundException extends  RuntimeException {

    public ProductNotFoundException(String message)
    {
        super(message);
    }


}
