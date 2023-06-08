package com.techelevator.view;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Inventory {
    public static List<Item> totalInventory = new ArrayList<>();

    private static String inventoryPath = "C:\\Users\\Student\\workspace\\nlr-14-module-1-capstone-team-5\\vendingmachine.csv";
    private static File inventoryFile = new File(inventoryPath);


    public static void readFile(){
        if (!inventoryFile.exists()){
            System.out.println(inventoryPath + " does not exist.");
            System.exit(1);
        } else if (!inventoryFile.isFile()){
            System.out.println(inventoryPath + " is not a file.");
            System.exit(1);
        }

        try(Scanner scanner = new Scanner(inventoryFile)){
            while (scanner.hasNextLine()){
                String line = scanner.nextLine();
                String[] itemStat = line.split("\\|");
                Item item = new Item(itemStat[0], itemStat[1], new BigDecimal(itemStat[2]), itemStat[3]);
                totalInventory.add(item);
            }

        } catch (Exception ex){
            System.out.println("Something went wrong.");
        }
    }

    public static void printInventory(){
        for (Item item : totalInventory){
            System.out.println(item.getButton() + " " + item.getName() + " $" + item.getPrice() + " " + item.getQuantityRemaining() + " remaining");
        }
    }

}
