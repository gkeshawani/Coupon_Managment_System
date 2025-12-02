package com.coupon_management_system.exception;

public class CouponNotApplicableException extends RuntimeException {
    public CouponNotApplicableException(String msg) {
        super(msg);
    }
}