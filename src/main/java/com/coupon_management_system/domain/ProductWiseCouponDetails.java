package com.coupon_management_system.domain;

import com.coupon_management_system.domain.enums.DiscountMode;
import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "product_wise_coupon_details")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductWiseCouponDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "coupon_id", nullable = false)
    private Coupon coupon;

    @ElementCollection
    @CollectionTable(name = "product_wise_eligible_products", joinColumns = @JoinColumn(name = "details_id"))
    @Column(name = "product_id")
    private Set<Long> eligibleProductIds;

    @Enumerated(EnumType.STRING)
    private DiscountMode mode;

    private Long discountValue;
}