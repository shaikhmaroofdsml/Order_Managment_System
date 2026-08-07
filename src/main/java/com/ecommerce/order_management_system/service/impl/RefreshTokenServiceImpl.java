package com.ecommerce.order_management_system.service.impl;


import com.ecommerce.order_management_system.entity.RefreshToken;
import com.ecommerce.order_management_system.entity.User;
import com.ecommerce.order_management_system.exception.ResourceNotFoundException;
import com.ecommerce.order_management_system.repo.RefereshTokenRepository;
import com.ecommerce.order_management_system.service.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshTokenServiceImpl implements RefreshTokenService {

    private final RefereshTokenRepository refereshTokenRepository;

    @Override
    public RefreshToken createRefreshToken(User user) {
        RefreshToken refreshToken = RefreshToken.builder()
                .token(UUID.randomUUID().toString())
                .user(user)
                .expiryDate(LocalDateTime.now().plusDays(7))
                .build();
        return refereshTokenRepository.save(refreshToken);

    }

    @Override
    public RefreshToken verifyRefreshToken(String token) {

        RefreshToken refreshToken = refereshTokenRepository.findByToken(token)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Refresh token not found"));

        if (refreshToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            refereshTokenRepository.delete(refreshToken);
            throw new RuntimeException("Refresh token expired");
        }

        return refreshToken;
    }

    @Override
    public void DeleteByUser(Long userId) {
        refereshTokenRepository.deleteByUser_Id(userId);
    }
}
