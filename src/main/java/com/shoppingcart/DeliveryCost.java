package com.shoppingcart;

import java.util.HashMap;
import java.util.Map;

public enum DeliveryCost {
    CPD("CostPerDelivery", 1.99),
    CPP("CostPerProduct", 0.99),
    FP("FixedPrice", 2.99);

    private static final Map<String, DeliveryCost> BY_LABEL = new HashMap<>();

    static {
        for (DeliveryCost deliveryCost : values()) {
            BY_LABEL.put(deliveryCost.label, deliveryCost);
        }
    }

    private final String label;
    private final double cost;

    DeliveryCost(String label, double cost) {
        this.label = label;
        this.cost = cost;
    }

    public double getCost() {
        return this.cost;
    }


    public static DeliveryCost valueOfLabel(String label) {
        return BY_LABEL.get(label);
    }
}
