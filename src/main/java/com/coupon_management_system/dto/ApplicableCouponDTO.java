package com.coupon_management_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplicableCouponDTO {
    private Long couponId;
    private String type;
    private Long totalDiscount;
}