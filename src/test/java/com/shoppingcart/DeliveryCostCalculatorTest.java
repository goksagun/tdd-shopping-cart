package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryCostCalculatorTest {

    @Test
    void calculateFor() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 100, food);

        cart.addItem(apple, 3);
        cart.addItem(almond, 1);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(DeliveryCost.CPD.getCost(), DeliveryCost.CPP.getCost(), DeliveryCost.FP.getCost());

        double deliveryCost = deliveryCostCalculator.calculateFor(cart);

        assertEquals(6.96, deliveryCost);
    }

    @Test
    void calculateForMoreDistinctCategory() {
        ShoppingCart cart = new ShoppingCart();

        Category food = new Category("Food");
        Category beverage = new Category("Beverage");

        Product apple = new Product("Apple", 100, food);
        Product almond = new Product("Almonds", 100, food);
        Product coke = new Product("Coke", 30, beverage);

        cart.addItem(apple, 3);
        cart.addItem(almond, 1);
        cart.addItem(coke, 7);

        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(DeliveryCost.CPD.getCost(), DeliveryCost.CPP.getCost(), DeliveryCost.FP.getCost());

        double deliveryCost = deliveryCostCalculator.calculateFor(cart);

        assertEquals(9.94, deliveryCost);
    }
}