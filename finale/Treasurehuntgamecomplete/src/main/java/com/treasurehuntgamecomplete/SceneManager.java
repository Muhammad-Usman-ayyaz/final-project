package com.treasurehuntgamecomplete;

import com.treasurehuntgamecomplete.challenges.*;
import com.treasurehuntgamecomplete.game.Player;
import com.treasurehuntgamecomplete.game.*;
import com.treasurehuntgamecomplete.utilities.ChallengeGUI;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import utilities.Difficulty;

public class SceneManager {
    private static Stage primaryStage;
    private static Player player;
    private static Map gameMap;
    private static Location currentLocation;

    public static void init(Stage stage) {
        primaryStage = stage;
    }

    public static void showMainMenu() {
        VBox root = new VBox(10);
        Label label = new Label("🌍 Welcome to the Adventure Game");
        TextField nameField = new TextField();
        nameField.setPromptText("Enter your name");

        Button startBtn = new Button("Start Adventure");
        startBtn.setOnAction(e -> {
            player = new Player(nameField.getText().isEmpty() ? "Adventurer" : nameField.getText());
            setupGame();
            showNextLocation();
        });

        root.getChildren().addAll(label, nameField, startBtn);
        primaryStage.setScene(new Scene(root, 400, 300));
        primaryStage.setTitle("Adventure Game");
        primaryStage.show();
    }

    private static void setupGame() {
        player.addItem("Calculator");
        player.addItem("Lucky Coin");

        RiddleChallenge riddle = new RiddleChallenge("What has keys but can't open locks?", "Piano", 10, Difficulty.EASY);
        MathChallenge math = new MathChallenge("5 + 3 * 2", 11, 15, Difficulty.MEDIUM);
        MiniGameChallenge miniGame = new MiniGameChallenge("Guess Number", 20, Difficulty.HARD);

        riddle.setPlayer(player);
        math.setPlayer(player);
        miniGame.setPlayer(player);

        Location loc1 = new Location("Forest", "A dark and spooky forest.", riddle);
        Location loc2 = new Location("Cave", "A hidden cave with mysterious symbols.", math);
        Location loc3 = new Location("Castle", "An ancient castle with secrets.", miniGame);

        gameMap = new Map();
        gameMap.addLocation(loc1);
        gameMap.addLocation(loc2);
        gameMap.addLocation(loc3);
    }

    public static void showNextLocation() {
        if (!gameMap.hasNextLocation()) {
            showGameOver();
            return;
        }

        currentLocation = gameMap.getNextLocation();
        VBox root = new VBox(10);
        Label name = new Label("📍 Location: " + currentLocation.getName());
        Label desc = new Label(currentLocation.getDescription());
        Button doChallenge = new Button("Do Challenge");

        doChallenge.setOnAction(e -> {
            ChallengeGUI.display(currentLocation.getChallenge(), () -> {
                player.increaseScore(currentLocation.getChallenge().getPoints());
                showNextLocation();
            });
        });

        root.getChildren().addAll(name, desc, doChallenge);
        primaryStage.setScene(new Scene(root, 400, 300));
    }

    private static void showGameOver() {
        VBox root = new VBox(10);
        Label label = new Label("🎮 Game Over!");
        Label score = new Label("Your final score: " + player.getScore());
        Button exitBtn = new Button("Exit");

        exitBtn.setOnAction(e -> primaryStage.close());
        root.getChildren().addAll(label, score, exitBtn);

        primaryStage.setScene(new Scene(root, 400, 300));
    }
}
