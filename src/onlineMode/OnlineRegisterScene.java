package onlineMode;

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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import modules.GameModule;
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

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(650.0);
        setPrefWidth(850.0);

  
        btnSignUp.setLayoutX(419.0);
        btnSignUp.setLayoutY(503.0);
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
                    if (password.equals(confirmPassword)&&validation.Validation.isValidEmail(email)) {
                        registerModel.setUsername(username);
                        registerModel.setPassword(password);
                        registerModel.setName(name);
                        registerModel.setEmail(email);
                        registerModel.setScore(0);

                        registerModule.sendregisterData(registerModel);

                        if (registerModule.getregisterData().equals("true")) {
                            OnlineGameScene.score = GameModule.getScore();
                            Scene scene = new Scene(new OnlineGameScene(myStage));
                            myStage.setScene(scene);
                        } else {
                            Alert a = new Alert(AlertType.ERROR);
                            a.setContentText("This UserAccount Already Exist");
                            a.show();
                        }
                    }
                    else {
                        Alert a = new Alert(AlertType.ERROR);
                        a.setContentText("Confirm Your Password Correctly Or email isn't valid");
                        a.show();
                    }
                      
                }
                else {
                    Alert a = new Alert(AlertType.ERROR);
                    a.setContentText("Please Complete Your Data");
                    a.show();
                }
            }

        });

        btnLogin.setLayoutX(277.0);
        btnLogin.setLayoutY(505.0);
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
        lblEmail.setText("Email");

        txtEmail.setLayoutX(303.0);
        txtEmail.setLayoutY(230.0);
        txtEmail.setPrefHeight(52.0);
        txtEmail.setPrefWidth(259.0);
        txtEmail.setPromptText("Email");

        
        lblConfirmPassword.setLayoutX(152.0);
        lblConfirmPassword.setLayoutY(366.0);
        lblConfirmPassword.setPrefHeight(52.0);
        lblConfirmPassword.setPrefWidth(117.0);
        lblConfirmPassword.setText("Confirm pass");

        txtConfirmPassword.setLayoutX(303.0);
        txtConfirmPassword.setLayoutY(374.0);
        txtConfirmPassword.setPrefHeight(52.0);
        txtConfirmPassword.setPrefWidth(259.0);
        txtConfirmPassword.setPromptText("Password");

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
