package onlineMode;

import home.Home;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static home.Home.bGround;
import static home.Home.closeLBL;
import static home.Home.minimizeLBL;
import home.XOGameCLient;
import javafx.application.Platform;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import modules.GameModule;
import pojo.LoginModel;
import modules.LoginModule;


public  class OnlineLoginScene extends AnchorPane {
    public ProgressIndicator pb = new ProgressIndicator();

    protected final TextField userNameTXT;
    protected final PasswordField passwordTXT;
    protected final Button loginBTN;
    protected final Button registerBTN;
    private Stage myStage;
    private LoginModel loginModel;
    private LoginModule loginViewModel;
    private String str;
    
    
    private double xOffset = 0;
    private double yOffset = 0;


    public OnlineLoginScene(Stage stage) {
        
        myStage = stage;
        userNameTXT = new TextField();
        passwordTXT = new PasswordField();
        loginBTN = new Button();
        registerBTN = new Button();
        loginModel = new LoginModel();
        loginViewModel = new LoginModule();
        
        
        this.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        this.setOnMouseDragged((MouseEvent event) -> {
            myStage.setX(event.getScreenX() - xOffset);
            myStage.setY(event.getScreenY() - yOffset);
        });
        

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(650.0);
        setPrefWidth(850.0);

        userNameTXT.setLayoutX(277.0);
        userNameTXT.setLayoutY(196.0);
        userNameTXT.setPrefHeight(52.0);
        userNameTXT.setPrefWidth(259.0);
        userNameTXT.setPromptText("user name");

        passwordTXT.setLayoutX(277.0);
        passwordTXT.setLayoutY(266.0);
        passwordTXT.setPrefHeight(52.0);
        passwordTXT.setPrefWidth(259.0);
        passwordTXT.setPromptText("Password");

        loginBTN.setLayoutX(419.0);
        loginBTN.setLayoutY(401.0);
        loginBTN.setMnemonicParsing(false);
        loginBTN.setPrefHeight(52.0);
        loginBTN.setPrefWidth(117.0);
        loginBTN.setText("Login");
        
         
        pb.setLayoutX(380);
        pb.setLayoutY(400);

        
        loginBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = userNameTXT.getText().trim();
                String password = passwordTXT.getText().trim();
                if(!(username.isEmpty())&&(!(password.isEmpty()))){
                    loginModel.setUsername(username);
                    loginModel.setPassword(password);
                    loginViewModel.sendLoginData(loginModel);
                    getChildren().add(pb);
                    

                    loginBTN.setDisable(true);
                    registerBTN.setDisable(true);
                    str = null;
                    Thread t = new Thread(){
                        @Override
                        public void run() {
                            str = loginViewModel.getLoginData();
                        }
                    
                    };
                    t.start();
                    
                     new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        Platform.runLater(() -> {
                                              t.stop();
                                              if(str == null){
                                                  Alert a = new Alert(Alert.AlertType.ERROR);
                                                  a.setContentText("connection error");
                                                  a.showAndWait();
                                                  LoginModule l =new LoginModule();
                                                  l.resetRepo();
                                                  myStage.setScene(new Scene(new Home(myStage)));
                                              } else if (str.equals("true")) {
                                                  OnlineGameScene.score = GameModule.getScore();
                                                  Home.onlineFlag = true;
                                                  OnlineGameScene.userName = userNameTXT.getText().trim();
                                                  Home.onlineScene = new Scene(new OnlineGameScene(myStage));
                                                  myStage.setScene(Home.onlineScene);
                                            } else {
                                                getChildren().remove(pb);
                                                registerBTN.setDisable(false);
                                                loginBTN.setDisable(false);
                                                Alert a = new Alert(Alert.AlertType.ERROR);
                                                a.setContentText("login fails");
                                                a.show();
                                            }
                 
                                           
                                        });
                                    }
                                },
                                        2000
                                );
                    
                }
                else{
                               Alert a = new Alert(Alert.AlertType.ERROR);
                               a.setContentText("Please Complete Your Data");
                               a.show();
                }
            }
        });


        registerBTN.setLayoutX(277.0);
        registerBTN.setLayoutY(403.0);
        registerBTN.setMnemonicParsing(false);
        registerBTN.setPrefHeight(52.0);
        registerBTN.setPrefWidth(117.0);
        registerBTN.setText("Sign Up");
        
        registerBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene scene = new Scene(new OnlineRegisterScene(myStage));
                     myStage.setScene(scene);
            }
        });
        Label backBTN =new Label();
        backBTN.setLayoutX(650);
        backBTN.setLayoutY(80);
        backBTN.setPrefSize(80, 80);
        backBTN.setGraphic(new ImageView(new Image("Icons/Home.png", 80, 80, true, true)));
        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                LoginModule l =new LoginModule();
                l.resetRepo();
                Scene scene = new Scene(new Home(myStage));
                myStage.setScene(scene);
            }
        });
        
        Label soundLBL = new Label();
        soundLBL.setLayoutX(30);
        soundLBL.setLayoutY(5);
        soundLBL.setPrefSize(50, 50);
        soundLBL.setGraphic(new ImageView(new Image("Icons/mute.png", 50, 50, true, true)));
        soundLBL.setOnMouseClicked((MouseEvent event) -> {
            if (XOGameCLient.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                soundLBL.setGraphic(new ImageView(new Image("Icons/mute.png", 50, 50, true, true)));
                XOGameCLient.mediaPlayer.pause();
            } else {
                soundLBL.setGraphic(new ImageView(new Image("Icons/unmute.png", 50, 50, true, true)));
                XOGameCLient.mediaPlayer.play();
            }
        });
        getChildren().add(soundLBL);
        
        
        getChildren().add(backBTN);
        
        getChildren().add(userNameTXT);
        getChildren().add(passwordTXT);
        getChildren().add(loginBTN);
        getChildren().add(registerBTN);
        setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);
    }
}
