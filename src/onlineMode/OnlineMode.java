package onlineMode;

import home.Home;
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
import static home.Home.bGround;
import static home.Home.closeLBL;
import static home.Home.minimizeLBL;
import java.io.IOException;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import repository.Repo;
import validation.Validation;

public  class OnlineMode extends AnchorPane {

    protected final AnchorPane anchorPane;
    protected final Label label;
    protected final TextField txtIp;
    protected final Label label0;
    protected final Button btnSubmit;
    private Label backBTN;
    private Stage myStage;
    public ProgressIndicator pb = new ProgressIndicator();

    private double xOffset = 0;
    private double yOffset = 0;

    public OnlineMode(Stage stage) {
        
        myStage = stage;
        anchorPane = new AnchorPane();
        label = new Label();
        txtIp = new TextField();
        label0 = new Label();
        btnSubmit = new Button();
        backBTN = new Label();
        
        
  
        this.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        this.setOnMouseDragged((MouseEvent event) -> {
            myStage.setX(event.getScreenX() - xOffset);
            myStage.setY(event.getScreenY() - yOffset);
        });
        


        setId("AnchorPane");
        setPrefHeight(400.0);
        setPrefWidth(600.0);
     
        anchorPane.setBackground(bGround);
        anchorPane.getChildren().add(minimizeLBL);
        anchorPane.getChildren().add(closeLBL);
        anchorPane.setMaxHeight(USE_PREF_SIZE);
        anchorPane.setMaxWidth(USE_PREF_SIZE);
        anchorPane.setMinHeight(USE_PREF_SIZE);
        anchorPane.setMinWidth(USE_PREF_SIZE);
        anchorPane.setPrefHeight(650.0);
        anchorPane.setPrefWidth(850.0);
        
       
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setLayoutX(160.0);
        label.setLayoutY(51.0);
        label.setPrefHeight(34.0);
        label.setPrefWidth(218.0);
        label.setFont(new Font(30));
        label.setTextFill(Color.AQUA);
        label.setText("Online Mode");

        txtIp.setLayoutX(320);
        txtIp.setLayoutY(300);
        txtIp.setPrefHeight(25.0);
        txtIp.setPrefWidth(164.0);

        label0.setAlignment(javafx.geometry.Pos.CENTER);
        label0.setLayoutX(150);
        label0.setLayoutY(250.0);
        label0.setPrefHeight(17.0);
        label0.setPrefWidth(500);
        label0.setTextFill(Color.AQUA);
        label0.setFont(new Font(20));
        label0.setText("Please enter IP address of servier");
        
        pb.setLayoutX(380);
        pb.setLayoutY(400);

        btnSubmit.setLayoutX(370);
        btnSubmit.setLayoutY(350);
        btnSubmit.setMnemonicParsing(false);
        btnSubmit.setText("Submit");
        
        
        btnSubmit.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String ipAddress =txtIp.getText().trim();
                if(!(ipAddress.isEmpty())){
                if(Validation.isValidIP(ipAddress)){
                    btnSubmit.setDisable(true);
                    anchorPane.getChildren().add(pb);

                    
                    Thread t = new Thread(){
                        @Override
                        public void run() {
                             try {
                        Repo.mySocket = new Socket(ipAddress, 7001);
                    } catch (IOException ex) {
                        Logger.getLogger(OnlineMode.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    }                      
                    };
                    t.start();
                    new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        Platform.runLater(() -> {
                                              t.stop();
                                           if(Repo.mySocket!=null&&Repo.mySocket.isConnected()){
                                               Scene scene = new Scene(new OnlineLoginScene(myStage));
                                               myStage.setScene(scene);
                                           }
                                           else{
                                               Alert a = new Alert(Alert.AlertType.ERROR);
                                               a.setContentText("connection error");
                                               a.show();
                                               anchorPane.getChildren().remove(pb);
                                               txtIp.clear();
                                               btnSubmit.setDisable(false);
                                           }
                                        });
                                    }
                                },
                                        2000
                                );
                 
                }
                }
                 else{
                      anchorPane.getChildren().remove(pb);
                     Alert a = new Alert(Alert.AlertType.ERROR);
                               a.setContentText("Ip Address isn't valid");
                               a.show();
                }
                              
            }
        });
        
        backBTN.setLayoutX(650);
        backBTN.setLayoutY(80);
        backBTN.setPrefSize(80, 80);
        backBTN.setGraphic(new ImageView(new Image("Icons/Home.png", 80, 80, true, true)));
        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Scene scene = new Scene(new Home(myStage));
                myStage.setScene(scene);
            }
        });

        anchorPane.getChildren().add(label);
        anchorPane.getChildren().add(txtIp);
        anchorPane.getChildren().add(label0);
        anchorPane.getChildren().add(btnSubmit);
        anchorPane.getChildren().add(backBTN);
        getChildren().add(anchorPane);
        setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);

    }
}
