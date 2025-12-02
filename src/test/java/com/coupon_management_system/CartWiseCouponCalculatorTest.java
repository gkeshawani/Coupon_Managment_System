package com.coupon_management_system;


import com.coupon_management_system.domain.CartWiseCouponDetails;
import com.coupon_management_system.domain.Coupon;
import com.coupon_management_system.domain.enums.CouponStatus;
import com.coupon_management_system.domain.enums.CouponType;
import com.coupon_management_system.domain.enums.DiscountMode;
import com.coupon_management_system.dto.CartDTO;
import com.coupon_management_system.dto.CartItemDTO;
import com.coupon_management_system.services.caculator.CartWiseCouponCalculator;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class CartWiseCouponCalculatorTest {

    @InjectMocks
    private CartWiseCouponCalculator calculator;

    @Test
    void testCartWiseDiscountApplicable() {
        // Create Cart
        CartDTO cart = new CartDTO();
        CartItemDTO item1 = new CartItemDTO();
        item1.setProductId(1L);
        item1.setQuantity(2);
        item1.setUnitPrice(60000L);

        cart.setItems(List.of(item1));

        // Create Coupon Details
        CartWiseCouponDetails details = CartWiseCouponDetails.builder()
                .thresholdAmount(100000L)
                .mode(DiscountMode.PERCENTAGE)
                .discountValue(10L)
                .build();

        Coupon coupon = Coupon.builder()
                .type(CouponType.CART_WISE)
                .status(CouponStatus.ACTIVE)
                .startAt(LocalDateTime.now().minusHours(1))
                .endAt(LocalDateTime.now().plusHours(1))
                .cartWiseDetails(details)
                .build();

        details.setCoupon(coupon);

        boolean isApplicable = calculator.isApplicable(coupon, cart);

        Assertions.assertTrue(isApplicable, "Coupon should be applicable");
    }
}
