package com.ecommerce.order_management_system.service;

public interface EmailVerificationService {

    void sendVerificationEmail(Long userId);

    void verifyEmail(String token);
}
