package com.coupon_management_system.dto;

import lombok.Data;

import java.util.List;

@Data
public class ApplicableCouponsResponse {
    private List<ApplicableCouponDTO> applicableCoupons;
}
