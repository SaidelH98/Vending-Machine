package com.techelevator.view;

import java.math.BigDecimal;
import java.util.Scanner;

public class Purchase {

    private static BigDecimal moneyProvided;
    private double change;

    public static void feedMoney(){
        Scanner scanner = new Scanner(System.in);

        boolean shouldRun = true;
        while (shouldRun) {
            try {
                System.out.println("\nPlease enter a dollar amount >>> $");
                String moneyStr = scanner.next();
                BigDecimal money = new BigDecimal(moneyStr);
                setMoneyProvided(money);
                System.out.println("You inserted $" + money);
                shouldRun = false;
            } catch (Exception ex){
                System.out.println("Please enter a valid dollar amount.");
            }
        }
    }

    public static Item selectItem(){
        Inventory.readFile();
        Inventory.printInventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Please enter an item code >>> ");
            String itemSelected = scanner.nextLine();
            for (Item item : Inventory.totalInventory){
                if (item.getButton().equalsIgnoreCase(itemSelected)){
                    System.out.println("Item selected is: " + item.getButton());
                    return item;
                }
            }
            System.out.println("Please enter a valid item code.");
        }
    }

    public static BigDecimal getMoneyProvided() {
        return moneyProvided;
    }

    public static void setMoneyProvided(BigDecimal money) {
        moneyProvided = money;
    }

    public double getChange() {
        return change;
    }

    public void setChange(double change) {
        this.change = change;
    }

}
