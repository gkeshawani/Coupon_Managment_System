package com.coupon_management_system.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(CouponNotFoundException.class)
    public ResponseEntity<?> handleNotFound(CouponNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(error("COUPON_NOT_FOUND", ex.getMessage()));
    }

    @ExceptionHandler(CouponNotApplicableException.class)
    public ResponseEntity<?> handleNotApplicable(CouponNotApplicableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(error("COUPON_NOT_APPLICABLE", ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleOther(Exception ex) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(error("INTERNAL_ERROR", ex.getMessage()));
    }

    private Map<String, Object> error(String code, String message) {
        return Map.of(
                "timestamp", LocalDateTime.now().toString(),
                "code", code,
                "message", message
        );
    }
}