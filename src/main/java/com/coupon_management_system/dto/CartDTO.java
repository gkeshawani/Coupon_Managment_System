package com.coupon_management_system.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.*;

@Data
public class CartDTO {
    private List<CartItemDTO> items;
}
