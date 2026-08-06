package com.ecommerce.order_management_system.service.Impl;

import com.ecommerce.order_management_system.dto.AuthResponse;
import com.ecommerce.order_management_system.dto.LoginRequest;
import com.ecommerce.order_management_system.security.JwtService;
import com.ecommerce.order_management_system.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {

//        System.out.println("Before authenticate");
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(
//                        request.getEmail(),
//                        request.getPassword()
//                )
//        );
//
//        System.out.println("After authenticate");

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    )
            );
        } catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
        UserDetails userDetails = userDetailsService
                .loadUserByUsername(request.getEmail());

        String token = jwtService.generateToken(userDetails);

        return AuthResponse.builder()
                .token(token)
                .build();

    }
}
//
//POST /api/auth/login
//        │
//                ▼
//AuthenticationManager
//        │
//                ▼
//CustomUserDetailsService
//        │
//                ▼
//Validate Email & Password
//        │
//                ▼
//                JwtService.generateToken()
//        │
//                ▼
//Return JWT Token