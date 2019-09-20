package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ProductTest {

    @Test
    void getCategory() {
        Category food = new Category("Food");
        Product product = new Product("Apple", 100.0, food);

        assertSame(food, product.getCategory());
    }

    @Test
    void setCategory() {
        Category food = new Category("Food");
        Category beverage = new Category("Beverage");
        Product product = new Product("Coke", 99.99, food);

        product.setCategory(beverage);

        assertSame(beverage, product.getCategory());
    }

    @Test
    void getTitle() {
        Category food = new Category("Food");
        Product product = new Product("Apple", 100.0, food);

        assertEquals("Apple", product.getTitle());
    }

    @Test
    void setTitle() {
        Category food = new Category("Food");
        Product product = new Product("Apple", 100.0, food);

        product.setTitle("Almonds");

        assertEquals("Almonds", product.getTitle());
    }

    @Test
    void getPrice() {
        Category food = new Category("Food");
        Product product = new Product("Coke", 99.99, food);

        assertEquals(99.99, product.getPrice());
    }

    @Test
    void setPrice() {
        Category food = new Category("Food");
        Product product = new Product("Coke", 99.99, food);

        product.setPrice(100.0);

        assertEquals(100.0, product.getPrice());
    }
}