package onlineMode;

import home.Home;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static home.Home.bGround;
import static home.Home.closeLBL;
import static home.Home.minimizeLBL;
import home.XOGameCLient;
import javafx.application.Platform;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import modules.GameModule;
import modules.LoginModule;
import modules.RegisterModule;
import pojo.RegisterModel;

public  class OnlineRegisterScene extends AnchorPane {

    protected final TextField txtUserName;
    protected final Button btnSignUp;
    protected final Button btnLogin;
    protected final PasswordField txtPassword;
    protected final Label lblUserName;
    protected final Label lblPassword;
    protected final TextField txtName;
    protected final Label lblName;
    protected final TextField txtEmail;
    protected final Label lblEmail;
    protected final PasswordField txtConfirmPassword;
    protected final Label lblConfirmPassword;
    private  Stage myStage;
    private RegisterModel registerModel;
    private RegisterModule registerModule;
    public ProgressIndicator pb = new ProgressIndicator();
    String str = null;
    
    
    private double xOffset = 0;
    private double yOffset = 0;

   


    public OnlineRegisterScene(Stage stage) {
        
        myStage = stage;
        registerModel = new RegisterModel();
        registerModule = new RegisterModule();
                
        txtUserName = new TextField();
        btnSignUp = new Button();
        btnLogin = new Button();
        txtPassword = new PasswordField();
        lblUserName = new Label();
        lblPassword = new Label();
        txtName = new TextField();
        lblName = new Label();
        txtEmail = new TextField();
        lblEmail = new Label();
        txtConfirmPassword = new PasswordField();
        lblConfirmPassword = new Label();
        
        
         
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
        
                 
        pb.setLayoutX(380);
        pb.setLayoutY(400);

        
  
        btnSignUp.setLayoutX(445.0);
        btnSignUp.setLayoutY(450.0);
        btnSignUp.setMnemonicParsing(false);
        btnSignUp.setPrefHeight(52.0);
        btnSignUp.setPrefWidth(117.0);
        btnSignUp.setText("Sign up");
        
         btnSignUp.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = txtUserName.getText().trim();
                String name = txtName.getText().trim();
                String password = txtPassword.getText().trim();
                String confirmPassword = txtConfirmPassword.getText().trim();
                String email=txtEmail.getText().trim();
                
                if(!(username.isEmpty())&&(!(password.isEmpty()))&&(!(confirmPassword.isEmpty()))&&(!(email.isEmpty()))&&(!(name.isEmpty()))){
                    if (password.equals(confirmPassword) && validation.Validation.isValidEmail(email)) {
                        registerModel.setUsername(username);
                        registerModel.setPassword(password);
                        registerModel.setName(name);
                        registerModel.setEmail(email);
                        registerModel.setScore(0);
                        getChildren().add(pb);
                        btnSignUp.setDisable(true);
                        btnLogin.setDisable(true);
                        

                        registerModule.sendregisterData(registerModel);
                        
                         Thread t = new Thread() {
                            @Override
                            public void run() {
                                str = registerModule.getregisterData();
                            }

                        };
                        t.start();
                        
                        new java.util.Timer().schedule(
                                new java.util.TimerTask() {
                            @Override
                            public void run() {
                                Platform.runLater(() -> {
                                    t.stop();
                                    if (str == null) {
                                        Alert a = new Alert(Alert.AlertType.ERROR);
                                        a.setContentText("connection error");
                                        a.showAndWait();
                                        LoginModule l = new LoginModule();
                                        l.resetRepo();
                                        myStage.setScene(new Scene(new Home(myStage)));
                                    } else if (str.equals("true")) {
                                        OnlineGameScene.score = GameModule.getScore();
                                        OnlineGameScene.userName = txtUserName.getText().trim();
                                        Home.onlineScene = new Scene(new OnlineGameScene(myStage));
                                        Home.onlineFlag = true;
                                        myStage.setScene(Home.onlineScene);
                                    } else {
                                        getChildren().remove(pb);
                                        btnSignUp.setDisable(false);
                                        btnLogin.setDisable(false);
                                        Alert a = new Alert(AlertType.ERROR);
                                        a.setContentText("This UserAccount Already Exist");
                                        a.show();
                                    }

                                });
                            }
                        },
                                2000
                        );

                    }
                    else {
                        if (! validation.Validation.isValidEmail(email)) {
                            Alert a = new Alert(AlertType.ERROR);
                            a.setContentText("Email isn't valid");
                            a.show();
                        }else if (! password.equals(confirmPassword)) {
                            Alert a = new Alert(AlertType.ERROR);
                            a.setContentText("Confirm Your Password Correctly ");
                            a.show();
                        }
                        
                    }
                      
                }
                else {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Please Complete Your Data");
                    a.show();
                }
            }

        });

        btnLogin.setLayoutX(303.0);
        btnLogin.setLayoutY(450.0);
        btnLogin.setMnemonicParsing(false);
        btnLogin.setPrefHeight(52.0);
        btnLogin.setPrefWidth(117.0);
        btnLogin.setText("Login");
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                         Scene scene = new Scene(new OnlineLoginScene(myStage));
                         myStage.setScene(scene);
            }
        });

        
        lblUserName.setLayoutX(152.0);
        lblUserName.setLayoutY(97.0);
        lblUserName.setPrefHeight(52.0);
        lblUserName.setPrefWidth(117.0);
        lblUserName.setTextFill(Color.AQUA);
        lblUserName.setText("User  Name");

        txtUserName.setLayoutX(303.0);
        txtUserName.setLayoutY(97.0);
        txtUserName.setPrefHeight(52.0);
        txtUserName.setPrefWidth(259.0);
        txtUserName.setPromptText("user name");

        lblPassword.setLayoutX(152.0);
        lblPassword.setLayoutY(293.0);
        lblPassword.setPrefHeight(52.0);
        lblPassword.setPrefWidth(117.0);
        lblPassword.setTextFill(Color.AQUA);
        lblPassword.setText("Password");
        
        txtPassword.setLayoutX(303.0);
        txtPassword.setLayoutY(301.0);
        txtPassword.setPrefHeight(52.0);
        txtPassword.setPrefWidth(259.0);
        txtPassword.setPromptText("Password");

         lblName.setLayoutX(152.0);
        lblName.setLayoutY(163.0);
        lblName.setPrefHeight(52.0);
        lblName.setPrefWidth(117.0);
        lblName.setTextFill(Color.AQUA);
        lblName.setText("Name");
        
        txtName.setLayoutX(303.0);
        txtName.setLayoutY(163.0);
        txtName.setPrefHeight(52.0);
        txtName.setPrefWidth(259.0);
        txtName.setPromptText("Name");


        lblEmail.setLayoutX(152.0);
        lblEmail.setLayoutY(230.0);
        lblEmail.setPrefHeight(52.0);
        lblEmail.setPrefWidth(117.0);
        lblEmail.setTextFill(Color.AQUA);
        lblEmail.setText("Email");

        txtEmail.setLayoutX(303.0);
        txtEmail.setLayoutY(230.0);
        txtEmail.setPrefHeight(52.0);
        txtEmail.setPrefWidth(259.0);
        txtEmail.setPromptText("Email");

        
        lblConfirmPassword.setLayoutX(152.0);
        lblConfirmPassword.setLayoutY(366.0);
        lblConfirmPassword.setPrefHeight(52.0);
        lblConfirmPassword.setTextFill(Color.AQUA);
        lblConfirmPassword.setPrefWidth(117.0);
        lblConfirmPassword.setText("Confirm pass");

        txtConfirmPassword.setLayoutX(303.0);
        txtConfirmPassword.setLayoutY(374.0);
        txtConfirmPassword.setPrefHeight(52.0);
        txtConfirmPassword.setPrefWidth(259.0);
        txtConfirmPassword.setPromptText("Password");
        
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

        getChildren().add(txtUserName);
        getChildren().add(btnSignUp);
        getChildren().add(btnLogin);
        getChildren().add(txtPassword);
        getChildren().add(lblUserName);
        getChildren().add(lblPassword);
        getChildren().add(txtName);
        getChildren().add(lblName);
        getChildren().add(txtEmail);
        getChildren().add(lblEmail);
        getChildren().add(txtConfirmPassword);
        getChildren().add(lblConfirmPassword);
         setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);

    }
}
