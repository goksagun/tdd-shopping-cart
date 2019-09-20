package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouponTest {

    @Test
    void getMinAmount() {
        Coupon coupon = new Coupon(150, 10, DiscountType.Rate.toString());

        assertEquals(150, coupon.getMinAmount());
    }

    @Test
    void setMinAmount() {
        Coupon coupon = new Coupon(150, 10, DiscountType.Rate.toString());

        coupon.setMinAmount(200);

        assertEquals(200, coupon.getMinAmount());
    }

    @Test
    void getAmount() {
        Coupon coupon = new Coupon(150, 10, DiscountType.Rate.toString());

        assertEquals(10, coupon.getAmount());
    }

    @Test
    void setAmount() {
        Coupon coupon = new Coupon(150, 10, DiscountType.Rate.toString());

        coupon.setAmount(30);

        assertEquals(30, coupon.getAmount());
    }

    @Test
    void getType() {
        Coupon coupon = new Coupon(150, 10, DiscountType.Rate.toString());

        assertEquals(DiscountType.Rate.toString(), coupon.getType());
    }

    @Test
    void setType() {
        Coupon coupon = new Coupon(150, 10, DiscountType.Rate.toString());

        coupon.setType(DiscountType.Amount.toString());

        assertEquals(DiscountType.Amount.toString(), coupon.getType());
    }
}