package com.flight.flight_api.entity;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

// 用户信息表定义
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    // 用户id, 自动赋值
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    // 邮箱
    @Column(nullable = false, length = 100)
    private String email;

    // 密码
    @Column(unique = true)
    private String password;

    // 用户名
    @Column(nullable = false, length = 50)
    private String firstName;

    // 用户名
    @Column(nullable = false, length = 50)
    private String lastName;

    // 国家
    @Column(nullable = false, length = 100)
    private String country;

    // 手机号
    @Column(nullable = true, length = 11)
    private String phone;

    // 航班订单信息
    @OneToMany(mappedBy = "user")
    @JsonManagedReference
    private Set<BookingEntity> bookings;

    // 用户注册时使用
    public UserEntity(String email, String password, String firstName, String lastName, String country,
            String phone) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.country = country;
        this.phone = phone;
    }
}