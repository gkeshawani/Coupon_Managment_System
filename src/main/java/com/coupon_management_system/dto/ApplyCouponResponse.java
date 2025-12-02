package com.coupon_management_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ApplyCouponResponse {
    private UpdatedCartDTO updatedCart;
}
