package com.treasurehuntgamecomplete.utilities;

import com.treasurehuntgamecomplete.challenges.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

    public class ChallengeGUI {

        public static void display(Challenge challenge, Runnable onComplete) {
            VBox root = new VBox(10);
            Stage stage = new Stage();

            Label qLabel = new Label("🔎 " + challenge.question);
            TextField answerField = new TextField();
            answerField.setPromptText("Your answer");
            Button submitBtn = new Button("Submit");

            submitBtn.setOnAction(e -> {
                int earned = 0;
                if (challenge instanceof RiddleChallenge rc) {
                    if (rc.checkAnswer(answerField.getText())) earned = rc.getPoints();
                } else if (challenge instanceof MathChallenge mc) {
                    try {
                        int ans = Integer.parseInt(answerField.getText());
                        if (mc.checkAnswer(ans)) earned = mc.getPoints();
                    } catch (NumberFormatException ignored) {}
                } else if (challenge instanceof MiniGameChallenge) {
                    earned = 0; // GUI-based mini-games can be implemented later
                }

                challenge.points = earned;
                stage.close();
                onComplete.run();
            });

            root.getChildren().addAll(qLabel, answerField, submitBtn);
            stage.setScene(new Scene(root, 300, 200));
            stage.setTitle("Challenge");
            stage.show();
        }
    }

