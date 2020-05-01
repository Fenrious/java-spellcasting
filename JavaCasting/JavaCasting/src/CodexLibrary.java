import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;

import java.util.ArrayList;

public class CodexLibrary {
    private static void standardButton(Button button, ScrollPane pane) {
        button.setAlignment(Pos.CENTER);
        button.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        button.prefWidthProperty().bind(pane.widthProperty().subtract(10));
        button.prefHeightProperty().bind(pane.heightProperty().divide(10));
        button.setWrapText(true);
    }

    public static ArrayList<Button> getButtons(ScrollPane scroll, JavaCast jc) {
        ArrayList<Button> buttons = new ArrayList<>();
        buttons.add(new Button("Environment"));
        standardButton(buttons.get(0), scroll);
        buttons.get(0).setOnAction(e -> {
            jc.setCodexTitle("Environment");
            String newText = jc.getUser().getLocation() + ":";
            if (jc.getEnvironmentObjects().get(jc.getUser().getLocation()) != null) {
                for (String objectName : jc.getEnvironmentObjects().get(jc.getUser().getLocation()).keySet()) {
                    if (jc.getEnvironmentObjects().get(jc.getUser().getLocation()).get(objectName) != null) {
                        newText = newText + "\nClass Name: " + jc.getEnvironmentObjects().get(jc.getUser().getLocation()).get(objectName).getClassName() + "\nVariable Name: " + objectName;
                        if(jc.getEnvironmentObjects().get(jc.getUser().getLocation()).get(objectName).getVariables() != null && !(jc.getEnvironmentObjects().get(jc.getUser().getLocation()).get(objectName).getVariables().get("Return") instanceof Null)) {
                            newText = newText + "\nReturn Value: " + jc.getEnvironmentObjects().get(jc.getUser().getLocation()).get(objectName).getVariables().get("Return").toString();
                        }
                        newText = newText + "\n";
                    }
                }
            }
            newText = newText + "\n\nUser:";
            if (jc.getEnvironmentObjects().get("User") != null) {
                for (String objectName : jc.getEnvironmentObjects().get("User").keySet()) {
                    if (jc.getEnvironmentObjects().get("User").get(objectName) != null) {
                        newText = newText + "\nClass Name: " + jc.getEnvironmentObjects().get("User").get(objectName).getClassName() + "\nVariable Name: " + objectName;
                        if(jc.getEnvironmentObjects().get("User").get(objectName).getVariables() != null && !(jc.getEnvironmentObjects().get("User").get(objectName).getVariables().get("Return") instanceof Null)) {
                            newText = newText + "\nReturn Value: " + jc.getEnvironmentObjects().get("User").get(objectName).getVariables().get("Return").toString();
                        }
                        newText = newText + "\n";
                    }
                }
                jc.setCodexText(newText);
            }
        });

        buttons.add(new Button("Button"));
        standardButton(buttons.get(1), scroll);
        buttons.get(1).setOnAction(e -> {
            jc.setCodexTitle("Button");
            String newText = "Methods:\nclick();\nThis method clicks a button, which causes its \"onAction\" effect to " +
                    "take place. There are no input variables as this method just uses the Button object itself. If " +
                    "there was a Button called \"soda\", you could call this method by using\n\"soda.click();\"\n" +
                    "This means that the Button with the name of \"soda\" has been clicked, and it will perform its" +
                    " designated action.";
            jc.setCodexText(newText);
        });

        buttons.add(new Button("Log"));
        standardButton(buttons.get(2), scroll);
        buttons.get(2).setOnAction(e -> {
            jc.setCodexTitle("Log");
            String newText = "This is a function to help record things that you have commanded or others have said. " +
                    "Your familiar is ignored in the records. To access the records, issue the command\n" +
                    "logbook.show();\nThis can be useful when you have issued an incorrect command or missed " +
                    "what had previously been said.";
            jc.setCodexText(newText);
        });

        buttons.add(new Button("Codex"));
        standardButton(buttons.get(3), scroll);
        buttons.get(3).setOnAction(e -> {
            jc.setCodexTitle("Codex");
            String newText = "The Avatar Progression Institute would like to formally apologize as the Codices" +
                    " require a bit more work to include search functions.";
            jc.setCodexText(newText);
        });

        buttons.add(new Button("Familiar"));
        standardButton(buttons.get(4), scroll);
        buttons.get(4).setOnAction(e -> {
            jc.setCodexTitle("Familiar");
            String newText = "This is a companion designed by the head Avatar Researchers at the Avatar " +
                    "Progression Institute to help you on your journey to becoming a fully fledged Avatar. " +
                    "Simply issue the command\nfamiliar.hint();\nand your familiar will try to give you a hint" +
                    " on what to do next.";
            jc.setCodexText(newText);
        });

        buttons.add(new Button("Map"));
        standardButton(buttons.get(5), scroll);
        buttons.get(5).setOnAction(e -> {
            jc.setCodexTitle("Map");
            String newText = "This feature was especially designed to prevent young freshmen getting lost. It also" +
                    " helps prevent freshmen trying to complete areas and lessons in an order that made it very " +
                    "difficult on the annual volunteers.\nTo look at the map\nmap.show();\n\nTo put the" +
                    " map away\nmap.hide();\n\nTo go to location\nmap.goTo(\"location\")";
            jc.setCodexText(newText);
        });

        buttons.add(new Button("Classes"));
        standardButton(buttons.get(6), scroll);
        buttons.get(6).setOnAction(e -> {
            jc.setCodexTitle("Classes");
            String newText = "These kinds of Classes do not refer to school. They are a narrow category of objects." +
                    "Multiple instances of the same Class are similar, but not exactly the same. They share the " +
                    "same types of traits as one another, but they don't have to share the same values for those " +
                    "traits. An example would be that all cats could have fur. Some cats have shorter fur, while " +
                    "other cats have longer fur. They are all cats, but they have their own values. Something that" +
                    " all members of a Class have access to is methods. This would be like the action to eat." +
                    "We might perform this action a bit differently from each other based on our values, but, at" +
                    " the end of the day, eating is about getting food from outside of the body into the stomach. " +
                    "Methods can be referenced in the \"Methods\" tab. In the Codex, tabs which contain Classes will" +
                    "start their entries with a description of the Class, then list their \"Constructors:\".";
            jc.setCodexText(newText);
        });

        buttons.add(new Button("Methods"));
        standardButton(buttons.get(7), scroll);
        buttons.get(7).setOnAction(e -> {
            jc.setCodexTitle("Methods");
            String newText = "Methods are the actions an object can take. They can be as simple as changing a " +
                    "value in the object from 1 to 2, or as complex as simulating cellular life. Of course, they" +
                    " can be even more simple or complex compared to the examples given. For most of your journey," +
                    "the way you call methods will require 3 distinct parts. The first is the name of the object you" +
                    " wish to call the method of. The second is the name of the method. " +
                    "The third is the input to the method. An example would be\nfritter.fill(\"Apple\");\n" +
                    "fritter is the name of the object. fill is the name of the method. \"Apple\" is the input to " +
                    "the fill method. Always remember to include () if your method has no input, and always remember" +
                    " to add a ; to the end of every command.";
            jc.setCodexText(newText);
        });

        buttons.add(new Button("Constructors"));
        standardButton(buttons.get(8), scroll);
        buttons.get(8).setOnAction(e -> {
            jc.setCodexTitle("Constructors");
            String newText = "Constructors are a special kind of method. All Classes have at least one constructor, " +
                    "but they are not limited to one. The constructor is only called once per new object because it " +
                    "sets up the object for use. A variable could call multiple constructors over its lifetime, but" +
                    " it will be equal to a completely new object each time it does. An example of a constructor" +
                    " call would be\nfritter = new Donut();\nThis particular constructor which has no input is " +
                    "called the default constructor. Also note that \"fritter\" must have previously been initialized" +
                    " as\nDount fritter\nbecause you cannot call a constructor to create an object which is " +
                    "incompatible with the Class of your variable.";
            jc.setCodexText(newText);
        });

        buttons.add(new Button("Donut"));
        standardButton(buttons.get(9), scroll);
        buttons.get(9).setOnAction(e -> {
            jc.setCodexTitle("Donut");
            String newText = "This Class describes a donut object. This is unique to the Valley.\n\nConstructors:" +
                    "\nDonut()\nDonut(boolean sprinkles)\nDonut(String icing)\nDonut(boolean " +
                    "sprinkles, String icing)\n\nVariables:\nboolean sprinkles;\nString icing;\nString filling;\n\n" +
                    "Methods:\ngetSprinkles();\nThis returns a boolean representing whether or not the donut has " +
                    "sprinkles\n\ngetIcing();\nThis returns the flavor of icing as a String.\n\ngetFilling();" +
                    "\nThis returns the flavor of the donut filling as a String.\n\nsprinkle(boolean sprinkles);" +
                    "\nThis method can either add or remove the sprinkles on a donut depending on which boolean " +
                    "it is given.\n\nfill(String filling);\nThis changes the current filling to whatever String is" +
                    "passed to it.";
            jc.setCodexText(newText);
        });

        buttons.add(new Button("Primitive Types"));
        standardButton(buttons.get(10), scroll);
        buttons.get(10).setOnAction(e -> {
            jc.setCodexTitle("Primitive Types");
            String newText = "Primitive Types are considered primitive because they have no methods. They are" +
                    " just their data value. The types are short, int, long, double, float, boolean, and char.\n" +
                    "short: Stores up to 32,767 and down to -32,768. Used for integer values.\nint: Stores up to" +
                    " 2,147,483,647 and down to -2,147,483,648. Used for integer values. Very common choice to" +
                    "hold integer values.\nlong: Stores up to 9,223,372,036,854,775,807 and down to " +
                    "-9,223,372,036,854,775,808. Used for integer values. Largest storage for integers.\ndouble: " +
                    "Tracks 2 decimal places. Useful for percentages and money.\nfloat: Holds several decimal places." +
                    " Useful for programs that require high levels of precision. Visible decimal places can be " +
                    "decreased with formatting.\nboolean: True or False. Useful for conditional statements like " +
                    "\"if\".\nchar: Holds onto a single character. Can be 0-255. Check the ASCII table for a " +
                    "translation between numbers and characters.";
            jc.setCodexText(newText);
        });

        buttons.add(new Button("Arrays"));
        standardButton(buttons.get(11), scroll);
        buttons.get(11).setOnAction(e -> {
            jc.setCodexTitle("Arrays");
            String newText = "An array is not an actual Class. It makes a series of objects based on the number" +
                    " given to initialize it. Arrays are a bit different when being initialized.\n" +
                    "Donut[] tray = new Donut[3];\nThis command creates 3 uninitialized Donut objects. " +
                    "To access the first element of tray, you must use tray[0]. Always remember that the number of" +
                    " elements in an array is one more than the maximum index of the array. Attempting to access an" +
                    " element that has a greater index than the maximum throws an error. The same happens when " +
                    "attempting to access an element with an index less than 0.";
            jc.setCodexText(newText);
        });

        return buttons;
    }
}
