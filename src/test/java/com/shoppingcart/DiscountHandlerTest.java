package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DiscountHandlerTest {

    @Test
    void handle() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");
        Category electronic = new Category("Electronic");
        Category computer = new Category("Computer", electronic);

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 150, food);
        Product mac = new Product("MacBook Pro 15'", 15000, computer);

        cart.addItem(apple, 3);
        cart.addItem(almond, 2);
        cart.addItem(mac, 1);

        // discount rules can be 20% on a category if bought more than 3 items.
        Campaign campaign1 = new Campaign(food, 20.0, 3, DiscountType.Rate.toString());
        // discount rules can be 50% on a category if bought more than 5 items.
        Campaign campaign2 = new Campaign(food, 50.0, 5, DiscountType.Rate.toString());
        // discount rules can be 5 TL on a category if bought more than items.
        Campaign campaign3 = new Campaign(food, 5.0, 1, DiscountType.Amount.toString());
        // discount rules can be 500 TL on a category if bought more than items.
        Campaign campaign4 = new Campaign(computer, 500.0, 1, DiscountType.Amount.toString());

        DiscountHandler discountHandler = new DiscountHandler(cart, campaign1, campaign2, campaign3, campaign4);

        discountHandler.handle();

        // applicable campaign1: [(100 * 3) + (150 * 1)] * 0.2 = 60 + 30 = 90
        // applicable campaign2: [(100 * 3) + (150 * 2)] * 0.5 = 150 + 150 = 300
        // applicable campaign3: 5 * 4 = 20
        // applicable campaign3: 500 * 1 = 500 ### THE BEST VALUE ###
        assertEquals(500, cart.getCampaignDiscount());
    }
}