package com.treasurehuntgamecomplete.utilities;

import com.treasurehuntgamecomplete.challenges.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import com.treasurehuntgamecomplete.SceneManager;

import static com.treasurehuntgamecomplete.SceneManager.*;

public class ChallengeGUI {


    public static void displayFirstlocation(Challenge challenge, Runnable onComplete) {
        BackgroundImage backgroundImage;
        if(gender){
            Image bgImage = new Image(SceneManager.class.getResource("/images/Firstq.jpg").toExternalForm());
            backgroundImage = new BackgroundImage(
                    bgImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(800, 600, false, false, false, false)
            );
        }
        else {
        Image bgImage = new Image(SceneManager.class.getResource("/images/female.jpg").toExternalForm());
            backgroundImage= new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(800, 600, false, false, false, false)
        );
        }



        Pane root = new Pane();

        root.setBackground(new Background(backgroundImage));
        root.setPrefSize(800, 600);

        Label qLabel = new Label(challenge.question);
        TextField answerField = new TextField();
        answerField.setLayoutX(450);
        answerField.setLayoutY(510);
        answerField.setPromptText("Your answer");
        Button submitBtn = new Button("Submit");
        submitBtn.setLayoutX(575);
        submitBtn.setLayoutY(510);


        submitBtn.setOnAction(e -> {
            int earned = 0;
            if (challenge instanceof RiddleChallenge rc) {
                if (rc.checkAnswer(answerField.getText())) earned = rc.getPoints();
            } else if (challenge instanceof MathChallenge mc) {
                try {
                    int ans = Integer.parseInt(answerField.getText());
                    if (mc.checkAnswer(ans)) earned = mc.getPoints();
                } catch (NumberFormatException ignored) {}
            } else if (challenge instanceof MiniGameChallenge mgc ) {

                mgc.play(onComplete);
                return;
            }

            challenge.points = earned;
            onComplete.run();
            startSchallange();
        });

        root.getChildren().addAll(qLabel, answerField, submitBtn);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setTitle("Challenge 1");
        primaryStage.show();
    }
    public static void displaySecondlocation(Challenge challenge, Runnable onComplete) {

        if(gender){
            Image bgImage = new Image(SceneManager.class.getResource("/images/castle game male.jpg").toExternalForm());
            BackgroundImage backgroundImage = new BackgroundImage(
                    bgImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(800, 600, false, false, false, false)
            );
        }
        Image bgImage = new Image(SceneManager.class.getResource("/images/castle game female.jpg").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(800, 600, false, false, false, false)
        );



        Pane root = new Pane();
        root.setBackground(new Background(backgroundImage));
        root.setPrefSize(800, 600);

        Label qLabel = new Label(challenge.question);
        TextField answerField = new TextField();
        answerField.setLayoutX(450);
        answerField.setLayoutY(510);
        answerField.setPromptText("Your answer");
        Button submitBtn = new Button("Submit");
        submitBtn.setLayoutX(575);
        submitBtn.setLayoutY(510);

        submitBtn.setOnAction(e -> {
            int earned = 0;
            if (challenge instanceof RiddleChallenge rc) {
                if (rc.checkAnswer(answerField.getText())) earned = rc.getPoints();
            } else if (challenge instanceof MathChallenge mc) {
                try {
                    int ans = Integer.parseInt(answerField.getText());
                    if (mc.checkAnswer(ans)) earned = mc.getPoints();
                } catch (NumberFormatException ignored) {}
            } else if (challenge instanceof MiniGameChallenge mgc ) {

                mgc.play(onComplete);
                return;
            }

            challenge.points = earned;
            onComplete.run();
            startTchallange();
        });

        root.getChildren().addAll(qLabel, answerField, submitBtn);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setTitle("Challenge 2");
        primaryStage.show();
    }


    public static void displayThirdlocation(Challenge challenge, Runnable onComplete) {
        if(gender){
            Image bgImage = new Image(SceneManager.class.getResource("/images/cave game male.jpg").toExternalForm());
            BackgroundImage backgroundImage = new BackgroundImage(
                    bgImage,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(800, 600, false, false, false, false)
            );
        }
        Image bgImage = new Image(SceneManager.class.getResource("/images/cave games female.jpg").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(800, 600, false, false, false, false)
        );



        Pane root = new Pane();
        root.setBackground(new Background(backgroundImage));
        root.setPrefSize(800, 600);

        Label qLabel = new Label(challenge.question);
        TextField answerField = new TextField();
        answerField.setLayoutX(450);
        answerField.setLayoutY(510);
        answerField.setPromptText("Your answer");
        Button submitBtn = new Button("Submit");
        submitBtn.setLayoutX(575);
        submitBtn.setLayoutY(510);


        submitBtn.setOnAction(e -> {
            int earned = 0;
            if (challenge instanceof RiddleChallenge rc) {
                if (rc.checkAnswer(answerField.getText())) earned = rc.getPoints();
            } else if (challenge instanceof MathChallenge mc) {
                try {
                    int ans = Integer.parseInt(answerField.getText());
                    if (mc.checkAnswer(ans)) earned = mc.getPoints();
                } catch (NumberFormatException ignored) {}
            } else if (challenge instanceof MiniGameChallenge mgc ) {
                mgc.play(onComplete);
                return;
            }

            challenge.points = earned;
            onComplete.run();
        });

        root.getChildren().addAll(qLabel, answerField, submitBtn);
        primaryStage.setScene(new Scene(root, 800, 600));
        primaryStage.setTitle("Challenge 3");
        primaryStage.show();
    }
}