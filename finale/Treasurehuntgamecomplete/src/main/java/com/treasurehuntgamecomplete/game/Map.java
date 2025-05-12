package com.treasurehuntgamecomplete.game;
import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Location> locations;
    private int currentIndex;

    public Map() {
        locations = new ArrayList<>();
        currentIndex = 0;  // The starting location
    }

    public void addLocation(Location loc) {
        locations.add(loc);
    }

    public Location getCurrentLocation() {
        return locations.get(currentIndex);
    }

    public Location getNextLocation()
    {
        if (currentIndex < locations.size() - 1)
        {

            return locations.get(currentIndex);
        }
        currentIndex++;
        return null;  // No next location
    }

    public boolean hasNextLocation() {
        return currentIndex < locations.size() - 1;
    }
}
