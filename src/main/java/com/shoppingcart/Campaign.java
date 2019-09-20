package com.shoppingcart;

public class Campaign {
    private Category category;
    private double amount;
    private int minQuantity;
    private String type;

    public Campaign(Category category, double amount, int minQuantity, String type) {
        this.category = category;
        this.amount = amount;
        this.minQuantity = minQuantity;
        this.type = type;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getMinQuantity() {
        return minQuantity;
    }

    public void setMinQuantity(int minQuantity) {
        this.minQuantity = minQuantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
