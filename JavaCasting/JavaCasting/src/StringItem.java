import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class StringItem extends Item {
    public StringItem(String value, JavaCast jc) {
        super("String", (HashMap<String, Object>) Map.of("Value", value, "JavaCast", jc),
                new HashMap<>());
        Consumer<HashMap<String, Object>> get = (var) -> {
            var.put("Return", var.get("Value"));
        };
        addMethod("get", get);
    }
}
