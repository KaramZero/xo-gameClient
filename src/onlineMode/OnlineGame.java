package onlineMode;

import Record.RecordModel;
import Video.VideoModel;
import home.Home;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static home.Home.bGround;
import static home.Home.minimizeLBL;
import home.XOGameCLient;
import java.io.File;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.media.MediaPlayer;
import xo.XOModel;
import modules.GameModule;
import vsPcMode.levels.Move;
import static xo.XOBoard.boardLBL;
import static xo.XOBoard.btnO;
import static xo.XOBoard.btnX;
import static xo.XOBoard.buttons;
import static xo.XOBoard.clearBoard;
import static xo.XOBoard.myBoard;
import static xo.XOBoard.setBTNs;
import static xo.XOBoard.setDisableBtn;
import static xo.XOModel.checkWin;
import static xo.XOModel.isEmptyBoard;

public class OnlineGame extends AnchorPane {

    private GameModule gameModule;
    boolean flag = true;
    boolean playFlag = true;
    private String move;
    RecordModel recordModel;
    private Label RecordBTN;
    boolean recordFlag;
    File file;
    ListView<String> list;
    String[][] recordArray;
    int counter;
    Label backBTN = new Label();
    
        
    private double xOffset = 0;
    private double yOffset = 0;



    Image player = new Image("Icons/x.png", 60, 60, true, true);
    ImageView x = new ImageView(player);
    char playerChar;

    Image playerTwo = new Image("Icons/o.png", 60, 60, true, true);
    ImageView o = new ImageView(playerTwo);
    char playerTwoChar;
    
     public static Label closeLBL;
     
    Stage myStage;

    public OnlineGame(Stage stage, boolean myTurn) {
        counter = 0;
        recordArray = new String[9][3];
        recordModel = new RecordModel();
        RecordBTN = new Label();
        recordFlag = false;
        list = new ListView<String>();

        myStage = stage;
        gameModule = new GameModule();
        move = "playing";
        
        closeLBL = new Label();
        
         
        this.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        this.setOnMouseDragged((MouseEvent event) -> {
            myStage.setX(event.getScreenX() - xOffset);
            myStage.setY(event.getScreenY() - yOffset);
        });
        
        

        t.start();
        t.suspend();

        getChildren().add(boardLBL);

        setBTNs();
        clearBoard();
        setButtons();
        setDisableBtn(true);

        setId("AnchorPane");
        setPrefHeight(650);
        setPrefWidth(850);

        btnX.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player = new Image("Icons/x.png", 60, 60, true, true);
                playerTwo = new Image("Icons/o.png", 60, 60, true, true);
                playerChar = 'X';
                playerTwoChar = 'O';
                btnO.setDisable(true);
                btnX.setOnMouseClicked(null);
                gameModule.sendPlayingChar("X");

                setDisableBtn(false);

                RecordBTN.setDisable(true);
            }
        });
        btnO.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player = new Image("Icons/o.png", 60, 60, true, true);
                playerTwo = new Image("Icons/x.png", 60, 60, true, true);
                playerChar = 'O';
                playerTwoChar = 'X';

                btnO.setOnMouseClicked(null);
                btnX.setDisable(true);
                gameModule.sendPlayingChar("O");

                setDisableBtn(false);

                RecordBTN.setDisable(true);
            }
        });
      
        backBTN.setLayoutX(650);
        backBTN.setLayoutY(80);
        backBTN.setPrefSize(80, 80);
        backBTN.setGraphic(new ImageView(new Image("Icons/Home.png", 80, 80, true, true)));
        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (move.equals("playing")) {
                    gameModule.sendMove(-1, -1, move);
                }

                clearBoard();
                t.stop();
                Scene sc = new Scene(new OnlineGameScene(myStage));
                stage.setScene(sc);

            }
        });
        
        closeLBL.setLayoutX(800);
        closeLBL.setLayoutY(5);
        closeLBL.setPrefSize(50, 50);
        closeLBL.setGraphic(new ImageView(new Image("Icons/close.png", 50, 50, true, true)));
        closeLBL.setOnMouseClicked((MouseEvent event) -> {
            if (move.equals("playing")) {
                gameModule.sendMove(-1, -1, move);
            }
            try {
                sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(OnlineGame.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.exit(0);
        });
       
        
        RecordBTN.setLayoutX(120);
        RecordBTN.setLayoutY(80);
        RecordBTN.setPrefSize(80, 80);
        RecordBTN.setGraphic(new ImageView(new Image("Icons/record.png", 60, 60, true, true)));

        RecordBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                recordFlag = true;
                RecordBTN.setDisable(true);

            }

        });

        if (!myTurn) {
            getMyChar();
        }
        
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
        
      
        setBackground(bGround);
        
        Platform.runLater(() -> {
            getChildren().add(btnO);
            getChildren().add(btnX);
            getChildren().add(backBTN);
            getChildren().add(minimizeLBL);
            getChildren().add(closeLBL);
            getChildren().add(RecordBTN);
        });
    }
    
   
    private void getMyChar() {
        btnO.setDisable(true);
        btnX.setDisable(true);
        playFlag = false;

        new Thread() {
            @Override
            public void run() {
                while (true) {
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OnlineGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     ////// Check for errors ////////////
                    
                     String er = gameModule.getErrors();
                    if(er != null){
                        Home.onlineFlag = false;
                        Scene scene = new Scene(new Home(myStage));
                        Platform.runLater(() -> {
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setContentText("connection error");
                            a.showAndWait();
                            myStage.setScene(scene);
                        });
                    
                    }
                     Move m = gameModule.getMove();
                if (m != null) {
                   
                    if (m.row == -1) {
                        clearBoard();
                        Platform.runLater(() -> {
                            Scene s = new Scene(new VideoModel(myStage, "Win", 3));
                            myStage.setScene(s);
                            t.stop();
                        });
                        

                    }}

                    String playerChar = gameModule.getPlayingChar();
                    if (playerChar != null) {
                        if (playerChar.equals("O")) {
                            setx();
                        } else if (playerChar.equals("X")) {
                            seto();
                        }
                        break;
                    }  
                }
                RecordBTN.setDisable(true);
            }

        }.start();

    }

    private void seto() {
        player = new Image("Icons/o.png", 60, 60, true, true);
        playerTwo = new Image("Icons/x.png", 60, 60, true, true);
        playerChar = 'O';
        playerTwoChar = 'X';
        btnO.setOnMouseClicked(null);
        btnX.setDisable(true);
        btnO.setDisable(false);

        setDisableBtn(false);
        playFlag = false;
        t.resume();

    }

    private void setx() {

        player = new Image("Icons/x.png", 60, 60, true, true);
        playerTwo = new Image("Icons/o.png", 60, 60, true, true);
        playerChar = 'X';
        playerTwoChar = 'O';
        btnX.setOnMouseClicked(null);
        btnO.setDisable(true);
        btnX.setDisable(false);

        setDisableBtn(false);
        playFlag = false;
        t.resume();
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
                            Alert a = new Alert(Alert.AlertType.ERROR);
                            a.setContentText("connection error");
                            a.showAndWait();

                            myStage.setScene(scene);
                        });
                    
                    }


                Move m = gameModule.getMove();
                if (m != null) {
                   
                    if (m.row == -1) {
                        clearBoard();
                        Platform.runLater(() -> {
                            Scene s = new Scene(new VideoModel(myStage, "Win", 3));
                            myStage.setScene(s);
                            t.stop();
                        });
                        

                    } else {
                        myBoard[m.row][m.col] = playerTwoChar;
                        Platform.runLater(() -> {
                            buttons[m.row][m.col].setGraphic(new ImageView(playerTwo));
                        });
                        if (recordFlag) {
                            recordArray[counter][0] = new String("" + playerTwoChar);
                            recordArray[counter][1] = String.valueOf(m.row);
                            recordArray[counter][2] = String.valueOf(m.col);
                            counter++;
                         }
                        playFlag = true;
                        if (XOModel.checkWin(myBoard)) {
                            clearBoard();
                            setDisableBtn(true);
                            backBTN.setDisable(false);
                            if (recordFlag) recordModel.saveRecord(recordArray,counter-1);
                          
                            Scene s = new Scene(new VideoModel(myStage, "Lose", 3));
                            Platform.runLater(() -> {
                                t.stop();
                                myStage.setScene(s);
                            });

                        } else if (!(XOModel.isEmptyBoard(myBoard))) {
                            clearBoard();
                            setDisableBtn(true);
                            backBTN.setDisable(false);
                            if (recordFlag) recordModel.saveRecord(recordArray,counter-1);

                        }
                    }
                    t.suspend();
                }
                try {
                    sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(OnlineGame.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    };

    EventHandler a = (EventHandler) (Event event) -> {
        Button btnTemp = ((Button) event.getSource());
        if (playFlag) {

            int xCord = 0, yCord = 0;
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    if (buttons[i][j] == btnTemp) {
                        xCord = i;
                        yCord = j;
                    }
                }
            }

            if (myBoard[xCord][yCord] == '_') {
                btnTemp.setGraphic(new ImageView(player));
                myBoard[xCord][yCord] = playerChar;
                if (recordFlag) {
                    recordArray[counter][0] = new String("" + playerChar);
                    recordArray[counter][1] = String.valueOf(xCord);
                    recordArray[counter][2] = String.valueOf(yCord);
                    counter++;
                }
                playFlag = false;

                if (checkWin(myBoard)) {
                    move = "win";
                    clearBoard();
                    t.stop();
                    setDisableBtn(true);
                    backBTN.setDisable(false);
                    gameModule.sendMove(xCord, yCord, move);
                   
                    if (recordFlag) recordModel.saveRecord(recordArray,counter-1);
                    Scene s = new Scene(new VideoModel(myStage, "Win", 3));
                    myStage.setScene(s);

                } else if (!(isEmptyBoard(myBoard))) {
                    move = "full";
                    clearBoard();
                    setDisableBtn(true);
                    backBTN.setDisable(false);
                    gameModule.sendMove(xCord, yCord, move);
                    if (recordFlag) recordModel.saveRecord(recordArray, counter - 1);
                } else
                    gameModule.sendMove(xCord, yCord, move);
            }

        }

        t.resume();
    };

    private void setButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnAction(a);
                getChildren().add(buttons[i][j]);
            }
        }
        btnO.setDisable(false);
        btnX.setDisable(false);
    }
}
