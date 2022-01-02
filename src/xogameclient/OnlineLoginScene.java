package xogameclient;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static xogameclient.Home.bGround;
import static xogameclient.Home.closeLBL;
import static xogameclient.Home.minimizeLBL;
import xogameclient.online.login.LoginData;
import xogameclient.online.login.LoginViewModel;

public  class OnlineLoginScene extends AnchorPane {

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
    private Stage myStage;
    private LoginData loginData;
    private LoginViewModel loginViewModel;

    public OnlineLoginScene(Stage stage) {
        
        myStage = stage;
        loginData = new LoginData();
        loginViewModel = new LoginViewModel();
        
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
        setPrefHeight(691.0);
        setPrefWidth(812.0);

        txtUserName.setLayoutX(303.0);
        txtUserName.setLayoutY(97.0);
        txtUserName.setPrefHeight(52.0);
        txtUserName.setPrefWidth(259.0);
        txtUserName.setPromptText("user name");

        btnSignUp.setLayoutX(419.0);
        btnSignUp.setLayoutY(503.0);
        btnSignUp.setMnemonicParsing(false);
        btnSignUp.setPrefHeight(52.0);
        btnSignUp.setPrefWidth(117.0);
        btnSignUp.setText("Sign up");

        btnLogin.setLayoutX(277.0);
        btnLogin.setLayoutY(505.0);
        btnLogin.setMnemonicParsing(false);
        btnLogin.setPrefHeight(52.0);
        btnLogin.setPrefWidth(117.0);
        btnLogin.setText("Login");
        
        btnLogin.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = txtName.getText().trim();
                String password = txtPassword.getText().trim();
                if(!(username.isEmpty())&&(!(password.isEmpty()))){
                    loginData.setUsername(username);
                    loginData.setPassword(password);
                    loginViewModel.sendLoginData(loginData);
                     if(loginViewModel.getLoginData().equals("true")){
                         Scene scene = new Scene(new OnlineGameScene(myStage));
                         myStage.setScene(scene);
                     }
                     else{
                               Alert a = new Alert(AlertType.ERROR);
                               a.setContentText("login fails");
                               a.show();
                     }
                }
            }
        });

        txtPassword.setLayoutX(303.0);
        txtPassword.setLayoutY(301.0);
        txtPassword.setPrefHeight(52.0);
        txtPassword.setPrefWidth(259.0);
        txtPassword.setPromptText("Password");

        lblUserName.setLayoutX(152.0);
        lblUserName.setLayoutY(97.0);
        lblUserName.setPrefHeight(52.0);
        lblUserName.setPrefWidth(117.0);
        lblUserName.setText("User  Name");

        lblPassword.setLayoutX(152.0);
        lblPassword.setLayoutY(293.0);
        lblPassword.setPrefHeight(52.0);
        lblPassword.setPrefWidth(117.0);
        lblPassword.setText("Password");

        txtName.setLayoutX(303.0);
        txtName.setLayoutY(163.0);
        txtName.setPrefHeight(52.0);
        txtName.setPrefWidth(259.0);
        txtName.setPromptText("Name");

        lblName.setLayoutX(152.0);
        lblName.setLayoutY(163.0);
        lblName.setPrefHeight(52.0);
        lblName.setPrefWidth(117.0);
        lblName.setText("Name");

        txtEmail.setLayoutX(303.0);
        txtEmail.setLayoutY(230.0);
        txtEmail.setPrefHeight(52.0);
        txtEmail.setPrefWidth(259.0);
        txtEmail.setPromptText("Email");

        lblEmail.setLayoutX(152.0);
        lblEmail.setLayoutY(230.0);
        lblEmail.setPrefHeight(52.0);
        lblEmail.setPrefWidth(117.0);
        lblEmail.setText("Email");

        txtConfirmPassword.setLayoutX(303.0);
        txtConfirmPassword.setLayoutY(374.0);
        txtConfirmPassword.setPrefHeight(52.0);
        txtConfirmPassword.setPrefWidth(259.0);
        txtConfirmPassword.setPromptText("Password");

        lblConfirmPassword.setLayoutX(152.0);
        lblConfirmPassword.setLayoutY(366.0);
        lblConfirmPassword.setPrefHeight(52.0);
        lblConfirmPassword.setPrefWidth(117.0);
        lblConfirmPassword.setText("Confirm pass");

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
