import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ButtonItem extends Item {
    public ButtonItem(String text, JavaCast jc) {
        super("Button", (HashMap<String, Object>) Stream.of(new Object[][] {
                        {"Text", text.substring(text.indexOf("\"") + 1, text.lastIndexOf("\""))},
                        {"JavaCast", jc},
                        {"Return", new Null()},
                        {"OnAction", new Null()},
                        {"Input", new ArrayList<>()}}).collect(Collectors.toMap(data -> (String) data[0], data -> (Object) data[1])),
                new HashMap<>());
        Consumer<HashMap<String, Object>> setOnAction = (var) -> {
            var.put("OnAction", ((ArrayList<Object>) var.get("Input")).get(0));
            var.put("Return", new Null());
        };
        Consumer<HashMap<String, Object>> setText = (var) -> {
            if(!((ArrayList<Object>) var.get("Input")).isEmpty() && Pattern.matches("[\"][a-zA-Z]*[\"]", ((ArrayList<String>) var.get("Input")).get(0))) {
                var.put("setText", ((ArrayList<String>) var.get("Input")).get(0)
                        .substring(((ArrayList<String>) var.get("Input")).get(0)
                                .indexOf("\"") + 1, ((ArrayList<String>) var.get("Input")).get(0).lastIndexOf("\"")));
                var.put("Return", new Null());
            } else {
                jc.familiarSays("Remember that a String must have quotes around it.");
            }
        };
        Consumer<HashMap<String, Object>> getText = (var) -> {
            var.put("Return", var.get("Text"));
        };
        Consumer<HashMap<String, Object>> click = (var) -> {
            ((Consumer<HashMap<String, Object>>) var.get("OnAction")).accept(var);
            var.put("Return", new Null());
        };
        addMethod("click", click);
        addMethod("setOnAction", setOnAction);
        addMethod("setText", setText);
        addMethod("getText", getText);
    }
    public ButtonItem(JavaCast jc) {
        super("Button", (HashMap<String, Object>) Stream.of(new Object[][] {
                        {"Text", new Null()},
                        {"JavaCast", jc},
                        {"Return", new Null()},
                        {"OnAction", new Null()},
                        {"Input", new ArrayList<>()}}).collect(Collectors.toMap(data -> (String) data[0], data -> (Object) data[1])),
                new HashMap<>());
        Consumer<HashMap<String, Object>> setOnAction = (var) -> {
            var.put("OnAction", ((ArrayList<Object>) var.get("Input")).get(0));
            var.put("Return", new Null());
        };
        Consumer<HashMap<String, Object>> setText = (var) -> {
            if(!((ArrayList<Object>) var.get("Input")).isEmpty() && Pattern.matches("[\"][a-zA-Z]*[\"]", ((ArrayList<String>) var.get("Input")).get(0))) {
                var.put("setText", ((ArrayList<String>) var.get("Input")).get(0)
                        .substring(((ArrayList<String>) var.get("Input")).get(0)
                                .indexOf("\"") + 1, ((ArrayList<String>) var.get("Input")).get(0).lastIndexOf("\"")));
                var.put("Return", new Null());
            } else {
                jc.familiarSays("Remember that a String must have quotes around it.");
            }
        };
        Consumer<HashMap<String, Object>> getText = (var) -> {
            var.put("Return", var.get("Text"));
        };
        Consumer<HashMap<String, Object>> click = (var) -> {
            ((Consumer<HashMap<String, Object>>) var.get("OnAction")).accept(var);
            var.put("Return", new Null());
        };
        addMethod("click", click);
        addMethod("setOnAction", setOnAction);
        addMethod("setText", setText);
        addMethod("getText", getText);
    }
    public ButtonItem(ButtonItem clone) {
        super("Button", clone.getVariables(), clone.getMethods());
    }

    public ButtonItem(Null n) {
        super("Button", null, null);
    }

}
