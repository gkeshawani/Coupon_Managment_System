package com.coupon_management_system.domain;


import com.coupon_management_system.domain.enums.CouponStatus;
import com.coupon_management_system.domain.enums.CouponType;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "coupons")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CouponType type;

    @Enumerated(EnumType.STRING)
    private CouponStatus status;

    private String description;

    private LocalDateTime startAt;
    private LocalDateTime endAt;

    @OneToOne(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    private CartWiseCouponDetails cartWiseDetails;

    @OneToOne(mappedBy = "coupon", cascade = CascadeType.ALL, orphanRemoval = true)
    private ProductWiseCouponDetails productWiseDetails;

    // BxGy omitted for brevity – you can add similar details entity
}