package onlineMode;


import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static home.Home.bGround;
import static home.Home.closeLBL;
import static home.Home.minimizeLBL;
import viewModels.GameViewModel;

public  class OnlineGameScene extends AnchorPane {

    protected  ListView lstOnlinePlayers;
    protected final Label label;
    private Stage myStage;
    private GameViewModel gameViewModel;
    private ObservableList<String> listUsersOnline;

    public OnlineGameScene(Stage stage) {
        myStage = stage;
        gameViewModel = new GameViewModel();
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
                String username = new String();
               username = (String) lstOnlinePlayers.getSelectionModel().getSelectedItem();
               gameViewModel.sendRequestGame(username);
            }
        });
                       
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
        
        t.start();
    }
    
    
   Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
 
                    ////// Check for new list /////////
                     ObservableList<String> list = gameViewModel.getListUsersOnline();
                    if ( list != null) {
                        listUsersOnline = list;
                        Platform.runLater(() -> {
                            lstOnlinePlayers.setItems(listUsersOnline);
                        });
                    }
                    
                
                    ////// Check for new Confirm ///////
                    String gameRequest[] = gameViewModel.getRequestConfirm();
                    if (gameRequest != null) {
                      
                        if (gameRequest[1].equals("yes")) {
                            Scene s = new Scene(new OnlineGame(myStage, true));
                            
                            Platform.runLater(() -> {
                                 t.stop();
                                myStage.setScene(s);
                            });
                        } else {
                           Platform.runLater(() -> {
                        Alert a = new Alert(AlertType.INFORMATION);
                        a.setContentText(gameRequest[0]+" says "+ gameRequest[1]);
                        a.show();
                        });
                        }
                    }

               
                    ////// Check for new Request ///////
                    String user = gameViewModel.getGameRequest();
                    if (user != null) {
                       
                        Platform.runLater(() -> {
                            ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);                            
                            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                            Alert alert = new Alert(AlertType.NONE,"Request from "+user,ok, no);
                            alert.setTitle("Confirm... ");
                             
                            
                             new java.util.Timer().schedule(
                                    new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    Button cancelButton =( Button ) alert.getDialogPane().lookupButton(no);
                                    Platform.runLater(() -> { cancelButton.fire(); });
                                }
                            },
                                    5000
                            );
                            
                            Optional<ButtonType> result = alert.showAndWait();
                            
                            if (result.orElse(no) == ok) {
                              
                                Scene s1 = new Scene(new OnlineGame(myStage,false));
                                t.stop();
                                myStage.setScene(s1);
                               gameViewModel.sendRequestConfirm(user, "yes");
                            }else  {
                               gameViewModel.sendRequestConfirm(user, "no");
                            }
                        });
                    }
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OnlineGameScene.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        };
}
