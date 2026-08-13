package com.ecommerce.order_management_system.repo;

import com.ecommerce.order_management_system.entity.EmailVerificationToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailVerificationTokenRepository  extends JpaRepository<EmailVerificationToken, Long> {

    Optional<EmailVerificationToken> findByToken(String token);

    void deleteByUser_Id(Long userId);
}
