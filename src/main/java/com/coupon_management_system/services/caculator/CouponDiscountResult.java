package com.coupon_management_system.services.caculator;


import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CouponDiscountResult {
    private Long totalDiscount;
    private List<ItemDiscount> itemDiscounts;

    @Data
    @Builder
    public static class ItemDiscount {
        private Long productId;
        private Long discountAmount;
        private Integer extraFreeQuantity; // for BxGy, not used now
    }
}