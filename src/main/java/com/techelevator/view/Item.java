package com.techelevator.view;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Item {

    private String button;
    private String name;
    private BigDecimal price;
    private String category;
    private int quantityRemaining = 5;

    public Item(String button, String name, BigDecimal price, String category){
        this.button = button;
        this.name = name;
        this.price = price;
        this.category = category;
    }

    public Item(String button, String name, BigDecimal price, String category, int quantityRemaining){
        this.button = button;
        this.name = name;
        this.price = price;
        this.category = category;
        this.quantityRemaining = quantityRemaining;
    }

    public String getButton(){
        return button;
    }

    public String getName(){
        return name;
    }

    public BigDecimal getPrice(){
        return price;
    }

    public String getCategory(){
        return category;
    }

    public int getQuantityRemaining(){
        return quantityRemaining;
    }

    public void setQuantityRemaining(int quantityRemaining){
        this.quantityRemaining = quantityRemaining;
    }

}
