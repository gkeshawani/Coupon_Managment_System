package com.coupon_management_system.respository;

import com.coupon_management_system.domain.CartWiseCouponDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartWiseCouponDetailsRepository extends JpaRepository<CartWiseCouponDetails, Long> {
}