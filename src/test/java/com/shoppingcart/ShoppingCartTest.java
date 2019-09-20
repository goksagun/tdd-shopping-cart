package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShoppingCartTest {

    @Test
    void addItem() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 100, food);

        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        assertEquals(2, cart.getCartItems().size());
    }

    @Test
    void getCampaignDiscount() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 150, food);

        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        // discount rules can be 20% on a category if bought more than 3 items.
        Campaign campaign1 = new Campaign(food, 20.0, 3, DiscountType.Rate.toString());
        // discount rules can be 50% on a category if bought more than 5 items.
        Campaign campaign2 = new Campaign(food, 50.0, 5, DiscountType.Rate.toString());
        // discount rules can be 5 TL on a category if bought more than items.
        Campaign campaign3 = new Campaign(food, 5.0, 1, DiscountType.Amount.toString());

        // apply campaigns to cart
        cart.applyDiscounts(campaign1, campaign2, campaign3);

        // applicable campaign1: [(100 * 3) + (150 * 1)] * 0.2 = 60 + 30 = 90 ### THE BEST VALUE ###
        // not applicable rules not matching for campaign2: 0
        // applicable campaign3: 5 * 4 = 20
        assertEquals(90, cart.getCampaignDiscount());
    }

    @Test
    void getCouponDiscount() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 150, food);

        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Coupon coupon = new Coupon(100.0, 10.0, DiscountType.Rate.toString());

        cart.applyCoupon(coupon);

        assertEquals(45, cart.getCouponDiscount());
    }

    @Test
    void getCouponDiscountAfterCampaignDiscount() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 150, food);

        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Campaign campaign1 = new Campaign(food, 20.0, 3, DiscountType.Rate.toString());
        Campaign campaign2 = new Campaign(food, 50.0, 5, DiscountType.Rate.toString());
        Campaign campaign3 = new Campaign(food, 5.0, 1, DiscountType.Amount.toString());

        cart.applyDiscounts(campaign1, campaign2, campaign3);

        Coupon coupon = new Coupon(100.0, 10.0, DiscountType.Rate.toString());

        cart.applyCoupon(coupon);

        assertEquals(36, cart.getCouponDiscount());
    }

    @Test
    void getTotalAmountAfterDiscount() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 150, food);

        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Campaign campaign1 = new Campaign(food, 20.0, 3, DiscountType.Rate.toString());
        Campaign campaign2 = new Campaign(food, 50.0, 5, DiscountType.Rate.toString());
        Campaign campaign3 = new Campaign(food, 5.0, 1, DiscountType.Amount.toString());

        cart.applyDiscounts(campaign1, campaign2, campaign3);

        Coupon coupon = new Coupon(100.0, 10.0, DiscountType.Rate.toString());

        cart.applyCoupon(coupon);

        assertEquals(324, cart.getTotalAmountAfterDiscount());
    }

    @Test
    void getDeliveryCost() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 100, food);

        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        assertEquals(6.96, cart.getDeliveryCost());
    }

    @Test
    void print() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 150, food);

        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        Campaign campaign1 = new Campaign(food, 20.0, 3, DiscountType.Rate.toString());
        Campaign campaign2 = new Campaign(food, 50.0, 5, DiscountType.Rate.toString());
        Campaign campaign3 = new Campaign(food, 5.0, 1, DiscountType.Amount.toString());

        cart.applyDiscounts(campaign1, campaign2, campaign3);

        Coupon coupon = new Coupon(100.0, 10.0, DiscountType.Rate.toString());

        cart.applyCoupon(coupon);

        assertEquals(
                "Food, Apple, 3, 100.0, 300.0\n" +
                        "Food, Almonds, 1, 150.0, 150.0\n" +
                        "Total Discount: 126.0\n" +
                        "Delivery Cost: 6.96\n" +
                        "Total Amount: 324.0\n",
                cart.print()
                    );
    }
}