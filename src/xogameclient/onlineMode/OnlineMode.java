package xogameclient.onlineMode;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import static xogameclient.home.Home.bGround;
import static xogameclient.home.Home.closeLBL;
import static xogameclient.home.Home.minimizeLBL;
import xogameclient.repository.Repo;
import xogameclient.validation.IpAddressValidation;

public  class OnlineMode extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final TextField txtIp;
    protected final Label label0;
    protected final Button btnSubmit;
    private Stage myStage;

    public OnlineMode(Stage stage) {
        
        myStage = stage;
        anchorPane = new AnchorPane();
        label = new Label();
        txtIp = new TextField();
        label0 = new Label();
        btnSubmit = new Button();

        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);

        anchorPane.setLayoutX(-1.0);
        anchorPane.setLayoutY(-3.0);
        anchorPane.setPrefHeight(400.0);
        anchorPane.setPrefWidth(600.0);

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setLayoutX(160.0);
        label.setLayoutY(51.0);
        label.setPrefHeight(34.0);
        label.setPrefWidth(218.0);
        label.setText("Online Mode");
        label.setFont(new Font(18.0));

        txtIp.setLayoutX(196.0);
        txtIp.setLayoutY(140.0);
        txtIp.setPrefHeight(25.0);
        txtIp.setPrefWidth(164.0);

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setLayoutX(181.0);
        label0.setLayoutY(106.0);
        label0.setPrefHeight(17.0);
        label0.setPrefWidth(210.0);
        label0.setText("Please enter IP address of servier");

        btnSubmit.setLayoutX(252.0);
        btnSubmit.setLayoutY(200.0);
        btnSubmit.setMnemonicParsing(false);
        btnSubmit.setText("Submit");
        
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ipAddress =txtIp.getText().trim();
                if(!(ipAddress.isEmpty())){
                if(IpAddressValidation.isValid(ipAddress)){
                      Repo.IpAddress =ipAddress;
                    Scene scene = new Scene(new OnlineLoginScene(myStage));
                    myStage.setScene(scene); 
                }
                 else{
                     Alert a = new Alert(Alert.AlertType.ERROR);
                               a.setContentText("Ip Address isn't valid");
                               a.show();
                }
                }
               
            }
        });

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(txtIp);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(btnSubmit);
        getChildren().add(anchorPane);
        setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);

    }
}
