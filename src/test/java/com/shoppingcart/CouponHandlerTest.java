package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CouponHandlerTest {

    @Test
    void handle() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");
        Category beverage = new Category("Beverage");

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 150, food);
        Product coke = new Product("Coke", 30, beverage);

        cart.addItem(apple, 1);
        cart.addItem(almond, 1);
        cart.addItem(coke, 5);

        // discount rules can be 20% on a category if bought more than 3 items.
        Campaign campaign1 = new Campaign(food, 20.0, 3, DiscountType.Rate.toString());
        // discount rules can be 50% on a category if bought more than 5 items.
        Campaign campaign2 = new Campaign(food, 50.0, 5, DiscountType.Rate.toString());
        // discount rules can be 5 TL on a category if bought more than items.
        Campaign campaign3 = new Campaign(food, 5.0, 1, DiscountType.Amount.toString());

        // apply campaigns to cart
        cart.applyDiscounts(campaign1, campaign2, campaign3);

        // coupon for 100 TL min purchase amount for a 10% discount
        Coupon coupon = new Coupon(100.0, 10.0, DiscountType.Rate.toString());

        CouponHandler couponHandler = new CouponHandler(cart, coupon);

        couponHandler.handle();

        assertEquals(39, cart.getCouponDiscount());
    }

    @Test
    void handleWithUnderMinAmount() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");
        Category beverage = new Category("Beverage");

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 150, food);

        cart.addItem(apple, 1);
        cart.addItem(almond, 1);

        Campaign campaign1 = new Campaign(food, 20.0, 3, DiscountType.Rate.toString());
        Campaign campaign2 = new Campaign(food, 50.0, 5, DiscountType.Rate.toString());
        Campaign campaign3 = new Campaign(food, 5.0, 1, DiscountType.Amount.toString());

        cart.applyDiscounts(campaign1, campaign2, campaign3);

        Coupon coupon = new Coupon(300.0, 50.0, DiscountType.Rate.toString());

        CouponHandler couponHandler = new CouponHandler(cart, coupon);

        couponHandler.handle();

        assertEquals(0, cart.getCouponDiscount());
    }
}