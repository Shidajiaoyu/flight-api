package com.flight.flight_api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flight.flight_api.entity.UserEntity;

// 用户表操作
@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    // 检查邮箱是否已经被注册
    boolean existsByEmail(String email);

    // 获取指定邮箱的用户信息
    Optional<UserEntity> findByEmail(String email);
}
