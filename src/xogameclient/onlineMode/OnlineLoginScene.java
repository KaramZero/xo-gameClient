package xogameclient.onlineMode;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static xogameclient.home.Home.bGround;
import static xogameclient.home.Home.closeLBL;
import static xogameclient.home.Home.minimizeLBL;
import xogameclient.pojo.LoginModel;
import xogameclient.viewModels.LoginViewModel;


public  class OnlineLoginScene extends AnchorPane {

    protected final TextField textField;
    protected final TextField textField0;
    protected final Button button;
    protected final Button button0;
    private Stage myStage;
    private LoginModel loginModel;
    private LoginViewModel loginViewModel;

    public OnlineLoginScene(Stage stage) {
        
        myStage = stage;
        textField = new TextField();
        textField0 = new TextField();
        button = new Button();
        button0 = new Button();
        loginModel = new LoginModel();
        loginViewModel = new LoginViewModel();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(650.0);
        setPrefWidth(850.0);

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
        button.setText("Login");
        
         button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                String username = textField.getText().trim();
                String password = textField0.getText().trim();
                if(!(username.isEmpty())&&(!(password.isEmpty()))){
                    loginModel.setUsername(username);
                    loginModel.setPassword(password);
                    loginViewModel.sendLoginData(loginModel);
                     if(loginViewModel.getLoginData().equals("true")){
                         Scene scene = new Scene(new OnlineGameScene(myStage));
                         myStage.setScene(scene);
                     }
                     else{
                               Alert a = new Alert(Alert.AlertType.ERROR);
                               a.setContentText("login fails");
                               a.show();
                     }
                }
            }
        });


        button0.setLayoutX(277.0);
        button0.setLayoutY(403.0);
        button0.setMnemonicParsing(false);
        button0.setPrefHeight(52.0);
        button0.setPrefWidth(117.0);
        button0.setText("Sign Up");
        
        button0.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene scene = new Scene(new OnlineRegisterScene(myStage));
                     myStage.setScene(scene);
            }
        });
        
        getChildren().add(textField);
        getChildren().add(textField0);
        getChildren().add(button);
        getChildren().add(button0);
        setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);
    }
}
