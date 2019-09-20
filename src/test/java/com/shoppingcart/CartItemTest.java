package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CartItemTest {

    @Test
    void getProduct() {
        Category food = new Category("Food");
        Product apple = new Product("Apple", 100.0, food);
        CartItem cartItem = new CartItem(apple, 3);

        assertSame(apple, cartItem.getProduct());
    }

    @Test
    void setProduct() {
        Category food = new Category("Food");
        Product apple = new Product("Apple", 100.0, food);
        Product almonds = new Product("Almonds", 150.0, food);
        CartItem cartItem = new CartItem(apple, 3);

        cartItem.setProduct(almonds);

        assertSame(almonds, cartItem.getProduct());
    }

    @Test
    void getQuantity() {
        Category food = new Category("Food");
        Product apple = new Product("Apple", 100.0, food);
        CartItem cartItem = new CartItem(apple, 3);

        assertEquals(3, cartItem.getQuantity());
    }

    @Test
    void setQuantity() {
        Category food = new Category("Food");
        Product apple = new Product("Apple", 100.0, food);
        CartItem cartItem = new CartItem(apple, 3);

        cartItem.setQuantity(5);

        assertEquals(5, cartItem.getQuantity());
    }
}