package com.coupon_management_system.services;

import com.coupon_management_system.dto.CouponResponse;
import com.coupon_management_system.dto.CreateCouponRequest;

import java.util.List;

public interface CouponService {
    CouponResponse createCoupon(CreateCouponRequest request);
    List<CouponResponse> getAllCoupons();
    CouponResponse getCoupon(Long id);
}
