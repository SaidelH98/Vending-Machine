package com.techelevator.view;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

public class ItemTest {

    private Item item;
    @Before
    public void setup(){
        //Arrange
        item = new Item("AA", "Test Item", new BigDecimal("0.00"), "Nothing");
    }

    @Test
    public void test_getButton(){
        //Act
        String result = item.getButton();

        //Assert
        Assert.assertEquals("AA", result);
    }

    //Make tests for all methods

}
