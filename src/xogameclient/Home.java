package xogameclient;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public  class Home extends AnchorPane {

    protected final Button btnLocal;
    protected final Button btnOnline;
    protected final Button btnVsPc;
    protected final Label label;
    private Stage myStage;
    

    public Home(Stage stage) {

        btnLocal = new Button();
        btnOnline = new Button();
        btnVsPc = new Button();
        label = new Label();
        myStage = stage;

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(691.0);
        setPrefWidth(812.0);

        btnLocal.setLayoutX(336.0);
        btnLocal.setLayoutY(300.0);
        btnLocal.setMnemonicParsing(false);
        btnLocal.setPrefHeight(63.0);
        btnLocal.setPrefWidth(144.0);
        btnLocal.setText("Local Playing");
        
        btnLocal.setOnAction((event) -> {
           Scene scene = new Scene(new TwoPlayerScene(stage));
            myStage.setScene(scene);

        });

        btnOnline.setLayoutX(333.0);
        btnOnline.setLayoutY(198.0);
        btnOnline.setMnemonicParsing(false);
        btnOnline.setPrefHeight(72.0);
        btnOnline.setPrefWidth(144.0);
        btnOnline.setText("Online Mode");

        btnVsPc.setLayoutX(336.0);
        btnVsPc.setLayoutY(386.0);
        btnVsPc.setMnemonicParsing(false);
        btnVsPc.setPrefHeight(63.0);
        btnVsPc.setPrefWidth(144.0);
        btnVsPc.setText("VS Pc");

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setLayoutX(91.0);
        label.setLayoutY(67.0);
        label.setPrefHeight(43.0);
        label.setPrefWidth(634.0);
        label.setText("Welcome to my Game");

        getChildren().add(btnLocal);
        getChildren().add(btnOnline);
        getChildren().add(btnVsPc);
        getChildren().add(label);
        // Im already married
        
      

    }
}
