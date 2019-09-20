package com.shoppingcart;

/**
 * Discount handler, handles campaigns to cart.
 *
 * @author Burak Bolat (brkblt@gmail.com)
 */
public class DiscountHandler implements HandlerI {
    private ShoppingCart cart;
    private Campaign[] campaigns;

    public DiscountHandler(ShoppingCart cart, Campaign... campaigns) {
        this.cart = cart;
        this.campaigns = campaigns;
    }

    /**
     * This handler is handles given campaign discount(s) to cart.
     *
     * If campaign rules is valid the best discount applies to cart.
     * Rules:
     *  - Discount applicable to category.
     *  - Items in the cart greater than or equals to discount min quantity, wh,ch under a distinct category.
     *  - Only one discount applicable has max amount.
     */
    public void handle() {
        double campaignDiscount = 0;

        for (Campaign campaign : this.campaigns) {
            // Hold category item quantity
            int quantity = 0;
            // Hold calculated discount
            double discount = 0;

            for (CartItem item : this.cart.getCartItems()) {
                // If category rules does not matches do not apply discount
                if (!item.getProduct().getCategory().equals(campaign.getCategory())) {
                    continue;
                }

                quantity += item.getQuantity();

                discount += calculate(campaign, item);
            }

            // If category total quantity less than campaign minQuantity or calculated discount is lowest continue next
            // campaign
            if (quantity < campaign.getMinQuantity() || discount < campaignDiscount) {
                continue;
            }

            campaignDiscount = discount;
        }

        this.cart.setCampaignDiscount(campaignDiscount);
    }

    /**
     * Calculates single cart item campaign discount.
     *
     * @param campaign
     * @param item
     * @return discount amount
     */
    private double calculate(Campaign campaign, CartItem item) {
        Product product = item.getProduct();
        double itemTotal = product.getPrice() * item.getQuantity();
        double itemDiscount = 0;

        if (campaign.getType().equals(DiscountType.Rate.toString())) {
            itemDiscount = itemTotal * (campaign.getAmount() / 100);
        } else {
            itemDiscount = item.getQuantity() * campaign.getAmount();
        }

        return itemDiscount;
    }
}
