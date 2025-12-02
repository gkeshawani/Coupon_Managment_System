package com.coupon_management_system.controller;

import com.coupon_management_system.dto.CouponResponse;
import com.coupon_management_system.dto.CreateCouponRequest;
import com.coupon_management_system.services.CouponService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
@RequiredArgsConstructor
public class CouponController {

    private final CouponService couponService;

    @PostMapping
    public CouponResponse create(@RequestBody CreateCouponRequest request) {
        return couponService.createCoupon(request);
    }

    @GetMapping
    public List<CouponResponse> list() {
        return couponService.getAllCoupons();
    }

    @GetMapping("/{id}")
    public CouponResponse get(@PathVariable Long id) {
        return couponService.getCoupon(id);
    }
}