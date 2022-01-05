package xogameclient;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static xogameclient.Home.bGround;
import static xogameclient.Home.closeLBL;
import static xogameclient.Home.minimizeLBL;
import xogameclient.online.login.LoginViewModel;
import xogameclient.online.login.Repo;

public  class OnlineGameScene extends AnchorPane {

    protected  ListView lstOnlinePlayers;
    protected final Label label;
    private Stage myStage;
    private LoginViewModel loginViewModel;
    private ObservableList<String> listUsersOnline;

    public OnlineGameScene(Stage stage) {
        myStage = stage;
        loginViewModel = new LoginViewModel();
        listUsersOnline= FXCollections.observableArrayList();

        lstOnlinePlayers = new ListView();
        label = new Label();

        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(650.0);
        setPrefWidth(850.0);

        lstOnlinePlayers.setLayoutX(590.0);
        lstOnlinePlayers.setLayoutY(59.0);
        lstOnlinePlayers.setPrefHeight(599.0);
        lstOnlinePlayers.setPrefWidth(200.0);   
        lstOnlinePlayers.setBackground(bGround);
        lstOnlinePlayers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                String s = new String();
               s = (String) lstOnlinePlayers.getSelectionModel().getSelectedItem();

               loginViewModel.sendRequestGame(s);
            }
        });
        
        new Thread(){
            @Override
            public void run() {
                while(true){
              // loginViewModel.getListUserOnline();
               
               //loginViewModel.readstream();                     

                  if (Repo.listUsersOnline != null) {                  
                     
                      listUsersOnline = Repo.listUsersOnline;
                       Platform.runLater(() -> {
                          lstOnlinePlayers.setItems(listUsersOnline);
                    });
                      
                        Repo.listUsersOnline = null;
                   }
                  if(Repo.gameRequest != null ){
                       System.out.println("request from  "+Repo.gameRequest);
                       Repo.gameRequest = null;
                   
                   }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OnlineGameScene.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }       
            }           
        }.start();
                       
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
