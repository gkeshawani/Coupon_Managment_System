package com.coupon_management_system.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
public class ApplyCouponRequest {
    private CartDTO cart;
}

