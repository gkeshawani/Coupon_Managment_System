package com.coupon_management_system.respository;

import com.coupon_management_system.domain.ProductWiseCouponDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductWiseCouponDetailsRepository extends JpaRepository<ProductWiseCouponDetails, Long> {
}
