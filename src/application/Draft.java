package application;

import com.sun.deploy.uitoolkit.impl.fx.AppletStageManager;
import javafx.beans.property.SimpleStringProperty;
import javafx.stage.Stage;

public class Draft {
    public static void main(String[] args) {
        SimpleStringProperty stringProperty=new SimpleStringProperty("123");

        System.out.println(stringProperty.getValue());
    }
}
