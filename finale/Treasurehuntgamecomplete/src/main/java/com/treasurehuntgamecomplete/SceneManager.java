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
        // Load background image for character/difficulty screen
        Image bgImage = new Image(SceneManager.class.getResource("/images/fchoice.jpg").toExternalForm());
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
        
        // Continue Button over image's "Continue" label
        Button continueBtn = new Button();
        continueBtn.setLayoutX(305); // adjust based on image
        continueBtn.setLayoutY(340); // adjust based on image
        continueBtn.setPrefSize(200, 50);
        continueBtn.setStyle("-fx-background-color: transparent;");

        Button DifficultBtn = new Button();
        DifficultBtn.setLayoutX(305); // adjust based on image
        DifficultBtn.setLayoutY(240); // adjust based on image
        DifficultBtn.setPrefSize(200, 50);
        DifficultBtn.setStyle("-fx-background-color: transparent;");

        DifficultBtn.setOnAction(e -> {      //lemda equation
            showDifficulty();
        });

        // Add all components
        root.getChildren().addAll(DifficultBtn,  continueBtn);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
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
    private static void showDifficulty(){
        Image bgImage = new Image(SceneManager.class.getResource("/images/Difficulty.jpg").toExternalForm());
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

        // Continue Button over image's "Continue" label
        Button easyBtn = new Button();
        easyBtn.setLayoutX(305); // adjust based on image
        easyBtn.setLayoutY(205); // adjust based on image
        easyBtn.setPrefSize(200, 60);
        easyBtn.setStyle("-fx-background-color: transparent;");

        Button mediumBtn = new Button();
        mediumBtn.setLayoutX(305); // adjust based on image
        mediumBtn.setLayoutY(300); // adjust based on image
        mediumBtn.setPrefSize(200, 60);
        mediumBtn.setStyle("-fx-background-color: transparent;");

        Button hardtBtn = new Button();
        hardtBtn.setLayoutX(305); // adjust based on image
        hardtBtn.setLayoutY(385); // adjust based on image
        hardtBtn.setPrefSize(200, 60);
        hardtBtn.setStyle("-fx-background-color: transparent;");

        easyBtn.setOnAction(e -> {
            showCharacter();
        });
        root.getChildren().addAll(hardtBtn,easyBtn,mediumBtn);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

    }
    private static void showCharacter(){
        Image bgImage = new Image(SceneManager.class.getResource("/images/characters.jpg").toExternalForm());
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

        // Continue Button over image's "Continue" label
        Button FemaleBtn = new Button();
        FemaleBtn.setLayoutX(120); // adjust based on image
        FemaleBtn.setLayoutY(490); // adjust based on image
        FemaleBtn.setPrefSize(210, 60);
        FemaleBtn.setStyle("-fx-background-color: transparent;");

        Button maleBtn = new Button();
        maleBtn.setLayoutX(470); // adjust based on image
        maleBtn.setLayoutY(490); // adjust based on image
        maleBtn.setPrefSize(210, 60);
        maleBtn.setStyle("-fx-background-color: transparent;");



        maleBtn.setOnAction(e -> {
            Entername();
        });

        root.getChildren().addAll(FemaleBtn,maleBtn);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

    }
    private static void Entername(){
        Image bgImage = new Image(SceneManager.class.getResource("/images/Name.jpg").toExternalForm());
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

        TextField nameField = new TextField();
        nameField.setPromptText("Enter Name");
        nameField.setLayoutX(300);
        nameField.setLayoutY(300);
        nameField.setPrefWidth(200);

        // Continue Button over image's "Continue" label
        Button SubmitBtn = new Button();
        SubmitBtn.setLayoutX(320); // adjust based on image
        SubmitBtn.setLayoutY(420); // adjust based on image
        SubmitBtn.setPrefSize(150, 50);
        SubmitBtn.setStyle("-fx-background-color: blue;");




        root.getChildren().addAll(SubmitBtn,nameField);
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);

    }




}
