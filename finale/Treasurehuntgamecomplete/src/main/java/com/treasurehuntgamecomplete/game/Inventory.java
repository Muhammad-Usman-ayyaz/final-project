package com.treasurehuntgamecomplete.game;
import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private final ArrayList<String> items;

    public Inventory() {
        items = new ArrayList<>();
    }

    public boolean checkItem(String name) {
        return items.contains(name);
    }

    public void addItem(String name) {
        if (!checkItem(name)) {
            items.add(name);
            System.out.println(name + " added to your inventory.");
        } else {
            System.out.println(name + " is already in your inventory.");
        }
    }

    public void removeItem(String name) {
        if (checkItem(name)) {
            items.remove(name);
            System.out.println(name + " removed from your inventory.");
        } else {
            System.out.println("Item not found in inventory.");
        }
    }

    public List<String> getItems() {
        return items;
    }

    public boolean hasItem(String itemName) {
        return items.contains(itemName);
    }
}
