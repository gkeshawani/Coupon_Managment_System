package com.coupon_management_system.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CouponResponse {
    private Long id;
    private String type;
    private String status;
    private String description;
}
