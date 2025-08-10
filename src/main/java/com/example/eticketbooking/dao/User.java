package com.example.eticketbooking.dao;


import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;

/**
 * Entity class representing a User Master in the e-ticket booking system.
 * @author Sarat
 */
@Entity
@Table(name = "users")
@Data
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;

    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    @Column(name = "password_hash")
    private String passwordHash;

    private String address;

    @Column(name = "user_type")
    private String userType;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

    @Column(name = "last_login")
    private OffsetDateTime lastLogin;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "is_verified")
    private Boolean isVerified;

    @Column(name = "is_locked")
    private Boolean isLocked;

    @Column(name = "failed_login_attempts")
    private Integer failedLoginAttempts;

    @Column(name = "last_failed_login")
    private OffsetDateTime lastFailedLogin;
    // populate userid with value from email
    @PrePersist
    public void prePersist() {
        if (userId == null || userId.isEmpty()) {
            this.userId = email;
        }
    }
}

