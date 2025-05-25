package com.treasurehuntgamecomplete.challenges;

import utilities.Difficulty;

import java.util.Scanner;

public class MathChallenge extends Challenge {
    private int solution;

    public MathChallenge(String question, int solution, int points, Difficulty difficultyLevel) {
        super(question, points, difficultyLevel);
        this.solution = solution;
    }

    public boolean checkAnswer(int userAnswer) {
        return userAnswer == solution;
    }

    public String getHint() {
        if (getPlayer().hasItem("Calculator")) {
            return "Hint: The answer is close to " + (solution + 2);
        }
        return null;
    }

    @Override
    public void askQuestion(Scanner scanner) {
        System.out.println("Riddle: " + question);
    }
}