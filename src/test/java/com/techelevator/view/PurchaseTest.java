package com.techelevator.view;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.math.BigDecimal;

public class PurchaseTest {



    @Test
    public void test_feedMoney(){
        //Arrange
        String input = "2.00";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //Act
        Purchase.feedMoney();

        //Assert
        Assert.assertEquals(Purchase.getMoneyProvided(), new BigDecimal("2.00"));
    }

    @Test
    public void test_selectItem(){
        //Arrange
        Inventory.readFile();
        String input = "C1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        //Act
        Item testItem = Purchase.selectItem();

        //Assert
        Assert.assertEquals(testItem.getButton(), input);
    }

    @Test
    public void test_purchaseItem(){
        //Arrange
        Purchase.setMoneyProvided(new BigDecimal("5.00"));
        Item testItem = new Item("C4", "Heavy", new BigDecimal("1.50"), "Drink");

        //Act
        boolean result = Purchase.purchaseItem(testItem);

        //Assert
        Assert.assertTrue(result);
    }

    @Test
    public void test_updateInventory(){
        //Arrange
        Purchase.setMoneyProvided(new BigDecimal("5.00"));
        Item testItem = new Item("C4", "Heavy", new BigDecimal("1.50"), "Drink");

        //Act
        Purchase.updateInventory(testItem, true);

        //Assert
        Assert.assertEquals(testItem.getQuantityRemaining(), 4);
    }

    @Test
    public void test_createChange(){
        //Arrange
        Purchase.setMoneyProvided(new BigDecimal("0.75"));

        //Act
        String result = Purchase.createChange();
        String intended = "Your change is 3 quarters, 0 dime, 0 nickels, and 0 pennies.";

        //Assert
        Assert.assertEquals(result, intended);
    }

}
