package com.ecommerce.order_management_system.service;

import com.ecommerce.order_management_system.dto.ForgotPasswardRequestDTO;
import com.ecommerce.order_management_system.dto.ResetPasswordRequestDTO;

public interface PasswordResetService {

    void forgotPassword(ForgotPasswardRequestDTO request);

    void resetPassword(ResetPasswordRequestDTO request);
}
