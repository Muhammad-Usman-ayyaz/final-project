package com.treasurehuntgamecomplete.challenges;

import utilities.Difficulty;

import java.util.Scanner;

public class MathChallenge extends Challenge {
    private int solution;

    public MathChallenge(String question, int solution, int points, Difficulty difficultyLevel) {
        super(question, points, difficultyLevel);
        this.solution = solution;
    }

    @Override
    public void askQuestion(Scanner scanner) {
        System.out.println("Solve: " + question);
        System.out.print("Enter your answer: ");
        int userAnswer = scanner.nextInt();

        // Check if the player has a "Calculator" item that gives them a hint
        if (userAnswer != solution && userAnswer == -1 && playerHasCalculator()) {
            System.out.println("You used your Calculator! Here's a hint: The answer is greater than 10.");
            System.out.print("Try again: ");
            userAnswer = scanner.nextInt();
        }

        if (checkAnswer(userAnswer)) {
            System.out.println("✅ Correct! You earned " + points + " points.");
        } else {
            System.out.println("❌ Incorrect! The correct answer was: " + solution);
        }
    }

    private boolean playerHasCalculator() {
        return this.getPlayer().hasItem("Calculator");  // Check if player has the item
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == solution;
    }
}
