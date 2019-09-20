package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DeliveryCostTest {

    @Test
    void getCost() {
        assertEquals(1.99, DeliveryCost.CPD.getCost());
        assertEquals(0.99, DeliveryCost.CPP.getCost());
        assertEquals(2.99, DeliveryCost.FP.getCost());

        assertEquals(DeliveryCost.valueOfLabel("CostPerDelivery").getCost(), DeliveryCost.CPD.getCost());
        assertEquals(DeliveryCost.valueOfLabel("CostPerProduct").getCost(), DeliveryCost.CPP.getCost());
        assertEquals(DeliveryCost.valueOfLabel("FixedPrice").getCost(), DeliveryCost.FP.getCost());
    }

    @Test
    void valueOfLabel() {
        assertSame(DeliveryCost.valueOfLabel("CostPerDelivery"), DeliveryCost.CPD);
        assertSame(DeliveryCost.valueOfLabel("CostPerProduct"), DeliveryCost.CPP);
        assertSame(DeliveryCost.valueOfLabel("FixedPrice"), DeliveryCost.FP);
    }
}