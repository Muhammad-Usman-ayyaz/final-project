package com.treasurehuntgamecomplete.challenges;

import utilities.Difficulty;
import java.util.Random;
import java.util.Scanner;

public class CombatChallenge extends Challenge {
    private int enemyStrength;

    public CombatChallenge(int enemyStrength, int points, Difficulty difficultyLevel) {
        super("Fight the enemy with strength " + enemyStrength + "!", points, difficultyLevel);
        this.enemyStrength = enemyStrength;
    }

    @Override
    public void askQuestion(Scanner scanner) {
        System.out.println("⚔️ A mighty enemy appears! Strength: " + enemyStrength);
        System.out.println("Do you want to (1) Fight or (2) Flee?");
        int choice = scanner.nextInt();

        if (choice == 1) {
            Random random = new Random();
            int playerAttack = random.nextInt(100) + 1;
            System.out.println("You attacked with strength: " + playerAttack);

            if (playerAttack >= enemyStrength) {
                System.out.println("🏆 You defeated the enemy! You earned " + points + " points!");
                player.increaseScore(points);
            } else {
                System.out.println("💀 You lost the fight. Losing 10 points.");
                player.increaseScore(-10);
            }
        } else {
            System.out.println("🏃 You fled safely but gained no points.");
        }
    }
}
