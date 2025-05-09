package com.treasurehuntgamecomplete.game;
import java.io.*;
import java.util.*;
import com.treasurehuntgamecomplete.challenges.*;
import utilities.Difficulty;

public class GameUtilities {

    public static int getRandomNumber(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    public static Map loadMapFromFile(String filename) {
        Map gameMap = new Map();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3) {
                    String name = parts[0].trim();
                    String description = parts[1].trim();
                    // Assuming the third part is challenge details; adjust as needed
                    Challenge challenge = createSampleChallenge(); // Implement based on your needs
                    gameMap.addLocation(new Location(name, description, challenge));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading map: " + e.getMessage());
        }
        return gameMap;
    }

    private static Challenge createSampleChallenge() {
        // Example: Return a default challenge. Adjust to load from file data.
        return new RiddleChallenge("What has keys but can't open locks?", "Piano", 10, Difficulty.EASY);
    }

    public static void playSound(String effectName) {
        System.out.println("Playing sound effect: " + effectName);
    }
}