import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Slider;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.function.Consumer;
import java.util.regex.Pattern;

public class TestingForJavaCast extends Application {

    public static boolean isCommand(String command) {
        return Pattern.matches("[a-zA-Z]*(\\[[0-9]+\\])?\\.[a-zA-Z]*\\([a-zA-Z]*\\)\\;", command);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Stage stage1 = new Stage();
        StackPane optionPane = new StackPane();
        VBox optionVBox = new VBox();
        Text optionTitle = new Text("Options:");
        HBox mVHBox = new HBox();
        Text mVLabel = new Text("Master Volume: ");
        Slider mVSlider = new Slider();
        mVSlider.setMax(1);
        mVSlider.setMin(0);
        mVSlider.setValue(0.8);
        mVSlider.setShowTickLabels(true);
        mVSlider.setShowTickMarks(true);
        mVSlider.setMajorTickUnit(0.8);
        mVSlider.setMinorTickCount(5);
        mVSlider.setBlockIncrement(0.10);
        mVHBox.getChildren().addAll(mVLabel, mVSlider);
        Button optionsToMenu = new Button("Return to the Menu");
        Text optionInfo = new Text("Press O during the game to open the Option menu again.");
        optionInfo.setFont(Font.font("Verdana", FontPosture.ITALIC, 25));
        optionVBox.getChildren().addAll(optionTitle, mVHBox, optionsToMenu, optionInfo);
        optionVBox.spacingProperty().bind(stage.heightProperty().divide(5));
        optionPane.getChildren().add(optionVBox);


        optionVBox.setAlignment(Pos.TOP_CENTER);
        mVHBox.setAlignment(Pos.CENTER);
        mVLabel.setFont(Font.font("Verdana", FontPosture.ITALIC, 20));
        optionTitle.setFont(Font.font("Verdana", FontPosture.ITALIC, 30));

        Scene scene = new Scene(optionPane, 800, 800);
        stage1.setScene(scene);
        stage1.show();
    }

    public static void main(String[] args) {
        String test = "Donut sillyLongName = new Donut(\"Ice\");";
        System.out.println(test.substring(6, test.indexOf(" = new Donut(")));
        String test2 = "gar.heave(something);";
        System.out.println(test2.substring(test2.indexOf(".") + 1, test2.indexOf("(")));
        System.out.println(test2.substring(test2.indexOf("(") + 1, test2.indexOf(")")));
        System.out.println(test.substring(0, test.indexOf(" ")));
        String test3 = "chocolate, vanilla,strawberry";
        System.out.println(test3.substring(0, test3.indexOf(",")));
        String test4 = test3.substring(test3.indexOf(",") + 1);
        System.out.println(test4);
        System.out.println(test4.substring(1));
        System.out.println(TestingForJavaCast.isCommand("awake.click();"));
        System.out.println("abcde".substring(0, "abcde".lastIndexOf("bc")));
        System.out.println(Pattern.matches("[\"]*[a-zA-Z]*[\"]*", "\"Hello\""));
        System.out.println("\"Hello\"".substring("\"Hello\"".indexOf("\"") + 1, "\"Hello\"".lastIndexOf("\"")));
    }
}
