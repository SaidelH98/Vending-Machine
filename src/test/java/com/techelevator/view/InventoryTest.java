package com.techelevator.view;

import org.junit.jupiter.api.Test;


public class InventoryTest {

    @Test
    public void test_printInventory() {

        Inventory.readFile();

        final String expectedOutput = "B3 Wonka Bar $1.50 5 remaining ";

        String testString = Inventory.printInventory();

        assert(testString.contains(expectedOutput));
    }


}
