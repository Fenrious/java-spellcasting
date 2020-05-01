import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DonutItem extends Item {
    public DonutItem(boolean sprinkles, JavaCast jc) {
        super("Donut", (HashMap<String, Object>) Stream.of(new Object[][] {
                {"Icing", "Vanilla"},
                {"JavaCast", jc},
                {"Sprinkles", sprinkles},
                {"Filling", new Null()},
                {"Return", new Null()},
                {"Input", new ArrayList<>()}}).collect(Collectors.toMap(data -> (String) data[0], data ->
                (Object) data[1])), new HashMap<>());
        Consumer<HashMap<String, Object>> getIcing = (var) -> {
            var.put("Return", var.get("Icing"));
        };
        Consumer<HashMap<String, Object>> getSprinkles = (var) -> {
            var.put("Return", var.get("Sprinkles"));
        };
        Consumer<HashMap<String, Object>> getFilling = (var) -> {
            var.put("Return", var.get("Filling"));
        };
        Consumer<HashMap<String, Object>> fill = (var) -> {
            if(!((ArrayList<String>) var.get("Input")).isEmpty() && Pattern.matches("[\"][a-zA-Z]*[\"]", ((ArrayList<String>) var.get("Input")).get(0))) {
                var.put("Filling", ((ArrayList<String>) var.get("Input")).get(0)
                        .substring(((ArrayList<String>) var.get("Input")).get(0)
                                .indexOf("\"") + 1, ((ArrayList<String>) var.get("Input")).get(0).lastIndexOf("\"")));
                var.put("Return", new Null());
                if (((JavaCast) var.get("JavaCast")).getUser().getQualities().get("MethodPractice")) {
                    ((JavaCast) var.get("JavaCast")).getTimer().schedule(((JavaCast) var.get("JavaCast"))
                            .getLibrary().get("CompleteDonut"), 0);
                    ((JavaCast) var.get("JavaCast")).getUser().getQualities().put("MethodPractice", false);
                } else if (((JavaCast) var.get("JavaCast")).getUser().getQualities().get("ArrayInsertPractice")) {
                    ((JavaCast) var.get("JavaCast")).getTimer().schedule(((JavaCast) var.get("JavaCast"))
                            .getLibrary().get("ArrayRemove"), 0);
                    ((JavaCast) var.get("JavaCast")).getUser().getQualities().put("ArrayInsertPractice", false);
                }
            } else {
                jc.familiarSays("Remember that a String must have quotes around it.");
            }
        };
        Consumer<HashMap<String, Object>> sprinkle = (var) -> {
            var.put("Sprinkles", true);
            var.put("Return", new Null());
        };
        addMethod("getIcing", getIcing);
        addMethod("getSprinkles", getSprinkles);
        addMethod("getFilling", getFilling);
        addMethod("fill", fill);
        addMethod("sprinkle", sprinkle);
    }

    public DonutItem(String icing, JavaCast jc) {
        super("Donut", (HashMap<String, Object>) Stream.of(new Object[][] {
                {"Icing", icing.substring(icing.indexOf("\"") + 1, icing.lastIndexOf("\""))},
                {"JavaCast", jc},
                {"Sprinkles", false},
                {"Filling", new Null()},
                {"Return", new Null()},
                {"Input", new ArrayList<>()}}).collect(Collectors.toMap(data -> (String) data[0], data ->
                (Object) data[1])), new HashMap<>());
        Consumer<HashMap<String, Object>> getIcing = (var) -> {
            var.put("Return", var.get("Icing"));
        };
        Consumer<HashMap<String, Object>> getSprinkles = (var) -> {
            var.put("Return", var.get("Sprinkles"));
        };
        Consumer<HashMap<String, Object>> getFilling = (var) -> {
            var.put("Return", var.get("Filling"));
        };
        Consumer<HashMap<String, Object>> fill = (var) -> {
            if(!((ArrayList<String>) var.get("Input")).isEmpty() && Pattern.matches("[\"][a-zA-Z]*[\"]", ((ArrayList<String>) var.get("Input")).get(0))) {
                var.put("Filling", ((ArrayList<String>) var.get("Input")).get(0)
                        .substring(((ArrayList<String>) var.get("Input")).get(0)
                                .indexOf("\"") + 1, ((ArrayList<String>) var.get("Input")).get(0).lastIndexOf("\"")));
                var.put("Return", new Null());
                if (((JavaCast) var.get("JavaCast")).getUser().getQualities().get("MethodPractice")) {
                    ((JavaCast) var.get("JavaCast")).getTimer().schedule(((JavaCast) var.get("JavaCast"))
                            .getLibrary().get("CompleteDonut"), 0);
                    ((JavaCast) var.get("JavaCast")).getUser().getQualities().put("MethodPractice", false);
                } else if (((JavaCast) var.get("JavaCast")).getUser().getQualities().get("ArrayInsertPractice")) {
                    ((JavaCast) var.get("JavaCast")).getTimer().schedule(((JavaCast) var.get("JavaCast"))
                            .getLibrary().get("ArrayRemove"), 0);
                    ((JavaCast) var.get("JavaCast")).getUser().getQualities().put("ArrayInsertPractice", false);
                }
            } else {
                jc.familiarSays("Remember that a String must have quotes around it.");
            }
        };
        Consumer<HashMap<String, Object>> sprinkle = (var) -> {
            var.put("Sprinkles", true);
            var.put("Return", new Null());
        };
        addMethod("getIcing", getIcing);
        addMethod("getSprinkles", getSprinkles);
        addMethod("getFilling", getFilling);
        addMethod("fill", fill);
        addMethod("sprinkle", sprinkle);
    }
    public DonutItem(boolean sprinkles, String icing, JavaCast jc) {
        super("Donut", (HashMap<String, Object>) Stream.of(new Object[][] {
                {"Icing", icing.substring(icing.indexOf("\"") + 1, icing.lastIndexOf("\""))},
                {"JavaCast", jc},
                {"Sprinkles", sprinkles},
                {"Filling", new Null()},
                {"Return", new Null()},
                {"Input", new ArrayList<>()}}).collect(Collectors.toMap(data -> (String) data[0], data ->
                (Object) data[1])), new HashMap<>());
        Consumer<HashMap<String, Object>> getIcing = (var) -> {
            var.put("Return", var.get("Icing"));
        };
        Consumer<HashMap<String, Object>> getSprinkles = (var) -> {
            var.put("Return", var.get("Sprinkles"));
        };
        Consumer<HashMap<String, Object>> getFilling = (var) -> {
            var.put("Return", var.get("Filling"));
        };
        Consumer<HashMap<String, Object>> fill = (var) -> {
            if(!((ArrayList<String>) var.get("Input")).isEmpty() && Pattern.matches("[\"][a-zA-Z]*[\"]", ((ArrayList<String>) var.get("Input")).get(0))) {
                var.put("Filling", ((ArrayList<String>) var.get("Input")).get(0)
                        .substring(((ArrayList<String>) var.get("Input")).get(0)
                                .indexOf("\"") + 1, ((ArrayList<String>) var.get("Input")).get(0).lastIndexOf("\"")));
                var.put("Return", new Null());
                if (((JavaCast) var.get("JavaCast")).getUser().getQualities().get("MethodPractice")) {
                    ((JavaCast) var.get("JavaCast")).getTimer().schedule(((JavaCast) var.get("JavaCast"))
                            .getLibrary().get("CompleteDonut"), 0);
                    ((JavaCast) var.get("JavaCast")).getUser().getQualities().put("MethodPractice", false);
                } else if (((JavaCast) var.get("JavaCast")).getUser().getQualities().get("ArrayInsertPractice")) {
                    ((JavaCast) var.get("JavaCast")).getTimer().schedule(((JavaCast) var.get("JavaCast"))
                            .getLibrary().get("ArrayRemove"), 0);
                    ((JavaCast) var.get("JavaCast")).getUser().getQualities().put("ArrayInsertPractice", false);
                }
            } else {
                jc.familiarSays("Remember that a String must have quotes around it.");
            }
        };
        Consumer<HashMap<String, Object>> sprinkle = (var) -> {
            var.put("Sprinkles", true);
            var.put("Return", new Null());
        };
        addMethod("getIcing", getIcing);
        addMethod("getSprinkles", getSprinkles);
        addMethod("getFilling", getFilling);
        addMethod("fill", fill);
        addMethod("sprinkle", sprinkle);
    }
    public DonutItem(JavaCast jc) {
        super("Donut", (HashMap<String, Object>) Stream.of(new Object[][] {
                {"Icing", "Vanilla"},
                {"JavaCast", jc},
                {"Sprinkles", false},
                {"Filling", new Null()},
                {"Return", new Null()},
                {"Input", new ArrayList<>()}}).collect(Collectors.toMap(data -> (String) data[0], data ->
                (Object) data[1])), new HashMap<>());
        Consumer<HashMap<String, Object>> getIcing = (var) -> {
            var.put("Return", var.get("Icing"));
        };
        Consumer<HashMap<String, Object>> getSprinkles = (var) -> {
            var.put("Return", var.get("Sprinkles"));
        };
        Consumer<HashMap<String, Object>> getFilling = (var) -> {
            var.put("Return", var.get("Filling"));
        };
        Consumer<HashMap<String, Object>> fill = (var) -> {
            if(!((ArrayList<String>) var.get("Input")).isEmpty() && Pattern.matches("[\"][a-zA-Z]*[\"]", ((ArrayList<String>) var.get("Input")).get(0))) {
                var.put("Filling", ((ArrayList<String>) var.get("Input")).get(0)
                        .substring(((ArrayList<String>) var.get("Input")).get(0)
                                .indexOf("\"") + 1, ((ArrayList<String>) var.get("Input")).get(0).lastIndexOf("\"")));
                var.put("Return", new Null());
                if (((JavaCast) var.get("JavaCast")).getUser().getQualities().get("MethodPractice")) {
                    ((JavaCast) var.get("JavaCast")).getTimer().schedule(((JavaCast) var.get("JavaCast"))
                            .getLibrary().get("CompleteDonut"), 0);
                    ((JavaCast) var.get("JavaCast")).getUser().getQualities().put("MethodPractice", false);
                } else if (((JavaCast) var.get("JavaCast")).getUser().getQualities().get("ArrayInsertPractice")) {
                    ((JavaCast) var.get("JavaCast")).getTimer().schedule(((JavaCast) var.get("JavaCast"))
                            .getLibrary().get("ArrayRemove"), 0);
                    ((JavaCast) var.get("JavaCast")).getUser().getQualities().put("ArrayInsertPractice", false);
                }
            } else {
                jc.familiarSays("Remember that a String must have quotes around it.");
            }
        };
        Consumer<HashMap<String, Object>> sprinkle = (var) -> {
            var.put("Sprinkles", true);
            var.put("Return", new Null());
        };
        addMethod("getIcing", getIcing);
        addMethod("getSprinkles", getSprinkles);
        addMethod("getFilling", getFilling);
        addMethod("fill", fill);
        addMethod("sprinkle", sprinkle);
    }
    public DonutItem(DonutItem clone) {
        super("Donut", clone.getVariables(), clone.getMethods());
    }

    public DonutItem(Null n) {
        super("Donut", null, null);
    }

}
