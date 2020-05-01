import javafx.scene.image.Image;

import java.util.TimerTask;
import java.util.function.Consumer;

public class Scenario extends TimerTask {

    JavaCast jc;
    Image target;
    String message;
    String nextScenario;
    Consumer<String> commands;
    Consumer<JavaCast> general;

    public Scenario(JavaCast jc, Image target, String message, String nextScenario, Consumer<String> commands, Consumer<JavaCast> general){
        this.jc = jc;
        this.target = target;
        this.message = message;
        this.nextScenario = nextScenario;
        this.commands = commands;
        this.general = general;
    }

    @Override
    public void run() {
        jc.setTarget(target);
        jc.sayMessage(message);
        if(nextScenario != null) {
            jc.setNextScenario(nextScenario);
        }
        if(commands != null) {
            jc.setScenarioCommands(commands);
        }
        if(general != null) {
            general.accept(jc);
        }
        if(jc.getCodexTitle().equals("Environment")) {
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
        }
        jc.updateCodex();
        jc.updateMapView();
    }
}
