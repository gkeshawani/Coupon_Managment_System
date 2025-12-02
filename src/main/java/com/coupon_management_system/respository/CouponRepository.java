package com.coupon_management_system.respository;

import com.coupon_management_system.domain.Coupon;
import com.coupon_management_system.domain.enums.CouponStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    List<Coupon> findByStatusAndStartAtBeforeAndEndAtAfter(
            CouponStatus status,
            LocalDateTime now1,
            LocalDateTime now2
    );
}
