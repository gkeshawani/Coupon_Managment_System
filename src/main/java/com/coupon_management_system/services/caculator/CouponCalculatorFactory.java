package com.coupon_management_system.services.caculator;

import com.coupon_management_system.domain.enums.CouponType;
import org.springframework.stereotype.Component;
import java.util.*;

@Component
public class CouponCalculatorFactory {

    private final Map<CouponType, CouponCalculator> registry = new EnumMap<>(CouponType.class);

    public CouponCalculatorFactory(
            CartWiseCouponCalculator cartWiseCouponCalculator,
            ProductWiseCouponCalculator productWiseCouponCalculator
    ) {
        registry.put(CouponType.CART_WISE, cartWiseCouponCalculator);
        registry.put(CouponType.PRODUCT_WISE, productWiseCouponCalculator);
        // BXGY can be added later
    }

    public CouponCalculator getCalculator(CouponType type) {
        return registry.get(type);
    }
}