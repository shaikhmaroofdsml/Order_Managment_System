package com.ecommerce.order_management_system.repo;

import com.ecommerce.order_management_system.entity.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefereshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    void deleteByUser_Id(Long userId);
}
