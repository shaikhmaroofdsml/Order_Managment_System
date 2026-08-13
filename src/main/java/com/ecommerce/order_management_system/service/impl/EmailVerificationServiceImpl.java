package com.ecommerce.order_management_system.service.impl;

import com.ecommerce.order_management_system.entity.EmailVerificationToken;
import com.ecommerce.order_management_system.entity.User;
import com.ecommerce.order_management_system.exception.ResourceNotFoundException;
import com.ecommerce.order_management_system.repo.EmailVerificationTokenRepository;
import com.ecommerce.order_management_system.repo.UserRepository;
import com.ecommerce.order_management_system.service.EmailService;
import com.ecommerce.order_management_system.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailVerificationServiceImpl
        implements EmailVerificationService {

    private final UserRepository userRepository;
    private final EmailVerificationTokenRepository tokenRepository;
    private final EmailService emailService;

    @Override
    @Transactional
    public void sendVerificationEmail(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        tokenRepository.deleteByUser_Id(userId);

        String token = UUID.randomUUID().toString();

        EmailVerificationToken verificationToken =
                EmailVerificationToken.builder()
                        .token(token)
                        .user(user)
                        .expiryDate(
                                LocalDateTime.now().plusMinutes(30)
                        )
                        .build();

        tokenRepository.save(verificationToken);

        String verificationLink =
                "http://localhost:8080/api/auth/verify-email?token="
                        + token;

        emailService.sendVerificationEmail(
                user.getEmail(),
                verificationLink
        );
    }

    @Override
    @Transactional
    public void verifyEmail(String token) {

        EmailVerificationToken verificationToken =
                tokenRepository.findByToken(token)
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Invalid verification token"));

        if (verificationToken.getExpiryDate()
                .isBefore(LocalDateTime.now())) {

            tokenRepository.delete(verificationToken);

            throw new RuntimeException(
                    "Verification token has expired");
        }

        User user = verificationToken.getUser();

        user.setEnabled(true);

        userRepository.save(user);

        tokenRepository.delete(verificationToken);
    }
}