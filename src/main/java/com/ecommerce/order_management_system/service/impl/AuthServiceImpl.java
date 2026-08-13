package com.ecommerce.order_management_system.service.impl;

import com.ecommerce.order_management_system.dto.AuthResponse;
import com.ecommerce.order_management_system.dto.LoginRequest;
import com.ecommerce.order_management_system.dto.UserRegisterRequestDTO;
import com.ecommerce.order_management_system.entity.Role;
import com.ecommerce.order_management_system.entity.User;
import com.ecommerce.order_management_system.exception.DuplicateResourceException;
import com.ecommerce.order_management_system.repo.UserRepository;
import com.ecommerce.order_management_system.security.JwtService;
import com.ecommerce.order_management_system.service.AuthService;
import com.ecommerce.order_management_system.service.EmailVerificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final JwtService jwtService;
    private final UserRepository userRepository;  // for User registration
    private final PasswordEncoder passwordEncoder; // for User registration
    private final EmailVerificationService emailVerificationService;



    // User Registration

    @Override
    public AuthResponse register(UserRegisterRequestDTO request) {

        System.out.println("STEP 1 - Registration started");

        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Email already exists");
        }

        System.out.println("STEP 2 - Email available");

        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.CUSTOMER)
                .enabled(false)
                .build();

        System.out.println("STEP 3 - User object created");

        userRepository.save(user);

        System.out.println("STEP 4 - User saved");

        emailVerificationService.sendVerificationEmail(user.getId());

        System.out.println("STEP 5 - Verification email sent");

        return AuthResponse.builder()
                .message("Registration successful. Please verify your email.")
                .build();
    }

//    @Override
//    public AuthResponse register(UserRegisterRequestDTO request) {
//
//        if (userRepository.findByEmail(request.getEmail()).isPresent()) {
//            throw new DuplicateResourceException("Email already exists");
//        }
//
//        User user = User.builder()
//                .name(request.getName())
//                .email(request.getEmail())
//                .password(passwordEncoder.encode(request.getPassword()))
//                .role(Role.CUSTOMER)
//                .enabled(false)
//                .build();
//
//        userRepository.save(user);
//        System.out.println("USER SAVED: " + user.getEmail());
//
//
//        emailVerificationService.sendVerificationEmail(user.getId());
//
//        System.out.println("VERIFICATION EMAIL SENT");
//
//        return AuthResponse.builder()
//                .message("Registration successful. Please verify your email.")
//                .build();
//    }

//    @Override
//    public AuthResponse register(UserRegisterRequestDTO registerRequestDTO) {
//        // check email valid or not.
//        if(userRepository.findByEmail(registerRequestDTO.getEmail()).isPresent())
//        {
//            throw new DuplicateResourceException("Email already exists");
//        }
//        // User registration .
//
//        User user = User.builder()
//                .name(registerRequestDTO.getName())
//                .email(registerRequestDTO.getEmail())
//                .password(passwordEncoder.encode(registerRequestDTO.getPassword()))
//                .role(Role.CUSTOMER)
//                .enabled(false)
//                .build();
//
//        userRepository.save(user);
//
//        emailVerificationService.sendVerificationEmail(user.getId());
//
//        String registrationToken = jwtService.generateToken(
//                userDetailsService.loadUserByUsername(user.getEmail()));
//
//
//        return AuthResponse.builder()
//                .token(registrationToken)
//                .build();
//    }


    //    //  User registration end

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
//  AuthenticationManager -> CustomUserDetailsService -> Validate Email & Password -> JwtService.generateToken() -> Return JWT Token