package com.ecommerce.order_management_system.service;

import com.ecommerce.order_management_system.dto.AuthResponse;
import com.ecommerce.order_management_system.dto.LoginRequest;

public interface AuthService {

    AuthResponse login(LoginRequest request);
}
