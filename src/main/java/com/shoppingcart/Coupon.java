package com.shoppingcart;

public class Coupon {
    private double minAmount;
    private double amount;
    private String type;

    public Coupon(double minAmount, double amount, String type) {
        this.minAmount = minAmount;
        this.amount = amount;
        this.type = type;
    }

    public double getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(double minAmount) {
        this.minAmount = minAmount;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
