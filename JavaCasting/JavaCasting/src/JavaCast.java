import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.*;
import javafx.stage.Stage;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Timer;
import java.util.function.Consumer;
import static javafx.application.Platform.exit;

/**
 * This is the main class of the Java Casting Game. It will handle all GUI construction and code parsing. This will
 * bring together all classes that contribute to the game.
 *
 * Last Modified: 10 / 17 / 19
 *
 * @author Shirley Krogel
 */

public class JavaCast extends Application {

    // 1. World Window Initializations
    private Stage world = new Stage();

    //    a. World: Title Screen
    private Text title = new Text("JavaCast");
    private ImageView titleBackground = new ImageView(new Image("TitleScreen.png"));
    private Button newGameButton = new Button("New Game");
    private Button continueGameButton = new Button("Continue"); // Needs saved games.
    private Button optionsButton = new Button("Options");
    private Button exitGameButton = new Button("Quit Game");
    private StackPane titlePane = new StackPane();
    private Scene worldScene = new Scene(titlePane, 800, 800); // PH values

    //    b. World: Options Screen
    private ImageView optionsBackground = new ImageView(new Image("OptionScreen.png"));
    private Text optionsTitle = new Text();
    private Text masterVolumeLabel = new Text();
    private Slider masterVolumeSlider = new Slider();
    private Button optionsReturnToTitleButton = new Button("Return to Main Menu");
    private Button optionsReturnToGameButton = new Button(" Return to Game");
    private StackPane optionsPane = new StackPane();

    //    c. World: Game Screen
    private ImageView worldBackground = new ImageView(new Image("BlackScreen.png")); // Starting Screen
    private ImageView target = new ImageView(new Image("Classmate.png"));
    private Text dialogue = new Text();
    private VBox dialogueBorder = new VBox();
    private StackPane worldPane = new StackPane();
    private URL snailMusic = getClass().getResource("/SnailsContender2.wav");
    private MediaPlayer worldMusic = new MediaPlayer(new Media(snailMusic.toString()));

    //    d. World: Map Screen
    private ImageView mapView = new ImageView(new Image("Map.png"));
    private StackPane mapPane = new StackPane();

    // 2. Casting Console Initializations
    private Stage castingConsole = new Stage();
    private Text classCode = new Text();
    private TextField castBar = new TextField();
    private Text codexShortcut = new Text();
    private VBox castVBox = new VBox();
    private StackPane castPane = new StackPane();
    private Scene castScene = new Scene(castPane, 500, 200); // PH values

    // 3. Codex Initializations
    private Stage codex = new Stage();
    private StackPane codexPane = new StackPane();
    private ArrayList<Button> codexButtons = new ArrayList<>();
    private VBox codexOptions = new VBox();
    private ScrollPane codexTabScroll = new ScrollPane();
    private ScrollPane codexTextScroll = new ScrollPane();
    private Text codexTitle = new Text();
    private Text codexText = new Text();

    // 4. Logbook Initializations
    private Stage logbook = new Stage();

    //    a. Log: Chat Log
    private Text[] chatPassages = new Text[50];
    private Button chatToCodeButton = new Button("Switch to Code Log");
    private StackPane chatPane = new StackPane();
    private ScrollPane chatScroll = new ScrollPane(chatPane);
    private Scene logScene = new Scene(chatScroll, 900, 800); // PH values

    //    b. Log: Code Log
    private Text[] codePassages = new Text[50];
    private Button codeToChatButton = new Button("Switch to Chat Log");
    private StackPane codePane = new StackPane();
    private ScrollPane codeScroll = new ScrollPane(codePane);

    // Familiar Window Initializations
    private Stage familiarStage = new Stage();
    private Text familiarMessage = new Text();
    private ImageView familiarAppearance = new ImageView();
    private StackPane familiarPane = new StackPane();
    private Scene familiarScene = new Scene(familiarPane, 400, 200); // PH values
    private String hintMessage;

    // Error Message Window Initialization
    private Stage errorStage = new Stage();
    private Text errorMessage = new Text();
    private StackPane errorPane = new StackPane();
    private Scene errorScene = new Scene(errorPane, 300, 200); // PH values

    // Owner Window Initializations
    private Stage ownerStage = new Stage();
    private Text ownerMessage = new Text();
    private StackPane ownerPane = new StackPane();
    private Scene ownerScene = new Scene(ownerPane, 300, 200); // PH values

    // User Initialization
    private User user;

    //Scenario Initialization
    private Consumer<String> scenarioCommands = (command) -> {
        user.getUserCommands().accept(command);
    };
    private HashMap<String, HashMap<String, Item>> environmentObjects = new HashMap<>();
    private ScenarioLibrary library;
    private Timer timer  = new Timer();
    private String nextScenario = "Start";

    //Continue Game Initialization
    private PrintWriter save;

    private void standardButton(Button button, StackPane pane) {
        button.setAlignment(Pos.CENTER);
        button.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        button.prefWidthProperty().bind(pane.widthProperty().divide(4));
        button.prefHeightProperty().bind(pane.heightProperty().divide(10));
        button.setWrapText(true);
    }

    private void standardText(Text text) {
        text.setTextAlignment(TextAlignment.LEFT);
        text.setFont(Font.font("Verdana", FontPosture.ITALIC, 16));
    }

    private void bindBackground(ImageView background, StackPane pane) {
        pane.getChildren().add(background);
        background.fitWidthProperty().bind(pane.widthProperty());
        background.fitHeightProperty().bind(pane.heightProperty());
        background.toBack();
    }

    public void sayMessage(String message) {
        if(!message.isEmpty()) {
            for (int i = chatPassages.length - 1; i > 0; i--) {
                chatPassages[i].setText(chatPassages[i - 1].getText());
            }
            chatPassages[0].setText(dialogue.getText());
            dialogue.setText(message);
        }
    }

    private void updateCodeLog() {
        for (int i = codePassages.length - 1; i > 0; i--) {
            codePassages[i].setText(codePassages[i-1].getText());
        }
        codePassages[0].setText(castBar.getText());
    }

    public void setTarget (Image newTarget) {
        target.setImage(newTarget);
    }

    public void setScenarioCommands (Consumer<String> commands) {
        this.scenarioCommands = commands;
    }

    public void setWorldScene(String paneName) {
        if(paneName.equals("World")) {
            worldScene.setRoot(worldPane);
        }
        else if(paneName.equals("Map")) {
            worldScene.setRoot(mapPane);
        }
    }

    public void setWorldBackground(String location) {
        switch(location) {
            case "Blackout":
                worldBackground.setImage(new Image("BlackScreen.png"));
                break;
            case "Dorms":
                worldBackground.setImage(new Image("Dorms.png"));
                break;
            case "Auditorium":
                worldBackground.setImage(new Image("Auditorium.png"));
                break;
            case "Bakery":
                worldBackground.setImage(new Image("Ruben'sBakery2.png"));
                break;
            case "Village":
                worldBackground.setImage(new Image("MushroomVillage2.png"));
                break;
            case "Studio":
                worldBackground.setImage(new Image("SpruceStudio.png"));

        }
    }

    public void updateMapView() {
        if(user.getQualities().get("AuditoriumKnown") && !user.getQualities().get("MarketKnown") &&
                !user.getQualities().get("BakeryKnown") &&
                !user.getQualities().get("Novelist'sHouseKnown") && !user.getQualities().get("MushroomVillageKnown")  &&
                !user.getQualities().get("ConstructionSiteKnown") && !user.getQualities().get("PostOfficeKnown") &&
                !user.getQualities().get("Artist'sStudioKnown") && !user.getQualities().get("ScienceLab") &&
                !user.getQualities().get("LibraryKnown") && !user.getQualities().get("ArtGalleryKnown") &&
                !user.getQualities().get("SnowyHillKnown") && !user.getQualities().get("SuspiciousHoleKnown") &&
                !user.getQualities().get("MadLabKnown") && !user.getQualities().get("DarkForestKnown") &&
                !user.getQualities().get("MountainattheEndoftheWorldKnown")) {
            mapView.setImage(new Image(getClass().getResource("MapAud.png").toExternalForm()));
        } else if(user.getQualities().get("AuditoriumKnown") && user.getQualities().get("MarketKnown") &&
                !user.getQualities().get("BakeryKnown") &&
                !user.getQualities().get("Novelist'sHouseKnown") && !user.getQualities().get("MushroomVillageKnown")  &&
                !user.getQualities().get("ConstructionSiteKnown") && !user.getQualities().get("PostOfficeKnown") &&
                !user.getQualities().get("Artist'sStudioKnown") && !user.getQualities().get("ScienceLab") &&
                !user.getQualities().get("LibraryKnown") && !user.getQualities().get("ArtGalleryKnown") &&
                !user.getQualities().get("SnowyHillKnown") && !user.getQualities().get("SuspiciousHoleKnown") &&
                !user.getQualities().get("MadLabKnown") && !user.getQualities().get("DarkForestKnown") &&
                !user.getQualities().get("MountainattheEndoftheWorldKnown")) {
            mapView.setImage(new Image("MapAudMar.png"));
        } else if(user.getQualities().get("AuditoriumKnown") && user.getQualities().get("BakeryKnown") &&
                !user.getQualities().get("Novelist'sHouseKnown") && !user.getQualities().get("MushroomVillageKnown")  &&
                !user.getQualities().get("ConstructionSiteKnown") && !user.getQualities().get("PostOfficeKnown") &&
                !user.getQualities().get("Artist'sStudioKnown") && !user.getQualities().get("ScienceLab") &&
                !user.getQualities().get("LibraryKnown") && !user.getQualities().get("ArtGalleryKnown") &&
                !user.getQualities().get("SnowyHillKnown") && !user.getQualities().get("SuspiciousHoleKnown") &&
                !user.getQualities().get("MadLabKnown") && !user.getQualities().get("DarkForestKnown") &&
                !user.getQualities().get("MountainattheEndoftheWorldKnown")) {
            mapView.setImage(new Image("MapAudBak.png"));
        } else if(user.getQualities().get("AuditoriumKnown") && user.getQualities().get("BakeryKnown") &&
                !user.getQualities().get("Novelist'sHouseKnown") && user.getQualities().get("MushroomVillageKnown")  &&
                !user.getQualities().get("ConstructionSiteKnown") && !user.getQualities().get("PostOfficeKnown") &&
                !user.getQualities().get("Artist'sStudioKnown") && !user.getQualities().get("ScienceLab") &&
                !user.getQualities().get("LibraryKnown") && !user.getQualities().get("ArtGalleryKnown") &&
                !user.getQualities().get("SnowyHillKnown") && !user.getQualities().get("SuspiciousHoleKnown") &&
                !user.getQualities().get("MadLabKnown") && !user.getQualities().get("DarkForestKnown") &&
                !user.getQualities().get("MountainattheEndoftheWorldKnown")) {
            mapView.setImage(new Image("MapAudBakVil.png"));
        } else if(user.getQualities().get("AuditoriumKnown") && user.getQualities().get("BakeryKnown") &&
                !user.getQualities().get("Novelist'sHouseKnown") && user.getQualities().get("MushroomVillageKnown")  &&
                !user.getQualities().get("ConstructionSiteKnown") && !user.getQualities().get("PostOfficeKnown") &&
                user.getQualities().get("Artist'sStudioKnown") && !user.getQualities().get("ScienceLab") &&
                !user.getQualities().get("LibraryKnown") && !user.getQualities().get("ArtGalleryKnown") &&
                !user.getQualities().get("SnowyHillKnown") && !user.getQualities().get("SuspiciousHoleKnown") &&
                !user.getQualities().get("MadLabKnown") && !user.getQualities().get("DarkForestKnown") &&
                !user.getQualities().get("MountainattheEndoftheWorldKnown")) {
            mapView.setImage(new Image("MapAudBakVilStu.png"));
        }


    }

    public void setFamiliarAppearance(Image familiar) {
        familiarAppearance.setImage(familiar);
    }

    public void setNextScenario(String key) {
        nextScenario = key;
    }

    public void setCodexTitle(String title) {
        codexTitle.setText(title);
    }

    public void setCodexText(String text){
        codexText.setText(text);
    }

    public void setHintMessage(String hint) {
        hintMessage = hint;
    }

    public String getHintMessage() {
        return hintMessage;
    }

    public String getCodexTitle(){
        return codexTitle.getText();
    }

    public String getNextScenario() {
        return nextScenario;
    }

    public Stage getCodex() {
        return codex;
    }

    public Stage getLogbook() {
        return logbook;
    }

    public void familiarSays(String message) {
        if(user.getFamiliar() != null) {
            familiarMessage.setText(message);
            if (!familiarStage.isShowing()) {
                familiarStage.show();
            }
        }
    }

    public Timer getTimer() {
        return timer;
    }

    public HashMap<String, Scenario> getLibrary() {
        return library.getLibrary();
    }

    public HashMap<String, HashMap<String, Item>> getEnvironmentObjects() {
        return environmentObjects;
    }

    public String getWorldScene() {
        if(worldScene.getRoot().equals(worldPane)) {
            return "World";
        }
        else if(worldScene.getRoot().equals(mapPane)) {
            return "Map";
        }
        return null;
    }

    public User getUser() {
        return user;
    }

    public ImageView getTarget() {
        return target;
    }

    public MediaPlayer getWorldMusic() {
        return worldMusic;
    }

    public void setWorldMusic(String media) {
        worldMusic.pause();
        worldMusic.dispose();
        worldMusic = new MediaPlayer(new Media(media));
        worldMusic.setCycleCount(MediaPlayer.INDEFINITE);
    }

    public void updateCodex() {
        if(user.getQualities().get("click") && !user.getQualities().get("Button")) {
            codexButtons.get(1).setVisible(true);
        } else {
            codexButtons.get(1).setVisible(false);
        }
        if(user.getQualities().get("Log")) {
            codexButtons.get(2).setVisible(true);
        } else {
            codexButtons.get(2).setVisible(false);
        }
        if(user.getQualities().get("Codex")) {
            codexButtons.get(3).setVisible(true);
        } else {
            codexButtons.get(3).setVisible(false);
        }
        if(user.getQualities().get("Familiar")) {
            codexButtons.get(4).setVisible(true);
        } else {
            codexButtons.get(4).setVisible(false);
        }
        if(user.getQualities().get("Map")) {
            codexButtons.get(5).setVisible(true);
        } else {
            codexButtons.get(5).setVisible(false);
        }
        if(user.getQualities().get("ClassesInfo")) {
            codexButtons.get(6).setVisible(true);
        } else {
            codexButtons.get(6).setVisible(false);
        }
        if(user.getQualities().get("Method")) {
            codexButtons.get(7).setVisible(true);
        } else {
            codexButtons.get(7).setVisible(false);
        }
        if(user.getQualities().get("Constructor")) {
            codexButtons.get(8).setVisible(true);
        } else {
            codexButtons.get(8).setVisible(false);
        }
        if(user.getQualities().get("Donut")) {
            codexButtons.get(9).setVisible(true);
        } else {
            codexButtons.get(9).setVisible(false);
        }
        if(user.getQualities().get("Primitive")) {
            codexButtons.get(10).setVisible(true);
        } else {
            codexButtons.get(10).setVisible(false);
        }
        if(user.getQualities().get("Arrays")) {
            codexButtons.get(11).setVisible(true);
        } else {
            codexButtons.get(11).setVisible(false);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        // 1. Setting up the World Stage

        world.setScene(worldScene);

        //    a. Setting up the Title Screen

        bindBackground(titleBackground, titlePane);

        DropShadow ds = new DropShadow();
        ds.setOffsetY(10.0f);
        ds.setColor(Color.color(0.4f, 0.4f, 0.4f));
        title.setFont(Font.font("Verdana", FontWeight.BOLD, 90));
        title.setFill(Color.FORESTGREEN);
        title.setEffect(ds);
        title.setCache(true);
        title.setStroke(Color.BLACK);
        title.setStrokeWidth(2);

        VBox titleVBox = new VBox();
        HBox titleHBox = new HBox();
        titleHBox.getChildren().add(newGameButton);
        /**File savefile = new File("savefile.txt");
        if (savefile.exists()) {
            try (Scanner read = new Scanner(savefile)) {
                user = new User(this);
                if (read.next().equals("User:")) {
                    while(!read.next().equals("Object:")) {

                    }
                }
            }
            // Add save games
        }
         */
        titleHBox.getChildren().addAll(optionsButton, exitGameButton);
        standardButton(newGameButton, titlePane);
        standardButton(continueGameButton, titlePane);
        standardButton(optionsButton, titlePane);
        standardButton(exitGameButton, titlePane);
        titleHBox.setAlignment(Pos.TOP_CENTER);

        titleVBox.getChildren().addAll(title, titleHBox);
        titleVBox.setAlignment(Pos.TOP_CENTER);

        titlePane.getChildren().add(titleVBox);

        //    b. Setting up the Options Screen

        bindBackground(optionsBackground, optionsPane);
        VBox optionsVBox = new VBox();
        HBox masterVolumeHBox = new HBox();

        //      b1. Setting up the volume slider
        masterVolumeSlider.setMax(1); // Sliders works with 0-100%, anything higher than 1 is just 100%
        masterVolumeSlider.setMin(0);
        masterVolumeSlider.setValue(0.5); // 100% can be pretty loud
        masterVolumeSlider.setShowTickLabels(true);
        masterVolumeSlider.setShowTickMarks(true);
        masterVolumeSlider.setMajorTickUnit(0.5);
        masterVolumeSlider.setMinorTickCount(5);
        masterVolumeSlider.setBlockIncrement(0.10);
        masterVolumeLabel.setText("Master Volume: ");
        masterVolumeLabel.setFill(Color.WHITE);
        masterVolumeLabel.setStroke(Color.BLACK);
        masterVolumeLabel.setTextAlignment(TextAlignment.LEFT);
        masterVolumeLabel.setFont(Font.font("Verdana", FontPosture.REGULAR, 30));
        masterVolumeHBox.getChildren().addAll(masterVolumeLabel, masterVolumeSlider);
        masterVolumeHBox.setAlignment(Pos.TOP_CENTER);
        worldMusic.volumeProperty().bind(masterVolumeSlider.valueProperty());
        worldMusic.setCycleCount(MediaPlayer.INDEFINITE);
        //      b1. End of slider setup

        optionsVBox.getChildren().addAll(optionsTitle, masterVolumeHBox, optionsReturnToTitleButton);
        standardButton(optionsReturnToTitleButton, optionsPane);
        standardButton(optionsReturnToGameButton, optionsPane);
        optionsVBox.setAlignment(Pos.TOP_CENTER);
        optionsPane.getChildren().add(optionsVBox);

        //    c. Setting up the Game Screen

        VBox worldVBox = new VBox();
        dialogueBorder.setStyle("-fx-padding: 10;" + "-fx-border-style: solid inside;" + "-fx-border-width: 2;" +
                "-fx-border-insets: 5;" + "-fx-border-radius: 5;" + "-fx-border-color: #000000;");
        dialogueBorder.getChildren().add(dialogue);
        standardText(dialogue);
        worldVBox.getChildren().addAll(worldBackground, dialogueBorder);
        VBox liningVBox = new VBox();
        liningVBox.getChildren().addAll(target, dialogueBorder);
        worldBackground.fitWidthProperty().bind(worldPane.widthProperty());
        worldBackground.fitHeightProperty().bind(worldPane.heightProperty().divide(1.5));
        target.fitWidthProperty().bind(worldPane.widthProperty());
        target.fitHeightProperty().bind(worldPane.heightProperty().divide(1.5));
        target.setVisible(false); // default for new game
        dialogueBorder.prefWidthProperty().bind(worldPane.widthProperty());
        dialogueBorder.prefHeightProperty().bind(worldPane.heightProperty().divide(3));
        dialogue.wrappingWidthProperty().bind(dialogueBorder.widthProperty().multiply(0.9));
        worldPane.getChildren().addAll(worldVBox, liningVBox);
        library = new ScenarioLibrary(this);

        //    d. Setting up the Map Screen
        bindBackground(mapView, mapPane);
        HBox mapHBox = new HBox();
        mapHBox.getChildren().add(mapView);
        mapPane.getChildren().add(mapHBox);

        // 2. Setting up the Casting Console Stage

        codexShortcut.setText("Open the Codex: codex.show();\nAdvance Time: wait();");
        codexShortcut.setTextAlignment(TextAlignment.CENTER);
        castVBox.getChildren().addAll(classCode, castBar, codexShortcut);
        castPane.getChildren().add(castVBox);
        castVBox.setAlignment(Pos.CENTER);
        castingConsole.setScene(castScene);

        // Setting up the Codex
        codexTitle.setTextAlignment(TextAlignment.LEFT);
        codexTitle.setFont(Font.font("Verdana", FontWeight.BOLD, 25));
        standardText(codexText);
        codexButtons.addAll(CodexLibrary.getButtons(codexTabScroll, this));
        for(int i = 0; i < codexButtons.size(); i++) {
            codexOptions.getChildren().add(codexButtons.get(i));
        }
        codexTabScroll.setContent(codexOptions);
        HBox codexHbox = new HBox();
        VBox codexTextVBox = new VBox();
        codexTextVBox.getChildren().addAll(codexTitle, codexText);
        codexText.setWrappingWidth(550);
        codexTabScroll.minHeightProperty().bind(codexHbox.prefHeightProperty());
        codexTabScroll.setPrefWidth(200);
        codexTextScroll.prefWidthProperty().bind(codexPane.widthProperty().subtract(codexTabScroll.getPrefWidth()));
        codexTextScroll.setContent(codexTextVBox);
        codexHbox.getChildren().addAll(codexTabScroll, codexTextScroll);
        codexPane.getChildren().add(codexHbox);
        codex.setScene(new Scene(codexPane, 800, 800));

        // Later Goal

        // 4. Setting up the Logbook

        logbook.setScene(logScene);

        //    a. Setting up the Chat Log

        VBox chatVBox = new VBox();
        for(int i = chatPassages.length - 1; i >= 0; i--) {
            chatPassages[i] = new Text();
            standardText(chatPassages[i]);
            chatVBox.getChildren().add(chatPassages[i]);
        }
        chatVBox.getChildren().add(chatToCodeButton);
        standardButton(chatToCodeButton, chatPane);
        chatPane.getChildren().add(chatVBox);
        chatVBox.prefHeightProperty().bind(chatScroll.heightProperty());
        chatVBox.prefWidthProperty().bind(chatScroll.widthProperty().subtract(15));

        //    b. Setting up the Code Log

        VBox codeVBox = new VBox();
        for(int i = codePassages.length - 1; i >= 0; i--) {
            codePassages[i] = new Text();
            standardText(codePassages[i]);
            codeVBox.getChildren().add(codePassages[i]);
        }
        codeVBox.getChildren().add(codeToChatButton);
        standardButton(codeToChatButton, codePane);
        codePane.getChildren().add(codeVBox);
        codeVBox.prefHeightProperty().bind(codeScroll.heightProperty());
        codeVBox.prefWidthProperty().bind(codeScroll.widthProperty().subtract(15));

        // 5. Setting up the Familiar Window

        familiarStage.setScene(familiarScene);
        HBox familiarHBox = new HBox();
        standardText(familiarMessage);
        familiarMessage.wrappingWidthProperty().bind(familiarScene.widthProperty().subtract(210));
        familiarHBox.getChildren().addAll(familiarMessage, familiarAppearance);
        familiarPane.getChildren().add(familiarHBox);

        // 6. Setting up the Error Window

        errorStage.setScene(errorScene);
        standardText(errorMessage);
        errorPane.getChildren().add(errorMessage);

        // 7. Setting up the Owner Window

        ownerStage.setScene(ownerScene);
        standardText(ownerMessage);
        ownerPane.getChildren().add(ownerMessage);

        // Setting the request to close out of a required window to cause all game windows to close

        world.setOnCloseRequest(e -> {
            castingConsole.close();
            codex.close();
            logbook.close();
            familiarStage.close();
            ownerStage.close();
            System.exit(0);
        });

        castingConsole.setOnCloseRequest(e -> {
            world.close();
            codex.close();
            logbook.close();
            familiarStage.close();
            ownerStage.close();
            System.exit(0);
        });

        // Setting up the buttons
        newGameButton.setOnAction(e->{
            user = new User(this);
            scenarioCommands = (command) -> {
                user.getUserCommands().accept(command);
            };
            worldScene.setRoot(worldPane);
            castingConsole.show();
            timer.schedule(library.getLibrary().get("Start"), 5000);
        });

        // Set up saves

        optionsButton.setOnAction(e->{
            worldScene.setRoot(optionsPane);
        });

        exitGameButton.setOnAction(e->{
            world.close();
            castingConsole.close();
            codex.close();
            logbook.close();
            familiarStage.close();
            ownerStage.close();
            exit();
        });

        optionsReturnToTitleButton.setOnAction(e->{
            worldScene.setRoot(titlePane);
        });

        optionsReturnToGameButton.setOnAction(e->{
            worldScene.setRoot(worldPane);
        });

        chatToCodeButton.setOnAction(e->{
            logScene.setRoot(codeScroll);
            logbook.setTitle("Code Log");
        });

        codeToChatButton.setOnAction(e->{
            logScene.setRoot(chatScroll);
            logbook.setTitle("Chat Log");
        });

        // User commands
        castBar.setOnAction(e->{
            if(!castBar.getText().equals("wait();")) {
                updateCodeLog();
            }
            scenarioCommands.accept(castBar.getText());
            if(codexTitle.getText().equals("Environment") && codex.isShowing()) {
                String newText = user.getLocation() + ":";
                if (environmentObjects.get(user.getLocation()) != null) {
                    for (String objectName : environmentObjects.get(user.getLocation()).keySet()) {
                        if (environmentObjects.get(user.getLocation()).get(objectName) != null) {
                            newText = newText + "\nClass Name: " + environmentObjects.get(user.getLocation()).get(objectName).getClassName() + "\nVariable Name: " + objectName;
                            if(environmentObjects.get(user.getLocation()).get(objectName).getVariables() != null && !(environmentObjects.get(user.getLocation()).get(objectName).getVariables().get("Return") instanceof Null)) {
                                newText = newText + "\nReturn Value: " + environmentObjects.get(user.getLocation()).get(objectName).getVariables().get("Return").toString();
                            }
                            newText = newText + "\n";
                        }
                    }
                }
                newText = newText + "\n\nUser:";
                if (environmentObjects.get("User") != null) {
                    for (String objectName : environmentObjects.get("User").keySet()) {
                        if (environmentObjects.get("User").get(objectName) != null) {
                            newText = newText + "\nClass Name: " + environmentObjects.get("User").get(objectName).getClassName() + "\nVariable Name: " + objectName;
                            if(environmentObjects.get("User").get(objectName).getVariables() != null && !(environmentObjects.get("User").get(objectName).getVariables().get("Return") instanceof Null)) {
                                newText = newText + "\nReturn Value: " + environmentObjects.get("User").get(objectName).getVariables().get("Return").toString();
                            }
                            newText = newText + "\n";
                        }
                    }
                    codexText.setText(newText);
                }
            }
            updateCodex();
            updateMapView();
        });

        // Make some windows unable to be resized

        world.setResizable(false);
        castingConsole.setResizable(false);

        // Final commands to display the windows.

        world.setTitle("World");
        castingConsole.setTitle("Casting Console");
        codex.setTitle("Magical Codex");
        logbook.setTitle("Chat Log");
        familiarStage.setTitle("Familiar");
        // Error stage will be named based on error
        // Owner stage will remain blank
        world.show();
    }
}

