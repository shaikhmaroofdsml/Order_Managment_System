package com.ecommerce.order_management_system.service.Impl;

import com.ecommerce.order_management_system.entity.User;
import com.ecommerce.order_management_system.repo.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.security.autoconfigure.SecurityProperties;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service

public class UserDetailsServiceImpl implements UserDetailsService {

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
        System.out.println("UserDetailsServiceImpl created");
    }

    private  final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("user not found..."));

        System.out.println("Email = " + user.getEmail());
        System.out.println("Password from DB = " + user.getPassword());
        System.out.println("Enabled = " + user.getEnabled());
        System.out.println("Role = " + user.getRole());

        return org.springframework.security.core.userdetails.User
                .builder()
                .username(user.getEmail())
                .password(user.getPassword())
                .roles(user.getRole().name())
                .disabled(!user.getEnabled())
                .build();
    }
}
