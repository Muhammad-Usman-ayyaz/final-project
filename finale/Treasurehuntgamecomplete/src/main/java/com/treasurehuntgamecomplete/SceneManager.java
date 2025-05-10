package com.treasurehuntgamecomplete;

import com.treasurehuntgamecomplete.challenges.*;
import com.treasurehuntgamecomplete.game.*;
import com.treasurehuntgamecomplete.utilities.ChallengeGUI;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import utilities.Difficulty;

import java.util.Objects;

public class SceneManager {
    private static Stage primaryStage;
    private static Player player;
    private static Map gameMap;
    private static Location currentLocation;

    public static void init(Stage stage) {
        primaryStage = stage;
    }

    public static void showMainMenu() {
        // Load the background image from resources
        Image bgImage = new Image(SceneManager.class.getResource("/images/mainmenu.jpg").toExternalForm());
        BackgroundImage backgroundImage = new BackgroundImage(
                bgImage,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(800, 600, false, false, false, false)
        );

        // Use Pane instead of VBox for absolute positioning
        Pane root = new Pane();
        root.setBackground(new Background(backgroundImage));
        root.setPrefSize(800, 600);

        // Transparent button over "START" area in the image (adjust position as needed)
        Button startButton = new Button();
        startButton.setLayoutX(330); // X-position of "START" button in the image
        startButton.setLayoutY(480); // Y-position of "START" button in the image
        startButton.setPrefSize(160, 50); // Width and height matching image "START" button
        startButton.setStyle("-fx-background-color: transparent");

        startButton.setOnAction(e -> showCharacterAndDifficultyScene());

        // Add the invisible button to the Pane
        root.getChildren().add(startButton);

        // Create scene and show
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Adventure Game - Main Menu");
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    private static void showCharacterAndDifficultyScene() {
//        Image bgImage = new Image(SceneManager.class.getResource("/images/mainmenu.jpg").toExternalForm());
//        BackgroundImage backgroundImage = new BackgroundImage(
//                bgImage,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundRepeat.NO_REPEAT,
//                BackgroundPosition.CENTER,
//                new BackgroundSize(800, 600, false, false, false, false)
//        );
        Pane root = new Pane();
        root.setBackground(new Background(backgroundImage));

        Label nameLabel = new Label("Enter Your Name:");
        TextField nameField = new TextField();

        Label charLabel = new Label("Choose Your Character:");
        ComboBox<String> characterBox = new ComboBox<>();
        characterBox.getItems().addAll("Warrior", "Wizard", "Explorer");

        Label diffLabel = new Label("Select Difficulty:");
        ComboBox<String> difficultyBox = new ComboBox<>();
        difficultyBox.getItems().addAll("EASY", "MEDIUM", "HARD");

        Button continueBtn = new Button("Continue");
        continueBtn.setOnAction(e -> {
            String name = nameField.getText().isEmpty() ? "Adventurer" : nameField.getText();
            player = new Player(name);
            setupGame(); // game setup
            showNextLocation(); // begin game
        });

        root.getChildren().addAll(nameLabel, nameField, charLabel, characterBox, diffLabel, difficultyBox, continueBtn);
        primaryStage.setScene(new Scene(root, 600, 400));
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
        root.setAlignment(Pos.CENTER);

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
        root.setAlignment(Pos.CENTER);

        Label label = new Label("🎮 Game Over!");
        Label score = new Label("Your final score: " + player.getScore());
        Button exitBtn = new Button("Exit");

        exitBtn.setOnAction(e -> primaryStage.close());
        root.getChildren().addAll(label, score, exitBtn);
        primaryStage.setScene(new Scene(root, 400, 300));
    }
}
