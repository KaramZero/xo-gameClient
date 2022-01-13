package onlineMode;


import home.Home;
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
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import modules.GameModule;

public  class OnlineGameScene extends AnchorPane {

    protected  ListView lstOnlinePlayers;
    protected final Label label;
    protected  Label labelUsername;
    protected  Label labelScore;
    private Stage myStage;
    private GameModule gameModule;
    private ObservableList<String> listUsersOnline;
    public static int score = 0  ;
    
        
    private double xOffset = 0;
    private double yOffset = 0;



    public OnlineGameScene(Stage stage) {
        myStage = stage;
        gameModule = new GameModule();
        listUsersOnline= FXCollections.observableArrayList();

        lstOnlinePlayers = new ListView();
        label = new Label();
        labelScore = new Label();
        labelUsername = new Label();
        
         
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

        lstOnlinePlayers.setLayoutX(590.0);
        lstOnlinePlayers.setLayoutY(59.0);
        lstOnlinePlayers.setPrefHeight(599.0);
        lstOnlinePlayers.setPrefWidth(200.0);   
        lstOnlinePlayers.setBackground(bGround); 
        lstOnlinePlayers.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, CornerRadii.EMPTY, Insets.EMPTY)));
        
        lstOnlinePlayers.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (event.getClickCount() == 2) {
                    String username = new String();
                    username = (String) lstOnlinePlayers.getSelectionModel().getSelectedItem();
                    gameModule.sendRequestGame(username);
                }
            }
        });
                       
        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        label.setLayoutX(592.0);
        label.setLayoutY(14.0);
        label.setPrefHeight(40.0);
        label.setPrefWidth(200.0);
        label.setText("Online Now");
        
      
        
        labelScore.setAlignment(javafx.geometry.Pos.CENTER);
        labelScore.setContentDisplay(javafx.scene.control.ContentDisplay.CENTER);
        labelScore.setLayoutX(200);
        labelScore.setLayoutY(14.0);
        labelScore.setPrefHeight(40);
        labelScore.setPrefWidth(400);
        labelScore.setTextFill(Color.AQUA);
        labelScore.setFont(new Font(30));
        
        labelScore.setText("your Score is "+String.valueOf(score));

        getChildren().add(lstOnlinePlayers);
        getChildren().add(label);
        getChildren().add(labelScore);

        setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);
        
        t.start();
    }
    
    
   Thread t = new Thread() {
            @Override
            public void run() {
                while (true) {
                    ////// Check for errors ////////////
                    
                     String er = gameModule.getErrors();
                    if(er != null){
                        Home.onlineFlag = false;
                        Scene scene = new Scene(new Home(myStage));
                        Platform.runLater(() -> {
                            t.stop();
                            Alert a = new Alert(AlertType.ERROR);
                            a.setContentText("connection error");
                            a.showAndWait();

                            myStage.setScene(scene);
                        });
                    
                    }
                    
                    ////// Check for new list /////////
                     ObservableList<String> list = gameModule.getListUsersOnline();
                    if ( list != null) {
                        listUsersOnline = list;
                        Platform.runLater(() -> {
                            lstOnlinePlayers.setItems(listUsersOnline);
                        });
                    }
                                    
                    ////// Check for new Confirm ///////
                    String gameRequest[] = gameModule.getRequestConfirm();
                    if (gameRequest != null) {
                      
                        if (gameRequest[1].equals("yes")) {
                            Scene s = new Scene(new OnlineGame(myStage, true));
                            
                            Platform.runLater(() -> {
                                 t.stop();
                                myStage.setScene(s);
                            });
                        } else {
                            Platform.runLater(() -> {
                                ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);
                                Alert a = new Alert(AlertType.INFORMATION, gameRequest[0] + " says " + gameRequest[1], ok);
                                a.setX(myStage.getX() + 250);
                                a.setY(myStage.getY() + 250);
                                new java.util.Timer().schedule(
                                        new java.util.TimerTask() {
                                    @Override
                                    public void run() {
                                        Button cancelButton = (Button) a.getDialogPane().lookupButton(ok);
                                        Platform.runLater(() -> {
                                            cancelButton.fire();
                                        });
                                    }
                                },
                                        5000
                                );

                                a.show();
                            });
                        }
                    }

                    ////// Check for new Request ///////
                    String user = gameModule.getGameRequest();
                    if (user != null) {
                       
                        Platform.runLater(() -> {
                            ButtonType ok = new ButtonType("OK", ButtonBar.ButtonData.OK_DONE);                            
                            ButtonType no = new ButtonType("NO", ButtonBar.ButtonData.CANCEL_CLOSE);
                            Alert alert = new Alert(AlertType.NONE,"Request from "+user,ok, no);
                            alert.setX(myStage.getX()+250);
                            alert.setY(myStage.getY()+250);
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
                               gameModule.sendRequestConfirm(user, "yes");
                            }else  {
                               gameModule.sendRequestConfirm(user, "no");
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
