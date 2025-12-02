package com.coupon_management_system.domain;

import com.coupon_management_system.domain.enums.DiscountMode;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "cart_wise_coupon_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartWiseCouponDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    private Long thresholdAmount; // in paise

    @Enumerated(EnumType.STRING)
    private DiscountMode mode;

    private Long discountValue;   // percentage (10) or fixed amount (10000)
}
