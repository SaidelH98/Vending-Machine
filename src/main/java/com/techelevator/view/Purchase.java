package com.techelevator.view;

import java.math.BigDecimal;
import java.util.Scanner;

public class Purchase {

    private static BigDecimal moneyProvided;
    private static BigDecimal change;

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

        boolean shouldRun = true;
        while (shouldRun) {
            try {
                System.out.print("\nPlease enter a dollar amount >>> $");
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
        //Inventory.readFile();
        Inventory.printInventory();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("Please enter an item code >>> ");
            String itemSelected = scanner.nextLine();
            for (Item item : Inventory.totalInventory){
                if (item.getButton().equalsIgnoreCase(itemSelected)){
                    if (item.getQuantityRemaining() == 0){
                        System.out.println("Item is sold out.");
                        break;
                    }
                    return item;
                }
            }
            System.out.println("Please enter a valid item code.");
        }
    }

    public static boolean purchaseItem(Item item){
        if(getMoneyProvided().subtract(item.getPrice()).compareTo(new BigDecimal(0.00)) == -1){
            System.out.println("Not enough money to purchase item.");
            return false;
        }
        System.out.println("Item selected is:");
        System.out.println(item.getName() + " $" + item.getPrice() + " $" + getMoneyProvided());
        if (item.getCategory().equals("Chip")){
            System.out.println("Crunch Crunch, Yum!");
        } else if (item.getCategory().equals("Candy")){
            System.out.println("Munch Munch, Yum!");
        } else if (item.getCategory().equals("Drink")){
            System.out.println("Glug Glug, Yum!");
        } else if (item.getCategory().equals("Gum")){
            System.out.println( "Chew Chew, Yum!");
        }
        return true;
    }

    public static void updateInventory(Item item, boolean canPurchaseItem){
        if (canPurchaseItem){
            setMoneyProvided(getMoneyProvided().subtract(item.getPrice()));
            item.setQuantityRemaining(item.getQuantityRemaining() - 1);
            System.out.println("Item Dispensed.");
            System.out.println("You have $" + getMoneyProvided() + " left");
        } else {
            System.out.println("Please add more money or select a different product.");
        }
    }

}
