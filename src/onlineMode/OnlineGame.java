package onlineMode;

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
import home.Home;
import static home.Home.bGround;
import static home.Home.closeLBL;
import static home.Home.minimizeLBL;
import xo.XOModel;
import viewModels.GameViewModel;
import vsPcMode.levels.Move;
import static xo.XOBoard.backBTN;
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

    private GameViewModel gameViewModel;
    boolean flag = true;
    boolean playFlag = true;
    private String move;

    Image player = new Image("Icons/x.png", 60, 60, true, true);
    ImageView x = new ImageView(player);
    char playerChar;

    Image playerTwo = new Image("Icons/o.png", 60, 60, true, true);
    ImageView o = new ImageView(playerTwo);
    char playerTwoChar;
    Stage myStage;

    public OnlineGame(Stage stage, boolean myTurn) {

        myStage = stage;
        gameViewModel = new GameViewModel();
        
        move = "playing";
        
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
                gameViewModel.sendPlayingChar("X");

                setDisableBtn(false);
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
                gameViewModel.sendPlayingChar("O");

                setDisableBtn(false);
            }
        });
        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if (move.equals("playing")) {
                    gameViewModel.sendMove(-1, -1,move);}
                
                clearBoard();
                t.stop();
                Scene sc = new Scene(new OnlineGameScene(myStage));
                stage.setScene(Home.onlineScene);
                
                
            }
        });


        if (!myTurn) {
            getMyChar();
        }

        setBackground(bGround);
        Platform.runLater(() -> {
            getChildren().add(btnO);
            getChildren().add(btnX);
            getChildren().add(backBTN);
            getChildren().add(minimizeLBL);
            getChildren().add(closeLBL);
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
                    
                    String playerChar = gameViewModel.getPlayingChar();
                    if (playerChar != null) {
                        if (playerChar.equals("O")) {
                            setx();
                        } else if (playerChar.equals("X")) {
                            seto();
                        }
                        break;
                    }
                }
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
        buttons[0][0].fire();

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
        buttons[0][0].fire();
    }

    Thread t = new Thread() {
        @Override
        public void run() {
            while (true) {
              
                Move m = gameViewModel.getMove();
                if (m != null) {
                    if (m.row == -1 ) {
                        ///// Show winner here
                        System.out.println(" i win :D ");
                       
                    } 
                    else {
                        myBoard[m.row][m.col] = playerTwoChar;
                        Platform.runLater(() -> {
                            buttons[m.row][m.col].setGraphic(new ImageView(playerTwo));
                        });
                        playFlag = true;
                        if (XOModel.checkWin(myBoard)){                            
                          
                            clearBoard();
                            setDisableBtn(true);
                            backBTN.setDisable(false);

                        } else if (!(XOModel.isEmptyBoard(myBoard))) {
                           
                            clearBoard();
                            setDisableBtn(true);
                            backBTN.setDisable(false);
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
               
                playFlag = false;
                
                if (checkWin(myBoard)) {
                    move = "win";
                    clearBoard();
                    setDisableBtn(true);
                    backBTN.setDisable(false);

                } else if (!(isEmptyBoard(myBoard))) {
                    move = "full";
                    clearBoard();
                    setDisableBtn(true);
                    backBTN.setDisable(false);
                }
                 gameViewModel.sendMove(xCord, yCord, move);
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
