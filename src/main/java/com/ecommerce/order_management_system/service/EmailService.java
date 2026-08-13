package com.ecommerce.order_management_system.service;

public interface EmailService {

    void sendPasswordResetEmail(String to, String token);

    void sendVerificationEmail(String to, String verificationLink);
}
