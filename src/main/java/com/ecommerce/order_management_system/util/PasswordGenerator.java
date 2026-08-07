package com.ecommerce.order_management_system.util;

import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class PasswordGenerator implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        BCryptPasswordEncoder encode = new BCryptPasswordEncoder();
        System.out.println(encode.encode("admin@123"));
    }

//    @Override
//    public void run(String... args) {
//
//        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//
//        String password = "admin123";
//
//        String hash = encoder.encode(password);
//
//        System.out.println("Password : " + password);
//        System.out.println("Hash : " + hash);
//        System.out.println("Match : " + encoder.matches(password, hash));
//
//    }
}
