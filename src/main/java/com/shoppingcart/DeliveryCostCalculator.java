package com.shoppingcart;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Dynamic delivery cost calculator fo cart.
 *
 * @author Burak Bolat (brkblt@gmail.com)
 */
public class DeliveryCostCalculator {
    private double costPerDelivery;
    private double costPerProduct;
    private double fixedCost;

    public DeliveryCostCalculator(double costPerDelivery, double costPerProduct, double fixedCost) {
        this.costPerDelivery = costPerDelivery;
        this.costPerProduct = costPerProduct;
        this.fixedCost = fixedCost;
    }

    /**
     * Optimize delivery cost applying dynamic cargo pricing rules.
     *
     * Formula is: (costPerDelivery * numberOfDeliveries) + (costPerProduct * numberOfProducts) + fixedCost
     * - numberOfDeliveries: calculated by number of distinct categories in the cart.
     * - numberOfProducts: the number of different products in the cart. It's not quantity of the products.
     *
     * @param cart
     * @return delivery cost
     */
    public double calculateFor(ShoppingCart cart) {
        int numberOfDeliveries = getNumberOfDeliveries(cart);

        int numberOfProducts = getNumberOfProducts(cart);

        return (this.costPerDelivery * numberOfDeliveries) + (this.costPerProduct * numberOfProducts) + this.fixedCost;
    }

    /**
     * numberOfDeliveries is calculated by number of distinct categories in the cart.
     *
     * @param cart
     * @return number of distinct category in the cart
     */
    private int getNumberOfDeliveries(ShoppingCart cart) {
        // TODO: Improve logic, maybe use stream api.
        int numberOfDeliveries = 0;
        Map<String, String> distinctCategories = new HashMap<String, String>();
        for (CartItem item : cart.getCartItems()) {
            String categoryTitle = item.getProduct().getCategory().getTitle();

            if (distinctCategories.containsKey(categoryTitle)) {
                continue;
            }

            distinctCategories.put(categoryTitle, categoryTitle);

            numberOfDeliveries++;
        }

        return numberOfDeliveries;
    }

    /**
     * numberOfProducts is the number of different products in the cart. It's not quantity of the products.
     *
     * @param cart
     * @return distinct product count in the cart
     */
    private int getNumberOfProducts(ShoppingCart cart) {
        List<CartItem> items = cart.getCartItems();

        Map<Product, Long> counting = items.stream().collect(
                Collectors.groupingBy(CartItem::getProduct, Collectors.counting()));

        return counting.size();
    }
}
