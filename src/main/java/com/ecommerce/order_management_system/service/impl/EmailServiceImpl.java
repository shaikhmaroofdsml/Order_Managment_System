package com.ecommerce.order_management_system.service.impl;

import com.ecommerce.order_management_system.service.EmailService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EmailServiceImpl implements EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromEmail;

    @Override
    public void sendPasswordResetEmail(String to, String token) {
        String resetLink =
                "http://localhost:8080/api/auth/reset-password?token="
                        + token;

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom(fromEmail);
        message.setTo(to);
        message.setSubject("Password Reset Request");

        message.setText(
                "Hello,\n\n" +
                        "You requested a password reset.\n\n" +
                        "Click the link below to reset your password:\n" +
                        resetLink +
                        "\n\n" +
                        "This link will expire in 15 minutes.\n\n" +
                        "If you did not request this, please ignore this email."
        );

        mailSender.send(message);
    }

    @Override
    public void sendVerificationEmail(
            String to,
            String verificationLink) {

        SimpleMailMessage message = new SimpleMailMessage();

        message.setFrom("smaroof579@gmail.com");
        message.setTo(to);
        message.setSubject("Verify Your Email");

        message.setText(
                "Hello,\n\n" +
                        "Please verify your email address by clicking the link below:\n\n" +
                        verificationLink +
                        "\n\n" +
                        "This link will expire in 30 minutes."
        );

        mailSender.send(message);
    }

}
