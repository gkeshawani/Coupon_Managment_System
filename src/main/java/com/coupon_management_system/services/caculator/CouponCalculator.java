package com.coupon_management_system.services.caculator;

import com.coupon_management_system.domain.Coupon;
import com.coupon_management_system.dto.CartDTO;

public interface CouponCalculator {
    boolean isApplicable(Coupon coupon, CartDTO cart);
    CouponDiscountResult calculateDiscount(Coupon coupon, CartDTO cart);
}