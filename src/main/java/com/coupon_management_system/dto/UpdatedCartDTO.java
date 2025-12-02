package com.coupon_management_system.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UpdatedCartDTO {
    private List<UpdatedCartItemDTO> items;
    private Long totalPrice;
    private Long totalDiscount;
    private Long finalPrice;
}
