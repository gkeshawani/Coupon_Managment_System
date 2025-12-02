package com.coupon_management_system.dto;

import lombok.Data;

@Data
public class CartWiseDetailsDTO {
    private Long thresholdAmount;
    private String mode;         // "PERCENTAGE" or "FIXED_AMOUNT"
    private Long discountValue;
}
