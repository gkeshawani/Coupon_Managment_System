package com.coupon_management_system.services;

import com.coupon_management_system.domain.CartWiseCouponDetails;
import com.coupon_management_system.domain.Coupon;
import com.coupon_management_system.domain.ProductWiseCouponDetails;
import com.coupon_management_system.domain.enums.CouponStatus;
import com.coupon_management_system.domain.enums.CouponType;
import com.coupon_management_system.domain.enums.DiscountMode;
import com.coupon_management_system.dto.CartWiseDetailsDTO;
import com.coupon_management_system.dto.CouponResponse;
import com.coupon_management_system.dto.CreateCouponRequest;
import com.coupon_management_system.dto.ProductWiseDetailsDTO;
import com.coupon_management_system.exception.CouponNotFoundException;
import com.coupon_management_system.respository.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final CouponRepository couponRepository;

    @Override
    public CouponResponse createCoupon(CreateCouponRequest request) {
        CouponType type = CouponType.valueOf(request.getType());

        Coupon coupon = Coupon.builder()
                .type(type)
                .status(CouponStatus.ACTIVE)
                .description("Test coupon")
                .startAt(LocalDateTime.now().minusMinutes(1))
                .endAt(LocalDateTime.now().plusDays(30))
                .build();

        switch (type) {
            case CART_WISE -> {
                CartWiseDetailsDTO dto = request.getCartWiseDetails();
                CartWiseCouponDetails d = CartWiseCouponDetails.builder()
                        .coupon(coupon)
                        .thresholdAmount(dto.getThresholdAmount())
                        .mode(DiscountMode.valueOf(dto.getMode()))
                        .discountValue(dto.getDiscountValue())
                        .build();
                coupon.setCartWiseDetails(d);
            }
            case PRODUCT_WISE -> {
                ProductWiseDetailsDTO dto = request.getProductWiseDetails();
                ProductWiseCouponDetails d = ProductWiseCouponDetails.builder()
                        .coupon(coupon)
                        .eligibleProductIds(
                                Arrays.stream(dto.getEligibleProductIds()).collect(java.util.stream.Collectors.toSet())
                        )
                        .mode(DiscountMode.valueOf(dto.getMode()))
                        .discountValue(dto.getDiscountValue())
                        .build();
                coupon.setProductWiseDetails(d);
            }
            case BXGY -> {
                // TODO: implement BxGy details
            }
        }

        Coupon saved = couponRepository.save(coupon);
        return toResponse(saved);
    }

    @Override
    public List<CouponResponse> getAllCoupons() {
        return couponRepository.findAll().stream()
                .map(this::toResponse)
                .toList();
    }

    @Override
    public CouponResponse getCoupon(Long id) {
        Coupon coupon = couponRepository.findById(id)
                .orElseThrow(() -> new CouponNotFoundException(id));
        return toResponse(coupon);
    }

    private CouponResponse toResponse(Coupon coupon) {
        return CouponResponse.builder()
                .id(coupon.getId())
                .type(coupon.getType().name())
                .status(coupon.getStatus().name())
                .description(coupon.getDescription())
                .build();
    }
}
