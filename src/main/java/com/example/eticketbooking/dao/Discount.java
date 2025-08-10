package com.example.eticketbooking.dao;

import jakarta.persistence.*;
import lombok.Data;

import java.time.OffsetDateTime;
import java.math.BigDecimal;

/**
 * Entity class representing a Discount in the e-ticket booking system.
 *
 * @author Sarat
 */
@Entity
@Table(name = "DISCOUNTS")
@Data
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "discount_id")
    private Integer discountId;

    @Column(name = "discount_name", nullable = false)
    private String discountName;

    private String description;

    @Column(name = "discount_type", nullable = false)
    private String discountType;

    @Column(name = "discount_value", nullable = false)
    private BigDecimal discountValue;

    @Column(columnDefinition = "jsonb")
    private String criteria;

    @Column(name = "is_active")
    private Boolean isActive;

    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}

