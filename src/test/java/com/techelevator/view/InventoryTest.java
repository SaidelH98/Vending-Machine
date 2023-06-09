package com.techelevator.view;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;


public class InventoryTest {

    @Test
    public void valid_singleInput() {

        final String expectedOutput = "1000";

        String testString = Inventory.printInventory();

        assert(testString.contains(expectedOutput));
    }

}
