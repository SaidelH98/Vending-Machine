package com.techelevator.view;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Purchase {

    private static BigDecimal moneyProvided = new BigDecimal("0.00");
    private static BigDecimal change;

    public static File file = new File("./log.txt");

    public static BigDecimal getMoneyProvided() {
        return moneyProvided;
    }

    public static void setMoneyProvided(BigDecimal money) {
        moneyProvided = money;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public static void feedMoney(){
        Scanner scanner = new Scanner(System.in);

        //fix bug when inserting $1.339 too many decimals on input
        boolean shouldRun = true;
        while (shouldRun) {
            try {
                System.out.print("\nPlease enter a dollar amount >>> $");
                String moneyStr = scanner.next();
                BigDecimal money = new BigDecimal(moneyStr);
                System.out.println("You inserted $" + money.setScale(2));
                setMoneyProvided(money.add(getMoneyProvided()));
                logTransaction("FEED MONEY", money.setScale(2), getMoneyProvided());

                shouldRun = false;
            } catch (Exception ex){
                System.out.println("Please enter a valid dollar amount.");
            }
        }
    }

    public static Item selectItem(){
        System.out.println(Inventory.printInventory());
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\nPlease enter an item code or enter 1 to exit >>> ");
            String itemSelected = scanner.nextLine();
            if (itemSelected.equals("1")) {
                createChange();
                System.exit(0);
            }
            for (Item item : Inventory.totalInventory){
                if (item.getButton().equalsIgnoreCase(itemSelected)){
                    if (item.getQuantityRemaining() == 0){
                        System.out.println("Item is sold out.");
                        break;
                    }
                    return item;
                }
            }
            System.out.println("Please enter a valid item code or enter 1 to exit.");
        }
    }

    public static boolean purchaseItem(Item item){
        if(getMoneyProvided().subtract(item.getPrice()).compareTo(new BigDecimal(0.00)) == -1){
            System.out.println("Not enough money to purchase item.");
            return false;
        }
        System.out.print("Item selected is: ");
        System.out.println(item.getName() + " | Price $" + item.getPrice() + " | Current Balance $" + getMoneyProvided());
        if (item.getCategory().equals("Chip")){
            System.out.println("Crunch Crunch, Yum!");
        } else if (item.getCategory().equals("Candy")){
            System.out.println("Munch Munch, Yum!");
        } else if (item.getCategory().equals("Drink")){
            System.out.println("Glug Glug, Yum!");
        } else if (item.getCategory().equals("Gum")){
            System.out.println( "Chew Chew, Yum!");
        }
        //writes purchase to log
        String formatToLog = item.getName() + " " + item.getButton();
        logTransaction(formatToLog, item.getPrice(), getMoneyProvided().subtract(item.getPrice()));

        return true;
    }

    public static void updateInventory(Item item, boolean canPurchaseItem){
        if (canPurchaseItem){
            setMoneyProvided(getMoneyProvided().subtract(item.getPrice()));
            item.setQuantityRemaining(item.getQuantityRemaining() - 1);
            System.out.println("[Item Dispensed]");
            System.out.println("You have $" + getMoneyProvided() + " left.");
        } else {
            System.out.println("Please add more money or select a different product.");
        }
    }

    public static String createChange(){
        String returnString = "";
        change = getMoneyProvided();
        double dblChange = change.doubleValue();
        int intChange = (int)(dblChange * 100);
        int quarters = (int)(Math.floor(intChange / 25));
        intChange %= 25;
        int dimes = (int)(Math.floor(intChange / 10));
        intChange %= 10;
        int nickels = (int)(Math.floor(intChange / 5));
        intChange %= 5;
        returnString = "Your change is " + quarters + " quarters, " + dimes + " dime, " + nickels + " nickels, and " + intChange + " pennies.";
        //writes to log
        logTransaction("GIVE CHANGE", change, new BigDecimal(0));
        return returnString;
    }

    public static void logTransaction(String transactionType, BigDecimal dollarAmount, BigDecimal moneyRemaining){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("MM/dd/yyyy hh:mm:ss a");
        LocalDateTime now = LocalDateTime.now();

        try (FileWriter dataOutput = new FileWriter(file, true)){
            dataOutput.append("\n" + dtf.format(now) + " " + transactionType + ": $" + dollarAmount + " $" + moneyRemaining);

        } catch (Exception ex) {
            System.out.println("Something went wrong.");
        }
    }
}
