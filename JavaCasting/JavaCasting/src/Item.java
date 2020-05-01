import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Consumer;

public class Item {

    private String className;
    private Object value;
    private HashMap<String, Object> variables;
    private HashMap<String, Consumer<HashMap<String, Object>>> methods;

    public Item(String className, HashMap<String, Object> variables, HashMap<String, Consumer<HashMap<String, Object>>> methods) {
        this.className = className;
        this.variables = variables;
        this.methods = methods;
    }
    public Item(String className, Object value) {
        this.className = className;
        this.value = value;
    }

    public void setVariable(String name, Object value) {
        if(variables.containsKey(name)) {
            variables.put(name, value);
        }
    }

    public void setMethod(String name, Consumer<HashMap<String, Object>> method) {
        if(methods.containsKey(name)) {
            methods.put(name, method);
        }
    }

    public void addMethod(String name, Consumer<HashMap<String, Object>> method) {
        if(!methods.containsKey(name)) {
            methods.put(name, method);
        }
    }

    public void useMethod(String method, String input) {
        ArrayList<String> newInput = new ArrayList<>();
        while(input.length() > 0) {
            if (input.contains(",")) {
                newInput.add(input.substring(0, input.indexOf(",")));
                String temp = input.substring(input.indexOf(",") + 1);
                if(temp.startsWith(" ")) {
                    temp = temp.substring(1);
                }
                input = temp;
            } else {
                newInput.add(input);
                input = "";
            }
        }
        variables.put("Input", newInput);
        if(!methods.containsKey(method)) {
            if (((JavaCast) variables.get("JavaCast")).getUser().getFamiliar() != null) {
                ((JavaCast) variables.get("JavaCast")).familiarSays("Uh, boss? I don't believe that object currently exists here. Recheck your" +
                        " Codex to see what objects are available to us.");
            }
            return;
        }
        methods.get(method).accept(variables);
    }

    public String getClassName() {
        return className;
    }

    public HashMap<String, Object> getVariables() {
        return variables;
    }

    public HashMap<String, Consumer<HashMap<String, Object>>> getMethods() {
        return methods;
    }

    public Object getValue() {
        return value;
    }
}
