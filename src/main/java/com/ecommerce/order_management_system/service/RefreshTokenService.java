package com.ecommerce.order_management_system.service;

import com.ecommerce.order_management_system.entity.RefreshToken;
import com.ecommerce.order_management_system.entity.User;

public interface RefreshTokenService {

    RefreshToken createRefreshToken(User user);

    RefreshToken verifyRefreshToken(String token);

    void DeleteByUser(Long userId);
}
