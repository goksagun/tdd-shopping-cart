package com.shoppingcart;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Shopping cart holds products with it's quantity and calculates total amount, discounts and delivery cost.
 *
 * @author Burak Bolat (brkblt@gmail.com)
 */
public class ShoppingCart {
    private List<CartItem> cartItems;
    private double total;
    private double campaignDiscount;
    private double couponDiscount;

    public ShoppingCart() {
        this.cartItems = new ArrayList<CartItem>();
        this.total = 0;
        this.campaignDiscount = 0;
        this.couponDiscount = 0;
    }

    /**
     * Add product to cart with it's quantity and increment cart total.
     *
     * @param product
     * @param quantity
     */
    public void addItem(Product product, int quantity) {
        CartItem cartItem = new CartItem(product, quantity);
        this.cartItems.add(cartItem);

        incrementTotalFor(cartItem);
    }

    public List<CartItem> getCartItems() {
        return cartItems;
    }

    /**
     * Applies campaigns to cart.
     *
     * @param campaigns
     */
    public void applyDiscounts(Campaign... campaigns) {
        DiscountHandler discountHandler = new DiscountHandler(this, campaigns);

        discountHandler.handle();
    }

    /**
     * Applies coupon to cart.
     *
     * @param coupon
     */
    public void applyCoupon(Coupon coupon) {
        CouponHandler couponHandler = new CouponHandler(this, coupon);

        couponHandler.handle();
    }

    /**
     * Get cart total amount after discount an coupon applied.
     *
     * @return total amount of cart
     */
    public double getTotalAmountAfterDiscount() {
        return this.total - this.campaignDiscount - this.couponDiscount;
    }

    /**
     * Get campaign discount amount.
     *
     * @return campaign discount amount
     */
    public double getCampaignDiscount() {
        return this.campaignDiscount;
    }

    public void setCampaignDiscount(double campaignDiscount) {
        this.campaignDiscount = campaignDiscount;
    }

    /**
     * Get coupon discount amount.
     *
     * @return coupon discount amount
     */
    public double getCouponDiscount() {
        return this.couponDiscount;
    }

    public void setCouponDiscount(double couponDiscount) {
        this.couponDiscount = couponDiscount;
    }

    /**
     * Get delivery cost.
     *
     * @return delivery cost.
     */
    public double getDeliveryCost() {
        DeliveryCostCalculator deliveryCostCalculator = new DeliveryCostCalculator(
                DeliveryCost.CPD.getCost(),
                DeliveryCost.CPP.getCost(),
                DeliveryCost.FP.getCost()
        );

        return deliveryCostCalculator.calculateFor(this);
    }

    /**
     * Print cart details.
     *
     * @return details of cart
     */
    public String print() {
        // group products by category
        Map<Category, List<CartItem>> map = getCategoryListMap();

        String out = "";
        for (Map.Entry<Category, List<CartItem>> entry : map.entrySet()) {
            Category category = entry.getKey();
            List<CartItem> items = entry.getValue();

            for (CartItem item : items) {
                Product product = item.getProduct();
                int quantity = item.getQuantity();

                double totalPrice = quantity * product.getPrice();

                // categoryTitle + productTitle + quantity + unitPrice + totalPrice + totalDiscount
                out += category.getTitle() + ", " +
                        product.getTitle() + ", " +
                        quantity + ", " +
                        product.getPrice() + ", " +
                        totalPrice +
                        System.lineSeparator();
            }
        }

        double totalDiscount = this.getCampaignDiscount() + this.getCouponDiscount();

        out += "Total Discount: " + totalDiscount + System.lineSeparator();
        out += "Delivery Cost: " + this.getDeliveryCost() + System.lineSeparator();
        out += "Total Amount: " + this.getTotalAmountAfterDiscount() + System.lineSeparator();

        return out;
    }

    /**
     * Group by category cart items.
     *
     * @return mapped items grouped by category
     */
    private Map<Category, List<CartItem>> getCategoryListMap() {
        Map<Category, List<CartItem>> map = new HashMap<Category, List<CartItem>>();

        for (CartItem cartItem : cartItems) {
            Category category = cartItem.getProduct().getCategory();

            if (!map.containsKey(category)) {
                List<CartItem> list = new ArrayList<CartItem>();

                list.add(cartItem);

                map.put(category, list);
            } else {
                map.get(category).add(cartItem);
            }
        }

        return map;
    }

    /**
     * Increment cart total for cartItem.
     *
     * @param cartItem
     */
    private void incrementTotalFor(CartItem cartItem) {
        this.total += cartItem.getProduct().getPrice() * cartItem.getQuantity();
    }
}
