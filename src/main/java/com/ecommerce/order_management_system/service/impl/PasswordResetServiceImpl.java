package com.ecommerce.order_management_system.service.impl;


import com.ecommerce.order_management_system.dto.ForgotPasswardRequestDTO;
import com.ecommerce.order_management_system.dto.ResetPasswordRequestDTO;
import com.ecommerce.order_management_system.entity.PasswordResetToken;
import com.ecommerce.order_management_system.entity.User;
import com.ecommerce.order_management_system.exception.ResourceNotFoundException;
import com.ecommerce.order_management_system.repo.PasswordResetTokenRepository;
import com.ecommerce.order_management_system.repo.UserRepository;
import com.ecommerce.order_management_system.service.EmailService;
import com.ecommerce.order_management_system.service.PasswordResetService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PasswordResetServiceImpl implements PasswordResetService {

    private final UserRepository userRepository;
    private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;

    @Override
    public void forgotPassword(ForgotPasswardRequestDTO request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found"));

        // Remove any previous reset token
        passwordResetTokenRepository.deleteByUser_Id(user.getId());

        // Generate new token
        String token = UUID.randomUUID().toString();

        PasswordResetToken resetToken = PasswordResetToken.builder()
                .token(token)
                .user(user)
                .expiryDate(LocalDateTime.now().plusMinutes(15))
                .build();

        passwordResetTokenRepository.save(resetToken);

        // send Email
        emailService.sendPasswordResetEmail(user.getEmail(), token);
    }

    @Override
    public void resetPassword(ResetPasswordRequestDTO request) {

        PasswordResetToken resetToken =
                passwordResetTokenRepository.findByToken(request.getToken())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Invalid password reset token"));

        if (resetToken.getExpiryDate()
                .isBefore(LocalDateTime.now())) {

            passwordResetTokenRepository.delete(resetToken);

            throw new RuntimeException(
                    "Password reset token has expired");
        }

        User user = resetToken.getUser();

        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);

        // Token can no longer be reused
        passwordResetTokenRepository.delete(resetToken);
    }
}
