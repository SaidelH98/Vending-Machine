package com.techelevator.view;

import org.junit.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


public class InventoryTest {

    @BeforeEach
    public void setup(){
        Inventory.readFile();
    }

    @Test
    public void test_printInventory() {

        final String expectedOutput = "B3 Wonka Bar $1.50 5 remaining ";

        String testString = Inventory.printInventory();

        assert(testString.contains(expectedOutput));
    }


}
