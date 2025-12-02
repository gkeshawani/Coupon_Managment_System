package com.coupon_management_system.services;

import com.coupon_management_system.domain.Coupon;
import com.coupon_management_system.domain.enums.CouponStatus;
import com.coupon_management_system.dto.ApplicableCouponDTO;
import com.coupon_management_system.dto.CartDTO;
import com.coupon_management_system.dto.UpdatedCartDTO;
import com.coupon_management_system.dto.UpdatedCartItemDTO;
import com.coupon_management_system.exception.CouponNotApplicableException;
import com.coupon_management_system.exception.CouponNotFoundException;
import com.coupon_management_system.respository.CouponRepository;
import com.coupon_management_system.services.caculator.CouponCalculator;
import com.coupon_management_system.services.caculator.CouponCalculatorFactory;
import com.coupon_management_system.services.caculator.CouponDiscountResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class CouponApplicationServiceImpl implements CouponApplicationService{


    private final CouponRepository couponRepository;
    private final CouponCalculatorFactory calculatorFactory;

    @Override
    public List<ApplicableCouponDTO> getApplicableCoupons(CartDTO cart) {
        LocalDateTime now = LocalDateTime.now();
        List<Coupon> activeCoupons = couponRepository
                .findByStatusAndStartAtBeforeAndEndAtAfter(CouponStatus.ACTIVE, now, now);

        return activeCoupons.stream()
                .map(coupon -> evaluateCoupon(coupon, cart))
                .filter(dto -> dto != null && dto.getTotalDiscount() > 0)
                .toList();
    }

    private ApplicableCouponDTO evaluateCoupon(Coupon coupon, CartDTO cart) {
        CouponCalculator calculator = calculatorFactory.getCalculator(coupon.getType());
        if (calculator == null) return null;

        if (!calculator.isApplicable(coupon, cart)) return null;

        CouponDiscountResult result = calculator.calculateDiscount(coupon, cart);

        return ApplicableCouponDTO.builder()
                .couponId(coupon.getId())
                .type(coupon.getType().name())
                .totalDiscount(result.getTotalDiscount())
                .build();
    }

    @Override
    public UpdatedCartDTO applyCoupon(Long couponId, CartDTO cart) {
        Coupon coupon = couponRepository.findById(couponId)
                .orElseThrow(() -> new CouponNotFoundException(couponId));

        LocalDateTime now = LocalDateTime.now();
        if (coupon.getStatus() != CouponStatus.ACTIVE ||
                coupon.getStartAt().isAfter(now) ||
                coupon.getEndAt().isBefore(now)) {
            throw new CouponNotApplicableException("Coupon is not active or expired");
        }

        CouponCalculator calculator = calculatorFactory.getCalculator(coupon.getType());
        if (calculator == null || !calculator.isApplicable(coupon, cart)) {
            throw new CouponNotApplicableException("Coupon conditions not satisfied");
        }

        CouponDiscountResult result = calculator.calculateDiscount(coupon, cart);

        long totalPrice = cart.getItems().stream()
                .mapToLong(i -> i.getUnitPrice() * i.getQuantity())
                .sum();

        long finalPrice = totalPrice - result.getTotalDiscount();

        List<UpdatedCartItemDTO> updatedItems = cart.getItems().stream()
                .map(i -> UpdatedCartItemDTO.builder()
                        .productId(i.getProductId())
                        .quantity(i.getQuantity())
                        .unitPrice(i.getUnitPrice())
                        .totalDiscount(result.getItemDiscounts().stream()
                                .filter(d -> d.getProductId().equals(i.getProductId()))
                                .mapToLong(CouponDiscountResult.ItemDiscount::getDiscountAmount)
                                .sum())
                        .build())
                .toList();

        return UpdatedCartDTO.builder()
                .items(updatedItems)
                .totalPrice(totalPrice)
                .totalDiscount(result.getTotalDiscount())
                .finalPrice(finalPrice)
                .build();
    }
}
