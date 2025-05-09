package com.treasurehuntgamecomplete.game;


import java.util.List;

public class Player {
    private String name;
    private int score;
    private Inventory inventory;

    public Player(String name) {
        this.name = name;
        this.score = 0;
        this.inventory = new Inventory(); // Create a new inventory for the player
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public void increaseScore(int points) {
        this.score += points;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public boolean hasItem(String itemName) {
        return inventory.checkItem(itemName);  // Use the inventory's checkItem method
    }

    public void addItem(String itemName) {
        inventory.addItem(itemName);  // Add item to the player's inventory
    }

    public void showInventory() {
        List<String> items = inventory.getItems();
        if (items.isEmpty()) {
            System.out.println("Your inventory is empty.");
        } else {
            System.out.println("Items in your inventory: " + String.join(", ", items));
        }
    }

    public boolean isAlive() {
        return score > 0; // Example condition for the player's survival (score should be positive)
    }
}
