package com.treasurehuntgamecomplete.challenges;
import utilities.Difficulty;

import java.util.Random;
import java.util.Scanner;

public class MiniGameChallenge extends Challenge {
    private String gameType;

    public MiniGameChallenge(String gameType, int points, Difficulty difficultyLevel) {
        super("Mini-Game: " + gameType, points, difficultyLevel);
        this.gameType = gameType;
    }

    @Override
    public void askQuestion(Scanner scanner) {
        Random random = new Random();
        int num = random.nextInt(10) + 1;
        System.out.println("🎮 Mini-Game: Guess a number between 1 and 10.");

        // If player has "Lucky Coin", give them a second chance
        int chances = 1;
        if (this.getPlayer().hasItem("Lucky Coin")) {
            System.out.println("You used your Lucky Coin! You get a second chance!");
            chances = 2;
        }

        boolean guessedCorrectly = false;
        for (int i = 0; i < chances; i++) {
            int guess = scanner.nextInt();
            if (guess == num) {
                System.out.println("🎉 You guessed it right! You earned " + points + " points.");
                guessedCorrectly = true;
                break;
            } else if (i < chances - 1) {
                System.out.println("❌ Wrong! Try again.");
            }
        }

        if (!guessedCorrectly) {
            System.out.println("❌ The correct number was: " + num);
        }
    }
}
