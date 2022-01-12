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
import javafx.scene.control.PasswordField;
import pojo.LoginModel;
import modules.LoginModule;


public  class OnlineLoginScene extends AnchorPane {

    protected final TextField userNameTXT;
    protected final PasswordField passwordTXT;
    protected final Button loginBTN;
    protected final Button registerBTN;
    private Stage myStage;
    private LoginModel loginModel;
    private LoginModule loginViewModel;

    public OnlineLoginScene(Stage stage) {
        
        myStage = stage;
        userNameTXT = new TextField();
        passwordTXT = new PasswordField();
        loginBTN = new Button();
        registerBTN = new Button();
        loginModel = new LoginModel();
        loginViewModel = new LoginModule();

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
        
        loginBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = userNameTXT.getText().trim();
                String password = passwordTXT.getText().trim();
                if(!(username.isEmpty())&&(!(password.isEmpty()))){
                    loginModel.setUsername(username);
                    loginModel.setPassword(password);
                    loginViewModel.sendLoginData(loginModel);
                     if(loginViewModel.getLoginData().equals("true")){
                          Home.onlineFlag = true;
                          Home.onlineScene = new Scene(new OnlineGameScene(myStage));
                         myStage.setScene(Home.onlineScene);
                     }
                     else{
                               Alert a = new Alert(Alert.AlertType.ERROR);
                               a.setContentText("login fails");
                               a.show();
                     }
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
        
        getChildren().add(userNameTXT);
        getChildren().add(passwordTXT);
        getChildren().add(loginBTN);
        getChildren().add(registerBTN);
        setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);
    }
}
