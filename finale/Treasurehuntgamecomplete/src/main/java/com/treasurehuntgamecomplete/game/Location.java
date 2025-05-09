package com.treasurehuntgamecomplete.game;
import com.treasurehuntgamecomplete.game.*;
import com.treasurehuntgamecomplete.challenges.*;

public class Location {
    private String name;
    private String description;
    private Challenge challenge;
    private boolean isComplete;

    public Location(String name, String description, Challenge challenge) {
        this.name = name;
        this.description = description;
        this.challenge = challenge;
        this.isComplete = false;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Challenge getChallenge() {
        return challenge;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void completeLocation() {
        isComplete = true;
    }
}
