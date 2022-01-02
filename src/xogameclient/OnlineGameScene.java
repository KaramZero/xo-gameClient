package xogameclient;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static xogameclient.Home.bGround;
import static xogameclient.Home.closeLBL;
import static xogameclient.Home.minimizeLBL;
import xogameclient.online.login.LoginViewModel;

public  class OnlineGameScene extends AnchorPane {

    protected final ListView lstOnlinePlayers;
    protected final Label label;
    private Stage myStage;
    private LoginViewModel loginViewModel;
    private ArrayList<String> listUsersOnline;

    public OnlineGameScene(Stage stage) {
        myStage = stage;
        loginViewModel = new LoginViewModel();
        listUsersOnline = new ArrayList<>();

        lstOnlinePlayers = new ListView();
        label = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(691.0);
        setPrefWidth(812.0);

        lstOnlinePlayers.setLayoutX(590.0);
        lstOnlinePlayers.setLayoutY(59.0);
        lstOnlinePlayers.setPrefHeight(599.0);
        lstOnlinePlayers.setPrefWidth(200.0);   
        lstOnlinePlayers.setBackground(bGround);
        
         /* listUsersOnline = loginViewModel.getListUserOnline();
          if(listUsersOnline.size()!=0)
          lstOnlinePlayers.setItems((ObservableList) listUsersOnline);*/
          
          
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setLayoutX(592.0);
        label.setLayoutY(14.0);
        label.setPrefHeight(40.0);
        label.setPrefWidth(200.0);
        label.setText("Online Now");

        getChildren().add(lstOnlinePlayers);
        getChildren().add(label);
        setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);
        
    
    }
}
