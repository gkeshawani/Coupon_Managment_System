package com.coupon_management_system.dto;

import lombok.Data;

@Data
public class CreateCouponRequest {
    private String type; // "CART_WISE", "PRODUCT_WISE", "BXGY"

    private CartWiseDetailsDTO cartWiseDetails;
    private ProductWiseDetailsDTO productWiseDetails;
}

