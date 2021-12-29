package xogameclient;

import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public abstract class FXMLDocument_Base2 extends AnchorPane {

    protected final TextField textField;
    protected final TextField textField0;
    protected final Button button;
    protected final Button button0;

    public FXMLDocument_Base2() {

        textField = new TextField();
        textField0 = new TextField();
        button = new Button();
        button0 = new Button();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(691.0);
        setPrefWidth(812.0);

        textField.setLayoutX(277.0);
        textField.setLayoutY(196.0);
        textField.setPrefHeight(52.0);
        textField.setPrefWidth(259.0);
        textField.setPromptText("user name");

        textField0.setLayoutX(277.0);
        textField0.setLayoutY(266.0);
        textField0.setPrefHeight(52.0);
        textField0.setPrefWidth(259.0);
        textField0.setPromptText("Password");

        button.setLayoutX(419.0);
        button.setLayoutY(401.0);
        button.setMnemonicParsing(false);
        button.setPrefHeight(52.0);
        button.setPrefWidth(117.0);
        button.setText("Button");

        button0.setLayoutX(277.0);
        button0.setLayoutY(403.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(52.0);
        button0.setPrefWidth(117.0);
        button0.setText("Button");

        
        getChildren().add(textField);
        getChildren().add(textField0);
        getChildren().add(button);
        getChildren().add(button0);

    }
}
