package com.coupon_management_system.services.caculator;

import com.coupon_management_system.domain.Coupon;
import com.coupon_management_system.domain.ProductWiseCouponDetails;
import com.coupon_management_system.domain.enums.DiscountMode;
import com.coupon_management_system.dto.CartDTO;
import com.coupon_management_system.dto.CartItemDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class ProductWiseCouponCalculator implements CouponCalculator {

    @Override
    public boolean isApplicable(Coupon coupon, CartDTO cart) {

        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            return false;
        }

        ProductWiseCouponDetails details = coupon.getProductWiseDetails();
        if (details == null || details.getEligibleProductIds() == null) {
            return false;
        }

        return cart.getItems().stream()
                .filter(Objects::nonNull)
                .anyMatch(i -> details.getEligibleProductIds().contains(i.getProductId()));
    }

    @Override
    public CouponDiscountResult calculateDiscount(Coupon coupon, CartDTO cart) {

        ProductWiseCouponDetails details = coupon.getProductWiseDetails();
        List<CouponDiscountResult.ItemDiscount> itemDiscounts = new ArrayList<>();

        long totalDiscount = 0L;

        for (CartItemDTO item : cart.getItems()) {
            if (item == null || item.getProductId() == null || item.getUnitPrice() == null) {
                continue; // skip invalid items safely
            }

            if (!details.getEligibleProductIds().contains(item.getProductId())) {
                continue;
            }

            long lineTotal = item.getUnitPrice() * item.getQuantity();
            long lineDiscount;

            if (details.getMode() == DiscountMode.PERCENTAGE) {
                lineDiscount = lineTotal * details.getDiscountValue() / 100;
            } else {
                lineDiscount = Math.min(details.getDiscountValue(), lineTotal);
            }

            totalDiscount += lineDiscount;

            itemDiscounts.add(CouponDiscountResult.ItemDiscount.builder()
                    .productId(item.getProductId())
                    .discountAmount(lineDiscount)
                    .extraFreeQuantity(0)
                    .build());
        }

        return CouponDiscountResult.builder()
                .totalDiscount(totalDiscount)
                .itemDiscounts(itemDiscounts)
                .build();
    }
}
