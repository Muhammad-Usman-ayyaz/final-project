package com.treasurehuntgamecomplete.challenges;

import utilities.Difficulty;
import java.util.Scanner;

public class RiddleChallenge extends Challenge {
    private final String answer;

    public RiddleChallenge(String question, String answer, int points, Difficulty difficultyLevel) {
        super(question, points, difficultyLevel);
        this.answer = answer;
    }

    @Override
    public void askQuestion(Scanner scanner) {
        System.out.println("Riddle: " + question);
        String userAnswer = scanner.nextLine();
        if (checkAnswer(userAnswer)) {
            System.out.println("✅ Correct! You earned " + points + " points.");
        } else {
            System.out.println("❌ Incorrect! The correct answer was: " + answer);
        }
    }

    public boolean checkAnswer(String userAnswer) {
        return userAnswer.equalsIgnoreCase(answer);
    }
}
