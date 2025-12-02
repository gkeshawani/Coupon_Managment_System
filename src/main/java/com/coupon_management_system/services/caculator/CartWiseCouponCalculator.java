package com.coupon_management_system.services.caculator;

import com.coupon_management_system.domain.CartWiseCouponDetails;
import com.coupon_management_system.domain.Coupon;
import com.coupon_management_system.domain.enums.DiscountMode;
import com.coupon_management_system.dto.CartDTO;
import com.coupon_management_system.dto.CartItemDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class CartWiseCouponCalculator implements CouponCalculator {

    @Override
    public boolean isApplicable(Coupon coupon, CartDTO cart) {

        if (coupon == null || coupon.getCartWiseDetails() == null) {
            return false;
        }

        if (cart == null || cart.getItems() == null || cart.getItems().isEmpty()) {
            return false;
        }

        long cartTotal = cart.getItems().stream()
                .filter(Objects::nonNull)
                .mapToLong(i -> i.getUnitPrice() * i.getQuantity())
                .sum();

        return cartTotal >= coupon.getCartWiseDetails().getThresholdAmount();
    }

    @Override
    public CouponDiscountResult calculateDiscount(Coupon coupon, CartDTO cart) {

        CartWiseCouponDetails details = coupon.getCartWiseDetails();

        long cartTotal = cart.getItems().stream()
                .filter(Objects::nonNull)
                .mapToLong(i -> i.getUnitPrice() * i.getQuantity())
                .sum();

        long discount;
        if (details.getMode() == DiscountMode.PERCENTAGE) {
            discount = cartTotal * details.getDiscountValue() / 100;
        } else {
            discount = Math.min(details.getDiscountValue(), cartTotal);
        }

        List<CouponDiscountResult.ItemDiscount> itemDiscounts = new ArrayList<>();

        for (CartItemDTO item : cart.getItems()) {
            if (item == null || item.getUnitPrice() == null || item.getProductId() == null) {
                continue;
            }

            long lineTotal = item.getUnitPrice() * item.getQuantity();
            long lineDiscount = cartTotal == 0 ? 0 : discount * lineTotal / cartTotal;

            itemDiscounts.add(
                    CouponDiscountResult.ItemDiscount.builder()
                            .productId(item.getProductId())
                            .discountAmount(lineDiscount)
                            .extraFreeQuantity(0)
                            .build()
            );
        }

        return CouponDiscountResult.builder()
                .totalDiscount(discount)
                .itemDiscounts(itemDiscounts)
                .build();
    }
}
