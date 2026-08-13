package com.ecommerce.order_management_system.dto;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ForgotPasswardRequestDTO {

    @NotBlank(message = "Email is required....")
    @Email(message = "Invalid email")
    private String email;
}
