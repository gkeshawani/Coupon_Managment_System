package com.coupon_management_system.controller;

import com.coupon_management_system.dto.*;
import com.coupon_management_system.services.CouponApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.*;

@RestController
@RequiredArgsConstructor
public class CouponApplicationController {


    private final CouponApplicationService couponApplicationService;

    @PostMapping("/applicable-coupons")
    public ApplicableCouponsResponse applicableCoupons(@RequestBody ApplicableCouponsRequest request) {
        List<ApplicableCouponDTO> list =
                couponApplicationService.getApplicableCoupons(request.getCart());
        ApplicableCouponsResponse res = new ApplicableCouponsResponse();
        res.setApplicableCoupons(list);
        return res;
    }

    @PostMapping("/apply-coupon/{id}")
    public ApplyCouponResponse apply(@PathVariable Long id,
                                     @RequestBody ApplyCouponRequest request) {
        UpdatedCartDTO updatedCart =
                couponApplicationService.applyCoupon(id, request.getCart());
        return ApplyCouponResponse.builder()
                .updatedCart(updatedCart)
                .build();
    }
}

