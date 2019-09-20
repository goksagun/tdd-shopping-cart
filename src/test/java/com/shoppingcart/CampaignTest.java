package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CampaignTest {

    @Test
    void getCategory() {
        Category food = new Category("Food");
        Campaign campaign = new Campaign(food, 10.0, 3, DiscountType.Rate.toString());

        assertSame(food, campaign.getCategory());
    }

    @Test
    void setCategory() {
        Category food = new Category("Food");
        Category beverage = new Category("Beverage");
        Campaign campaign = new Campaign(food, 10.0, 3, DiscountType.Rate.toString());

        campaign.setCategory(beverage);

        assertSame(beverage, campaign.getCategory());
    }

    @Test
    void getAmount() {
        Category food = new Category("Food");
        Campaign campaign = new Campaign(food, 10.0, 3, DiscountType.Rate.toString());

        assertEquals(10.0, campaign.getAmount());
    }

    @Test
    void setAmount() {
        Category food = new Category("Food");
        Campaign campaign = new Campaign(food, 10.0, 3, DiscountType.Rate.toString());

        campaign.setAmount(9.99);

        assertEquals(9.99, campaign.getAmount());
    }

    @Test
    void getMinQuantity() {
        Category food = new Category("Food");
        Campaign campaign = new Campaign(food, 10.0, 3, DiscountType.Rate.toString());

        assertEquals(10.0, campaign.getAmount());
    }

    @Test
    void setMinQuantity() {
        Category food = new Category("Food");
        Campaign campaign = new Campaign(food, 10.0, 3, DiscountType.Rate.toString());

        campaign.setMinQuantity(1);

        assertEquals(1, campaign.getMinQuantity());
    }

    @Test
    void getType() {
        Category food = new Category("Food");
        Campaign campaign = new Campaign(food, 10.0, 3, DiscountType.Rate.toString());

        assertEquals(DiscountType.Rate.toString(), campaign.getType());
    }

    @Test
    void setType() {
        Category food = new Category("Food");
        Campaign campaign = new Campaign(food, 10.0, 3, DiscountType.Rate.toString());

        campaign.setType(DiscountType.Amount.toString());

        assertEquals(DiscountType.Amount.toString(), campaign.getType());
    }
}