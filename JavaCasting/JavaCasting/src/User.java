import javafx.scene.control.Button;
import javafx.scene.image.Image;

import java.time.chrono.MinguoDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Consumer;
import java.util.regex.Pattern;

/**
 * This program contains the player. The name User is to relate it to how the player can be referenced within the game.
 *
 * Last Modified: 10 / 17 / 19
 *
 * @author Shirley Krogel
 */

public class User {

    private HashMap<String, Boolean> qualities = new HashMap<>();
    private ArrayList<String> classLibrary = new ArrayList<>(Arrays.asList("Button", "Donut"));
    private Consumer<String> userCommands;
    private JavaCast jc;
    private String location;
    private String familiar;

    public User(JavaCast jc){
        location = "Dorms";
        this.jc = jc;

        qualities.put("wait", false);
        qualities.put("click", false);
        qualities.put("Log", false);
        qualities.put("Codex", false);
        qualities.put("Familiar", false);
        qualities.put("Map", false);
        qualities.put("ClassesInfo", false);
        qualities.put("Method", false);
        qualities.put("Constructor", false);
        qualities.put("String", false);
        qualities.put("Donut", false);
        qualities.put("User", false);
        qualities.put("Primitive", false);
        qualities.put("Arrays", false);
        qualities.put("Wrapper", false);
        qualities.put("ArrayList", false);
        qualities.put("Comparators", false);
        qualities.put("For Loop", false);
        qualities.put("For Each Loop", false);
        qualities.put("While Loop", false);
        qualities.put("Do While Loop", false);
        qualities.put("Print Method", false);
        qualities.put(".toString", false);
        qualities.put("Text", false);
        qualities.put("Font", false);
        qualities.put("Slider", false);
        qualities.put("MediaPlayer", false);
        qualities.put("Button", false);
        qualities.put("Scanner", false);
        qualities.put("PrintWriter", false);
        qualities.put("File", false);
        qualities.put("Properties", false);
        qualities.put("Binding", false);
        qualities.put("Casting", false);
        qualities.put("ExtendvImplement", false);
        qualities.put("Bytes", false);
        qualities.put("Binary", false);
        qualities.put("Circuits", false);
        qualities.put("Data Structures", false);
        qualities.put("Own Type", false);
        qualities.put("Linked List", false);
        qualities.put("Doubly Linked List", false);
        qualities.put("Linked Tree", false);
        qualities.put("Tree List", false);
        qualities.put("Image", false);
        qualities.put("ImageView", false);
        qualities.put("Background", false);
        qualities.put("BackgroundImage", false);
        qualities.put("Coordinates", false);
        qualities.put("Circle", false);
        qualities.put("Rectangle", false);
        qualities.put("Triangle", false);
        qualities.put("Sort Methods", false);
        qualities.put("DestructivevNonDestructive", false);
        qualities.put("SearchMethods", false);
        qualities.put("Stage", false);
        qualities.put("Scene", false);
        qualities.put("GridPane", false);
        qualities.put("StackPane", false);
        qualities.put("BorderPane", false);
        qualities.put("TilePane", false);
        qualities.put("ScrollPane", false);
        qualities.put("Consumer", false);
        qualities.put("Generics", false);

        qualities.put("ConstructorPractice", false);
        qualities.put("MethodPractice", false);
        qualities.put("ArrayInsertPractice", false);

        qualities.put("Dorms", false);
        qualities.put("Auditorium", false);
        qualities.put("Market", false);
        qualities.put("Bakery", false);
        qualities.put("Novelist'sHouse", false);
        qualities.put("MushroomVillage", false);
        qualities.put("ConstructionSite", false);
        qualities.put("PostOffice", false);
        qualities.put("Artist'sStudio", false);
        qualities.put("ScienceLab", false);
        qualities.put("Library", false);
        qualities.put("ArtGallery", false);
        qualities.put("SnowyHill", false);
        qualities.put("SuspiciousHole", false);
        qualities.put("MadLab", false);
        qualities.put("DarkForest", false);
        qualities.put("MountainattheEndoftheWorld", false);

        qualities.put("AuditoriumKnown", false);
        qualities.put("MarketKnown", false);
        qualities.put("BakeryKnown", false);
        qualities.put("Novelist'sHouseKnown", false);
        qualities.put("MushroomVillageKnown", false);
        qualities.put("ConstructionSiteKnown", false);
        qualities.put("PostOfficeKnown", false);
        qualities.put("Artist'sStudioKnown", false);
        qualities.put("ScienceLabKnown", false);
        qualities.put("LibraryKnown", false);
        qualities.put("ArtGalleryKnown", false);
        qualities.put("SnowyHillKnown", false);
        qualities.put("SuspiciousHoleKnown", false);
        qualities.put("MadLabKnown", false);
        qualities.put("DarkForestKnown", false);
        qualities.put("MountainattheEndoftheWorldKnown", false);

        userCommands = (command) -> {
            if (command.equals("codex.show();")) {
                jc.getCodex().show();
            }
            else if (command.equals("logbook.show();") && qualities.get("Log")) {
                jc.getLogbook().show();
            }
            else if (command.equals("wait();") && qualities.get("wait")) {
                if(jc.getNextScenario() != null) {
                    jc.getTimer().schedule(jc.getLibrary().get(jc.getNextScenario()), 0);
                    jc.setNextScenario(null);
                }
                else {
                    jc.familiarSays("Boss? I think people are waiting for you to do something. You can check" +
                                " the chat logs or ask me if you're not sure what is expected.");
                }
            }
            else if (command.equals("map.show();") && qualities.get("Map")) {
                jc.updateMapView();
                jc.setWorldScene("Map");
            }
            else if (command.equals("map.goTo(\"Auditorium\");") && qualities.get(location) &&
                    jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "Auditorium";
                jc.setWorldBackground("Auditorium");
                jc.getTarget().setVisible(false);
                jc.sayMessage("");
            }
            else if (command.equals("map.goTo(\"Market\");") && qualities.get("Auditorium") &&
                    jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "Bakery";
                jc.getTimer().schedule(jc.getLibrary().get("Busy"), 0);
            }
            else if (command.equals("map.goTo(\"Bakery\");") && qualities.get(location)
                    && qualities.get("Auditorium") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "Bakery";
                jc.setWorldBackground("Bakery");
                jc.getTarget().setVisible(false);
                jc.sayMessage("");
            }
            // **Remember to set other locations to their first scenarios**
            else if (command.equals("map.goTo(\"Novelist'sHouse\");") && qualities.get(location) &&
                    qualities.get("Bakery") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "Novelist'sHouse";
                jc.setWorldBackground("Novelist'sHouse");
                if(qualities.get("Novelist'sHouse")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.goTo(\"ConstructionSite\");") && qualities.get(location) &&
                    qualities.get("Bakery") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "ConstructionSite";
                jc.setWorldBackground("ConstructionSite");
                if(qualities.get("ConstructionSite")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.goTo(\"Village\");") && qualities.get(location) &&
                    qualities.get("Bakery") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "MushroomVillage";
                jc.setWorldBackground("Village");
                if(qualities.get("MushroomVillage")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                } else {
                    jc.getTimer().schedule(jc.getLibrary().get("Village"), 0);
                }
            }
            else if (command.equals("map.goTo(\"PostOffice\");") && qualities.get(location) &&
                    qualities.get("Novelist'sHouse") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "PostOffice";
                jc.setWorldBackground("PostOffice");
                if(qualities.get("PostOffice")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.goTo(\"Artist'sStudio\");") && qualities.get(location) &&
                    qualities.get("MushroomVillage") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "Artist'sStudio";
                jc.setWorldBackground("Studio");
                if(qualities.get("Artist'sStudio")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.goTo(\"ScienceLab\");") && qualities.get(location) &&
                    qualities.get("ConstructionSite") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "ScienceLab";
                jc.setWorldBackground("ScienceLab");
                if(qualities.get("ScienceLab")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.goTo(\"Library\");") && qualities.get(location) &&
                    qualities.get("PostOffice") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "Library";
                jc.setWorldBackground("Library");
                if(qualities.get("Library")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.goTo(\"ArtGallery\");") && qualities.get(location) &&
                    qualities.get("Artist'sStudio") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "ArtGallery";
                jc.setWorldBackground("ArtGallery");
                if(qualities.get("ArtGallery")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.goTo(\"SnowyHill\");") && qualities.get(location) &&
                    qualities.get("ScienceLab") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "SnowyHill";
                jc.setWorldBackground("SnowyHill");
                if(qualities.get("SnowyHill")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.goTo(\"SuspiciousHole\");") && qualities.get(location) &&
                    qualities.get("ScienceLab") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "SuspiciousHole";
                jc.setWorldBackground("SuspiciousHole");
                if(qualities.get("SuspiciousHole")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.goTo(\"MadScientist'sLab\");") && qualities.get(location) &&
                    qualities.get("ScienceLab") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "MadScientist'sLab";
                jc.setWorldBackground("MadScientist'sLab");
                if(qualities.get("MadScientist'sLab")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.goTo(\"DarkForest\");") && qualities.get(location) &&
                    qualities.get("ArtGallery") && jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "DarkForest";
                jc.setWorldBackground("DarkForest");
                if(qualities.get("DarkForest")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.goTo(\"MountainattheEndoftheWorld\");") && qualities.get(location) &&
                    (qualities.get("Artist'sStudio") || qualities.get("Library") || qualities.get("SnowyHill")) &&
                    jc.getWorldScene().equals("Map")) {
                jc.setWorldScene("World");
                location = "MountainattheEndoftheWorld";
                jc.setWorldBackground("MountainattheEndoftheWorld");
                if(qualities.get("MountainattheEndoftheWorld")) {
                    jc.getTarget().setVisible(false);
                    jc.sayMessage("");
                }
            }
            else if (command.equals("map.hide();")) {
                jc.setWorldScene("World");
            }
            else if (command.equals("familiar.hint();") && familiar != null) {
                if(jc.getHintMessage() != null) {
                    jc.familiarSays(jc.getHintMessage());
                } else {
                    jc.familiarSays("It looks like there isn't anything you are being asked to do right now. I " +
                            "think we just need to wait a while longer, boss.");
                }
            }
            else if (isCommand(command)) {
                String objectName = command.substring(0, command.indexOf("."));
                String method = command.substring(command.indexOf(".") + 1, command.indexOf("("));
                String className;
                if(jc.getEnvironmentObjects().get(location).containsKey(objectName)) {
                    className = jc.getEnvironmentObjects().get(location).get(objectName).getClassName();
                } else if(jc.getEnvironmentObjects().get("User").containsKey(objectName)){
                    className = jc.getEnvironmentObjects().get("User").get(objectName).getClassName();
                }
                else {
                    jc.familiarSays("Uh, boss? I don't believe that object currently exists here. Recheck your" +
                                " Codex to see what objects are available to us.");
                    return;
                }
                String input = command.substring(command.indexOf("(") + 1, command.indexOf(")"));
                if(((method.equals("click") && qualities.get("click")) || qualities.get(className)) &&
                        jc.getEnvironmentObjects().get(location).containsKey(objectName)) {
                    jc.getEnvironmentObjects().get(location).get(objectName).useMethod(method, input);
                } else if(((method.equals("click") && qualities.get("click")) || qualities.get(className)) &&
                        jc.getEnvironmentObjects().get("User").containsKey(objectName)) {
                    jc.getEnvironmentObjects().get("User").get(objectName).useMethod(method, input);
                }
            }
            else if(isInitializer(command) == 1) {
                String className = command.substring(0, command.indexOf(" "));
                command = command.substring(command.indexOf(" ") + 1);
                String objectName = command.substring(0, command.indexOf(" "));
                String input = command.substring(command.indexOf("(") + 1, command.indexOf(")"));
                switch(className) {
                    case "Button":
                        if(qualities.get("Button")) {
                            if (Pattern.matches("[\"][a-zA-Z]*[\"]", input)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new ButtonItem(input, jc));
                            } else if (input.trim().equals("")) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new ButtonItem(jc));
                            } else {
                                jc.familiarSays("That initialization seems to have either had too many or too few arguments." +
                                        " Remember that there's a guide to formatting your commands in the Codex.");
                            }
                            break;
                        }
                    case "Donut":
                        if(qualities.get("Donut")) {
                            if (input.equals("")) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(jc));
                                if (qualities.get("ConstructorPractice")) {
                                    jc.getTimer().schedule(jc.getLibrary().get("MethodPractice"), 0);
                                    qualities.put("ConstructorPractice", false);
                                }
                            } else if (input.toLowerCase().equals("false")) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(false, jc));
                                if (qualities.get("ConstructorPractice")) {
                                    jc.getTimer().schedule(jc.getLibrary().get("MethodPractice"), 0);
                                    qualities.put("ConstructorPractice", false);
                                }
                            } else if (input.toLowerCase().equals("true")) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(true, jc));
                                if (qualities.get("ConstructorPractice")) {
                                    jc.getTimer().schedule(jc.getLibrary().get("MethodPractice"), 0);
                                    qualities.put("ConstructorPractice", false);
                                }
                            } else if (input.toLowerCase().contains("false,")) {
                                if (Pattern.matches("[\"][a-zA-Z]*[\"]", input.substring(input.indexOf(",") + 1).trim())) {
                                    jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(false, input.substring(input.indexOf(",") + 1).trim(), jc));
                                    if (qualities.get("ConstructorPractice")) {
                                        jc.getTimer().schedule(jc.getLibrary().get("MethodPractice"), 0);
                                        qualities.put("ConstructorPractice", false);
                                    }
                                } else {
                                    jc.familiarSays("Remember that a String must have quotes around it.");
                                }
                            } else if (input.toLowerCase().contains("true,")) {
                                if (Pattern.matches("[\"][a-zA-Z]*[\"]", input.substring(input.indexOf(",") + 1).trim())) {
                                    jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(true, input.substring(input.indexOf(",") + 1).trim(), jc));
                                    if (qualities.get("ConstructorPractice")) {
                                        jc.getTimer().schedule(jc.getLibrary().get("MethodPractice"), 0);
                                        qualities.put("ConstructorPractice", false);
                                    }
                                } else {
                                    jc.familiarSays("Remember that a String must have quotes around it.");
                                }
                            } else if (Pattern.matches("[\"][a-zA-Z]*[\"]", input)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(input, jc));
                                if (qualities.get("ConstructorPractice")) {
                                    jc.getTimer().schedule(jc.getLibrary().get("MethodPractice"), 0);
                                    qualities.put("ConstructorPractice", false);
                                }
                            } else {
                                jc.familiarSays("That initialization seems to have either had too many or too few arguments." +
                                        " Remember that there's a guide to formatting your commands in the Codex.");
                            }
                            break;
                        }
                    default:
                        jc.familiarSays("Hey boss? I don't think you have permission to make that yet.");
                }
            }
            else if(isInitializer(command) == 2) {

                String className = command.substring(0, command.indexOf("["));
                command = command.substring(command.indexOf("]") + 2);
                String objectName = command.substring(0, command.indexOf(" "));
                String number = command.substring(command.indexOf("[") + 1, command.indexOf("]"));
                int num = Integer.parseInt(number);
                switch(className) {
                    case "Button":
                        if(qualities.get("Button")) {
                            for (int i = 0; i < num; i++) {
                                jc.getEnvironmentObjects().get("User").put(objectName + "[" + i + "]", new ButtonItem(new Null()));
                            }
                            break;
                        }
                    case "Donut":
                        if(qualities.get("Donut")) {
                            for (int i = 0; i < num; i++) {
                                jc.getEnvironmentObjects().get("User").put(objectName + "[" + i + "]", new DonutItem(new Null()));
                            }
                            break;
                        }
                    default:
                        jc.familiarSays("Hey boss? I don't think you have permission to make that yet.");
                }
            }
            else if(isInitializer(command) == 3) {
                String className = jc.getEnvironmentObjects().get(location).get(command.substring(0, command.indexOf(" "))).getClassName();
                String objectName = command.substring(0, command.indexOf(" "));
                command = command.substring(command.indexOf(" ") + 1);
                String input = command.substring(command.indexOf("(") + 1, command.indexOf(")"));
                switch(className) {
                    case "Button":
                        if(qualities.get("Button")) {
                            if (Pattern.matches("[\"][a-zA-Z]*[\"]", input)) {
                                jc.getEnvironmentObjects().get(location).put(objectName, new ButtonItem(input, jc));
                            } else if (input.trim().equals("")) {
                                jc.getEnvironmentObjects().get(location).put(objectName, new ButtonItem(jc));
                            } else {
                                jc.familiarSays("That initialization seems to have either had too many or too few arguments." +
                                        " Remember that there's a guide to formatting your commands in the Codex.");
                            }
                            break;
                        }
                    case "Donut":
                        if(qualities.get("Donut")) {
                            if (input.equals("")) {
                                jc.getEnvironmentObjects().get(location).put(objectName, new DonutItem(jc));
                            } else if (input.toLowerCase().equals("false")) {
                                jc.getEnvironmentObjects().get(location).put(objectName, new DonutItem(false, jc));
                            } else if (input.toLowerCase().equals("true")) {
                                jc.getEnvironmentObjects().get(location).put(objectName, new DonutItem(true, jc));
                            } else if (input.toLowerCase().contains("false,")) {
                                if (Pattern.matches("[\"][a-zA-Z]*[\"]", input.substring(input.indexOf(",") + 1).trim())) {
                                    jc.getEnvironmentObjects().get(location).put(objectName, new DonutItem(false, input.substring(input.indexOf(",") + 1).trim(), jc));
                                } else {
                                    jc.familiarSays("Remember that a String must have quotes around it.");
                                }
                            } else if (input.toLowerCase().contains("true,")) {
                                if (Pattern.matches("[\"][a-zA-Z]*[\"]", input.substring(input.indexOf(",") + 1).trim())) {
                                    jc.getEnvironmentObjects().get(location).put(objectName, new DonutItem(true, input.substring(input.indexOf(",") + 1).trim(), jc));
                                } else {
                                    jc.familiarSays("Remember that a String must have quotes around it.");
                                }
                            } else if (Pattern.matches("[\"][a-zA-Z]*[\"]", input)) {
                                jc.getEnvironmentObjects().get(location).put(objectName, new DonutItem(input, jc));
                            } else {
                                jc.familiarSays("That initialization seems to have either had too many or too few arguments." +
                                        " Remember that there's a guide to formatting your commands in the Codex.");
                            }
                            break;
                        }
                    default:
                        jc.familiarSays("Hey boss? I don't think you have permission to make that yet.");
                }
            }
            else if(isInitializer(command) == 4) {
                String className = jc.getEnvironmentObjects().get("User").get(command.substring(0, command.indexOf(" "))).getClassName();
                String objectName = command.substring(0, command.indexOf(" "));
                command = command.substring(command.indexOf(" ") + 1);
                String input = command.substring(command.indexOf("(") + 1, command.indexOf(")"));
                switch(className) {
                    case "Button":
                        if(qualities.get("Button")) {
                            if (Pattern.matches("[\"][a-zA-Z]*[\"]", input)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new ButtonItem(input, jc));
                            } else if (input.trim().equals("")) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new ButtonItem(jc));
                            } else {
                                jc.familiarSays("That initialization seems to have either had too many or too few arguments." +
                                        " Remember that there's a guide to formatting your commands in the Codex.");
                            }
                            break;
                        }
                    case "Donut":
                        if(qualities.get("Donut")) {
                            if (input.equals("")) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(jc));
                            } else if (input.toLowerCase().equals("false")) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(false, jc));
                            } else if (input.toLowerCase().equals("true")) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(true, jc));
                            } else if (input.toLowerCase().contains("false,")) {
                                if (Pattern.matches("[\"][a-zA-Z]*[\"]", input.substring(input.indexOf(",") + 1).trim())) {
                                    jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(false, input.substring(input.indexOf(",") + 1).trim(), jc));
                                } else {
                                    jc.familiarSays("Remember that a String must have quotes around it.");
                                }
                            } else if (input.toLowerCase().contains("true,")) {
                                if (Pattern.matches("[\"][a-zA-Z]*[\"]", input.substring(input.indexOf(",") + 1).trim())) {
                                    jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(true, input.substring(input.indexOf(",") + 1).trim(), jc));
                                } else {
                                    jc.familiarSays("Remember that a String must have quotes around it.");
                                }
                            } else if (Pattern.matches("[\"][a-zA-Z]*[\"]", input)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(input, jc));
                            } else {
                                jc.familiarSays("That initialization seems to have either had too many or too few arguments." +
                                        " Remember that there's a guide to formatting your commands in the Codex.");
                            }
                            break;
                        }
                    default:
                        jc.familiarSays("Hey boss? I don't think you have permission to make that yet.");
                }
            }
            else if(isInitializer(command) == 5) {
                String className = command.substring(command.indexOf(" "));
                command = command.substring(command.indexOf(" ") + 1);
                String objectName = command.substring(0, command.indexOf(" "));
                String targetObject = command.substring(command.indexOf("=") + 1).trim();
                switch(className) {
                    case "Button":
                        if(qualities.get("Button")) {
                            if (jc.getEnvironmentObjects().get(location).containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new ButtonItem(
                                        (ButtonItem) jc.getEnvironmentObjects().get(location).get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    case "Donut":
                        if(qualities.get("Donut")) {
                            if (jc.getEnvironmentObjects().get(location).containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(
                                        (DonutItem) jc.getEnvironmentObjects().get(location).get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    default:
                        jc.familiarSays("Hey boss? I don't think you have permission to make that yet.");
                }
            }
            else if(isInitializer(command) == 6) {
                String className = command.substring(command.indexOf(" "));
                command = command.substring(command.indexOf(" ") + 1);
                String objectName = command.substring(0, command.indexOf(" "));
                String targetObject = command.substring(command.indexOf("=") + 1).trim();
                switch(className) {
                    case "Button":
                        if(qualities.get("Button")) {
                            if (jc.getEnvironmentObjects().get("User").containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new ButtonItem(
                                        (ButtonItem) jc.getEnvironmentObjects().get("User").get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    case "Donut":
                        if(qualities.get("Donut")) {
                            if (jc.getEnvironmentObjects().get("User").containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(
                                        (DonutItem) jc.getEnvironmentObjects().get("User").get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    default:
                        jc.familiarSays("Hey boss? I don't think you have permission to make that yet.");
                }
            }
            else if(isInitializer(command) == 7) {
                String className = jc.getEnvironmentObjects().get(location).get(command.substring(0, command.indexOf(" "))).getClassName();
                String objectName = command.substring(0, command.indexOf(" "));
                command = command.substring(command.indexOf(" ") + 1);
                String targetObject = command.substring(command.indexOf("=") + 1).trim();
                switch(className) {
                    case "Button":
                        if(qualities.get("Button")) {
                            if (jc.getEnvironmentObjects().get(location).containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get(location).put(objectName, new ButtonItem(
                                        (ButtonItem) jc.getEnvironmentObjects().get(location).get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    case "Donut":
                        if(qualities.get("Donut")) {
                            if (jc.getEnvironmentObjects().get(location).containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get(location).put(objectName, new DonutItem(
                                        (DonutItem) jc.getEnvironmentObjects().get(location).get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    default:
                        jc.familiarSays("Hey boss? I don't think you have permission to make that yet.");
                }
            }
            else if(isInitializer(command) == 8) {
                String className = jc.getEnvironmentObjects().get(location).get(command.substring(0, command.indexOf(" "))).getClassName();
                String objectName = command.substring(0, command.indexOf(" "));
                command = command.substring(command.indexOf(" ") + 1);
                String targetObject = command.substring(command.indexOf("=") + 1).trim();
                switch(className) {
                    case "Button":
                        if(qualities.get("Button")) {
                            if (jc.getEnvironmentObjects().get("User").containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get(location).put(objectName, new ButtonItem(
                                        (ButtonItem) jc.getEnvironmentObjects().get("User").get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    case "Donut":
                        if(qualities.get("Donut")) {
                            if (jc.getEnvironmentObjects().get("User").containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get(location).put(objectName, new DonutItem(
                                        (DonutItem) jc.getEnvironmentObjects().get("User").get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    default:
                        jc.familiarSays("Hey boss? I don't think you have permission to make that yet.");
                }
            }
            else if(isInitializer(command) == 9) {
                String className = jc.getEnvironmentObjects().get("User").get(command.substring(0, command.indexOf(" "))).getClassName();
                String objectName = command.substring(0, command.indexOf(" "));
                command = command.substring(command.indexOf(" ") + 1);
                String targetObject = command.substring(command.indexOf("=") + 1).trim();
                switch(className) {
                    case "Button":
                        if(qualities.get("Button")) {
                            if (jc.getEnvironmentObjects().get(location).containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new ButtonItem(
                                        (ButtonItem) jc.getEnvironmentObjects().get(location).get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    case "Donut":
                        if(qualities.get("Donut")) {
                            if (jc.getEnvironmentObjects().get(location).containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(
                                        (DonutItem) jc.getEnvironmentObjects().get(location).get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    default:
                        jc.familiarSays("Hey boss? I don't think you have permission to make that yet.");
                }
            }
            else if(isInitializer(command) == 10) {
                String className = jc.getEnvironmentObjects().get("User").get(command.substring(0, command.indexOf(" "))).getClassName();
                String objectName = command.substring(0, command.indexOf(" "));
                command = command.substring(command.indexOf(" ") + 1);
                String targetObject = command.substring(command.indexOf("=") + 1).trim();
                switch(className) {
                    case "Button":
                        if(qualities.get("Button")) {
                            if (jc.getEnvironmentObjects().get("User").containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new ButtonItem(
                                        (ButtonItem) jc.getEnvironmentObjects().get("User").get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    case "Donut":
                        if(qualities.get("Donut")) {
                            if (jc.getEnvironmentObjects().get("User").containsKey(targetObject)) {
                                jc.getEnvironmentObjects().get("User").put(objectName, new DonutItem(
                                        (DonutItem) jc.getEnvironmentObjects().get("User").get(targetObject)));
                            } else {
                                jc.familiarSays("There doesn't seem to be anything with that name here. Make sure that " +
                                        "you match the name of the object you want in the environment tab.");
                            }
                            break;
                        }
                    default:
                        jc.familiarSays("Hey boss? I don't think you have permission to make that yet.");
                }
            }
            else {
                jc.familiarSays("Uh, boss? I think something was off about that last command. Check your Codex" +
                            " to make sure it was entered correctly.");
            }
        };
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public void setFamiliar(String familiar) {
        if(this.familiar == null) {
            this.familiar = familiar;
        }
    }

    public void setQualityTrue(String quality) {
        if(qualities.containsKey(quality)) {
            qualities.put(quality, true);
        }
    }

    public Consumer<String> getUserCommands() {
        return userCommands;
    }

    public String getFamiliar(){
        return familiar;
    }

    public String getLocation(){
        return location;
    }

    public boolean isCommand(String command) {
        return Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?\\.[a-zA-Z]*\\([\"]?[a-zA-Z]*[\"]?\\)\\;", command);
    }

    public int isInitializer(String command) {
        Scanner scan = new Scanner(command);
        ArrayList<String> sections = new ArrayList<>();
        while(scan.hasNext()) {
            sections.add(scan.next());
        }
        scan.close();
        if(sections.size() == 5) {
            if(classLibrary.contains(sections.get(0))) {
                if(Pattern.matches("[a-zA-Z]*", sections.get(1))) {
                    if(sections.get(2).equals("=")) {
                        if(sections.get(3).equals("new")) {
                            if(sections.get(0).equals(sections.get(4).substring(0, sections.get(0).length())) &&
                                    sections.get(4).endsWith(");")) {
                                return 1;
                            }
                        }
                    }
                }
            } else if(classLibrary.contains(sections.get(0).substring(0, sections.get(0).length() - 2))) {
                if(Pattern.matches("[a-zA-Z]*", sections.get(1))) {
                    if(sections.get(2).equals("=")) {
                        if(sections.get(3).equals("new")) {
                            if(sections.get(0).equals(sections.get(4).substring(0, sections.get(0).length() - 3)) &&
                                    sections.get(4).endsWith("];")) {
                                return 2;
                            }
                        }
                    }
                }
            }
        } else if (sections.size() == 4) {
            if(Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?", sections.get(0)) && jc.getEnvironmentObjects().get(location).
                    containsKey(sections.get(0))) {
                if(sections.get(1).equals("=")) {
                    if(sections.get(2).equals("new")) {
                        if(classLibrary.contains(sections.get(3).substring(0, sections.get(3).indexOf("("))) &&
                                jc.getEnvironmentObjects().get(location).get(sections.get(0)).getClassName().equals(
                                sections.get(3).substring(0, jc.getEnvironmentObjects().get(location).get(
                                        sections.get(0)).getClassName().length())) &&  sections.get(3).endsWith(");"))
                            return 3;
                        }
                    }
                } else if(Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?", sections.get(0)) && jc.getEnvironmentObjects().get("User").
                    containsKey(sections.get(0))) {
                if(sections.get(1).equals("=")) {
                    if(sections.get(2).equals("new")) {
                        if (jc.getEnvironmentObjects().get("User").get(sections.get(0)).getClassName().equals(
                                sections.get(3).substring(0, jc.getEnvironmentObjects().get("User").get(
                                        sections.get(0)).getClassName().length())) && sections.get(3).endsWith(");")) {
                            return 4;
                        }
                    }
                }
            } else if(classLibrary.contains(sections.get(0))) {
                if(Pattern.matches("[a-zA-Z]*", sections.get(1)) && jc.getEnvironmentObjects().get(location).
                        containsKey(sections.get(1))) {
                    if(sections.get(2).equals("=")) {
                        if(Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?", sections.get(3)) && jc.getEnvironmentObjects()
                                .get(location).get(sections.get(3)).getClassName().equals(sections.get(0)) &&
                                (sections.get(3).endsWith(");") || sections.get(3).endsWith("];"))) {
                            return 5;
                        }
                    }
                } else if (Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?", sections.get(1)) && jc.getEnvironmentObjects().get("User").
                        containsKey(sections.get(1))) {
                    if(sections.get(2).equals("=")) {
                        if(Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?", sections.get(3)) && jc.getEnvironmentObjects()
                                .get("User").get(sections.get(3)).getClassName().equals(sections.get(0)) &&
                                (sections.get(3).endsWith(");") || sections.get(3).endsWith("];"))) {
                            return 6;
                        }
                    }
                }
            }
        } else if (sections.size() == 3) {
            if(Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?", sections.get(0)) &&
                    jc.getEnvironmentObjects().get(location).containsKey(sections.get(0))) {
                if(sections.get(1).equals("=")) {
                    if(Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?", sections.get(2)) &&
                            jc.getEnvironmentObjects().get(location).containsKey(sections.get(2)) &&
                            jc.getEnvironmentObjects().get(location).get(sections.get(2)).getClassName()
                                    .equals(jc.getEnvironmentObjects().get(location).get(sections.get(0)).getClassName())
                            && (sections.get(2).endsWith(");") || sections.get(2).endsWith("];"))) {
                        return 7;
                    } else if (Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?", sections.get(2)) &&
                            jc.getEnvironmentObjects().get("User").containsKey(sections.get(2)) &&
                            jc.getEnvironmentObjects().get("User").get(sections.get(2)).getClassName()
                                    .equals(jc.getEnvironmentObjects().get(location).get(sections.get(0)).getClassName())
                            && (sections.get(2).endsWith(");") || sections.get(2).endsWith("];"))) {
                        return 8;
                    }
                }
            } else if (Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?", sections.get(0)) &&
                    jc.getEnvironmentObjects().get("User").containsKey(sections.get(0))) {
                if(sections.get(1).equals("=")) {
                    if(Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?", sections.get(2)) &&
                            jc.getEnvironmentObjects().get(location).containsKey(sections.get(2)) &&
                            jc.getEnvironmentObjects().get(location).get(sections.get(2)).getClassName()
                                    .equals(jc.getEnvironmentObjects().get("User").get(sections.get(0)).getClassName())
                            && sections.get(2).endsWith(";")) {
                        return 9;
                    } else if (Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?", sections.get(2)) &&
                            jc.getEnvironmentObjects().get("User").containsKey(sections.get(2)) &&
                            jc.getEnvironmentObjects().get("User").get(sections.get(2)).getClassName()
                                    .equals(jc.getEnvironmentObjects().get("User").get(sections.get(0)).getClassName())
                            && (sections.get(2).endsWith(");") || sections.get(2).endsWith("];"))) {
                        return 10;
                    }
                }
            }
        }

        jc.familiarSays("Something didn't seem quite right with that command, boss. Maybe a class name was misspelled?");
        return 0;
    }

    public HashMap<String, Boolean> getQualities() {
        return qualities;
    }
}
