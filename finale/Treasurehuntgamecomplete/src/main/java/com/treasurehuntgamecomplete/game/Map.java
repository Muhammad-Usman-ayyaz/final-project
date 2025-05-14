package com.treasurehuntgamecomplete.game;

import com.treasurehuntgamecomplete.game.Location;

import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Location> locations;
    private int currentIndex;

    public Map() {
        locations = new ArrayList<>();
        currentIndex = 0;
    }

    public void addLocation(Location loc) {
        locations.add(loc);
    }

    public Location getCurrentLocation() {
        if (currentIndex < locations.size()) {
            return locations.get(currentIndex);
        }
        return null;
    }

    public Location getNextLocation() {
        if (hasNextLocation()) {  // Ensure there is a next location before incrementing
            currentIndex++;  // Increment to the next location
            return locations.get(currentIndex);
        }
        return null;  // Return null if no next location
    }

    public boolean hasNextLocation() {
        return currentIndex + 1 < locations.size(); // Ensure there is another location to go to
    }
}