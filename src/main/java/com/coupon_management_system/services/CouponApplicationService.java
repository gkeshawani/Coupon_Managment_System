package com.coupon_management_system.services;

import com.coupon_management_system.dto.ApplicableCouponDTO;
import com.coupon_management_system.dto.CartDTO;
import com.coupon_management_system.dto.UpdatedCartDTO;

import java.util.List;

public interface CouponApplicationService {
    List<ApplicableCouponDTO> getApplicableCoupons(CartDTO cart);
    UpdatedCartDTO applyCoupon(Long couponId, CartDTO cart);
}