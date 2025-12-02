package com.coupon_management_system.exception;

public class CouponNotFoundException extends RuntimeException {
    public CouponNotFoundException(Long id) {
        super("Coupon not found: " + id);
    }
}