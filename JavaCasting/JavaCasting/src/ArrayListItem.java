import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class ArrayListItem extends Item {
    public ArrayListItem(JavaCast jc) {
        super("ArrayList", (HashMap<String, Object>) Map.of("List", new ArrayList<>(), "JavaCast", jc, "Return",
                new Null(), "Input", new ArrayList<>()), new HashMap<>());
        Consumer<HashMap<String, Object>> setList = (var) -> {
            var.put("List", ((ArrayList<Object>) var.get("Input")).get(0));
            var.put("Return", new Null());
        };
        Consumer<HashMap<String, Object>> add = (var) -> {
            var.put("List", ((ArrayList<Object>) var.get("List")).add(((ArrayList<Object>) var.get("Input")).get(0)));
            var.put("Return", new Null());
        };
        Consumer<HashMap<String, Object>> set = (var) -> {
            var.put("List", ((ArrayList<Object>) var.get("List")).set(((ArrayList<Integer>) var.get("Input")).get(0), ((ArrayList<Object>) var.get("Input")).get(1)));
            var.put("Return", new Null());
        };
        Consumer<HashMap<String, Object>> get = (var) -> {
            var.put("Return", ((ArrayList<Object>) var.get("List")).get(((ArrayList<Integer>) var.get("Input")).get(0)));
        };
        addMethod("setList", setList);
        addMethod("add", add);
        addMethod("set", set);
        addMethod("get", get);
    }
    public ArrayListItem(ArrayList<Object> list, JavaCast jc) {
        super("ArrayList", (HashMap<String, Object>) Map.of("List", list, "JavaCast", jc, "Return",
                new Null(), "Input", new ArrayList<>()), new HashMap<>());
        Consumer<HashMap<String, Object>> setList = (var) -> {
            var.put("List", ((ArrayList<Object>) var.get("Input")).get(0));
            var.put("Return", new Null());
        };
        Consumer<HashMap<String, Object>> add = (var) -> {
            var.put("List", ((ArrayList<Object>) var.get("List")).add(((ArrayList<Object>) var.get("Input")).get(0)));
            var.put("Return", new Null());
        };
        Consumer<HashMap<String, Object>> set = (var) -> {
            var.put("List", ((ArrayList<Object>) var.get("List")).set(((ArrayList<Integer>) var.get("Input")).get(0), ((ArrayList<Object>) var.get("Input")).get(1)));
            var.put("Return", new Null());
        };
        Consumer<HashMap<String, Object>> get = (var) -> {
            var.put("Return", ((ArrayList<Object>) var.get("List")).get(((ArrayList<Integer>) var.get("Input")).get(0)));
        };
        addMethod("setList", setList);
        addMethod("add", add);
        addMethod("set", set);
        addMethod("get", get);
    }
}
