package com.ecommerce.order_management_system.controller;


import com.ecommerce.order_management_system.dto.AuthResponse;
import com.ecommerce.order_management_system.dto.LoginRequest;
import com.ecommerce.order_management_system.dto.UserRegisterRequestDTO;
import com.ecommerce.order_management_system.service.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    //  Testing
//    @PostMapping("/login")
//    public String login()
//    {
//        return "Login API working.....";
//    }


    private final AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request)
    {
        System.out.println("Controller working fine .....");
        return ResponseEntity.ok(authService.login(request));

    }

    @PostMapping("/register")
    public  ResponseEntity<AuthResponse> register(@Valid @RequestBody UserRegisterRequestDTO registerRequestDTO)
    {
        return  ResponseEntity.status(HttpStatus.CREATED).body(authService.register(registerRequestDTO));

    }
}
