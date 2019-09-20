package com.shoppingcart;

/**
 * Coupon handler, handles coupon to cart.
 *
 * @author Burak Bolat (brkblt@gmail.com)
 */
public class CouponHandler implements HandlerI {
    private ShoppingCart cart;
    private Coupon coupon;

    public CouponHandler(ShoppingCart cart, Coupon coupon) {
        this.cart = cart;
        this.coupon = coupon;
    }

    /**
     * This handler is handles given coupon discount to cart.
     *
     * If cart total amount after campaign discount applied if exists and applicable, greater than coupon minimum amount
     * applies coupon to cart.
     */
    public void handle() {
        double couponDiscount = 0;

        if (this.cart.getTotalAmountAfterDiscount() >= coupon.getMinAmount()) {
            couponDiscount = this.cart.getTotalAmountAfterDiscount() * (coupon.getAmount() / 100);
        }

        this.cart.setCouponDiscount(couponDiscount);
    }
}
