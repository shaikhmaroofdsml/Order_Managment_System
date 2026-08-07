package com.ecommerce.order_management_system.service;

import com.ecommerce.order_management_system.dto.AuthResponse;
import com.ecommerce.order_management_system.dto.LoginRequest;
import com.ecommerce.order_management_system.dto.UserRegisterRequestDTO;

public interface AuthService {

    AuthResponse login(LoginRequest request);

    AuthResponse register(UserRegisterRequestDTO registerRequestDTO);
}
