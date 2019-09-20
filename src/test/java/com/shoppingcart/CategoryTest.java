package com.shoppingcart;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CategoryTest {

    @Test
    void getTitle() {
        Category category = new Category("Computer");
        
        assertEquals("Computer", category.getTitle());
    }

    @Test
    void setTitle() {
        Category category = new Category("Computer");
        
        category.setTitle("Beverage");

        assertEquals("Beverage", category.getTitle());
    }

    @Test
    void getParent() {
        Category category = new Category("Computer");
        
        assertNull(category.getParent());
    }

    @Test
    void setParent() {
        Category electronic = new Category("Electronic");
        Category computer = new Category("Computer");
        
        computer.setParent(electronic);

        assertSame(electronic, computer.getParent());
    }
}