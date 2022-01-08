package onlineMode;

import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
import vsPcMode.VsPcScene;
import vsPcMode.levels.Move;
import static xo.XOBoard.buttons;
import static xo.XOBoard.clearBoard;
import static xo.XOBoard.myBoard;
import static xo.XOBoard.setBTNs;
import static xo.XOBoard.setDisableBtn;
import static xo.XOModel.checkWin;
import static xo.XOModel.isEmptyBoard;

public class OnlineGame extends AnchorPane {

    private Label btnX;
    private Label btnO;
    private Label backBTN;

    private Label boardLBL;
    private GameViewModel gameViewModel;
    boolean flag = true;
    boolean playFlag = true;
    private String move;

    Image player = new Image("Icons/x.png", 60, 60, true, true);
    ImageView x = new ImageView(player);
    char playerChar;

    Image pc = new Image("Icons/o.png", 60, 60, true, true);
    ImageView o = new ImageView(pc);
    char pcChar;
    Stage myStage;

    public OnlineGame(Stage s, boolean myTurn) {

        myStage = s;
        btnO = new Label();
        btnX = new Label();
        backBTN = new Label();

        boardLBL = new Label();
        gameViewModel = new GameViewModel();
        t.start();
        t.suspend();

        boardLBL.setLayoutX(310);
        boardLBL.setLayoutY(210);
        boardLBL.setPrefSize(270, 270);
        boardLBL.setGraphic(new ImageView(new Image("Icons/board.png", 270, 270, true, true)));
        getChildren().add(boardLBL);

        setBTNs();
        clearBoard();
        setButtons();
        
        setDisableBtn(true);

        setId("AnchorPane");
        setPrefHeight(650);
        setPrefWidth(850);

        btnX.setLayoutX(310);
        btnX.setLayoutY(80);
        btnX.setPrefSize(80, 80);
        btnX.setGraphic(new ImageView(new Image("Icons/x.png", 60, 60, true, true)));

        btnO.setLayoutX(470);
        btnO.setLayoutY(80);
        btnO.setPrefSize(80, 80);
        btnO.setGraphic(new ImageView(new Image("Icons/o.png", 60, 60, true, true)));

        btnX.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player = new Image("Icons/x.png", 60, 60, true, true);
                pc = new Image("Icons/o.png", 60, 60, true, true);
                playerChar = 'X';
                pcChar = 'O';
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
                pc = new Image("Icons/x.png", 60, 60, true, true);
                playerChar = 'O';
                pcChar = 'X';

                btnO.setOnMouseClicked(null);
                btnX.setDisable(true);
                gameViewModel.sendPlayingChar("O");

                setDisableBtn(false);
            }
        });

        if (!myTurn) {
            getMyChar();
        }

        backBTN.setLayoutX(650);
        backBTN.setLayoutY(20);
        backBTN.setPrefSize(100, 30);
        backBTN.setGraphic(new ImageView(new Image("Icons/Home.png", 80, 80, true, true)));
        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                if (move.equals("playing")) {
                    gameViewModel.sendMove(-1, -1,move);
                }
                clearBoard();
                t.stop();
                Scene sc = new Scene(new OnlineGameScene(myStage));
                s.setScene(Home.onlineScene);
            }
        });

        setBackground(bGround);

        Platform.runLater(() -> {
            getChildren().add(btnO);
            getChildren().add(btnX);

            getChildren().add(backBTN);
            getChildren().add(minimizeLBL);
            getChildren().add(closeLBL);
        });
    }

    void getMyChar() {
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

                    String s = gameViewModel.getPlayingChar();
                    if (s != null) {
                        if (s.equals("O")) {
                            setx();
                        } else if (s.equals("X")) {
                            seto();
                        }
                        break;
                    }

                }
            }

        }.start();

    }

    void seto() {

        player = new Image("Icons/o.png", 60, 60, true, true);
        pc = new Image("Icons/x.png", 60, 60, true, true);
        playerChar = 'O';
        pcChar = 'X';

        btnO.setOnMouseClicked(null);
        btnX.setDisable(true);

        btnO.setDisable(false);

        setDisableBtn(false);
        playFlag = false;
        buttons[0][0].fire();

    }

    void setx() {

        player = new Image("Icons/x.png", 60, 60, true, true);
        pc = new Image("Icons/o.png", 60, 60, true, true);
        playerChar = 'X';
        pcChar = 'O';
        btnO.setDisable(true);
        btnX.setDisable(false);
        btnX.setOnMouseClicked(null);

        setDisableBtn(false);
        playFlag = false;
        buttons[0][0].fire();

    }

    Thread t = new Thread() {
        @Override
        public void run() {
            while (true) {
                Move m = gameViewModel.getMove();
                String state = gameViewModel.getState();
                if (m != null) {
                    if (m.row == -1 ) {
                        ///// Show winner here
                        System.out.println(" i win :D ");
                        move = "win";
                    }
                    else if(state.equals("full")){
                        ///// Show we are evin
                        System.out.println(" WE are Evin now :) ");
                    }
                    else {
                        myBoard[m.row][m.col] = pcChar;
                        Platform.runLater(() -> {
                            buttons[m.row][m.col].setGraphic(new ImageView(pc));
                        });
                        playFlag = true;

                        if (XOModel.checkWin(myBoard)) {
                            clearBoard();
                            setDisableBtn(true);

                        } else if (!(XOModel.isEmptyBoard(myBoard))) {
                            clearBoard();
                            setDisableBtn(true);
                        }
                        t.suspend();
                    }
                    if(state.equals("win")){
                        //// Show i Lose
                        System.out.println("I lose :( ");
                    }
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
                move = "playing";
                playFlag = false;

                if (checkWin(myBoard)) {
                    move = "win";
                    clearBoard();
                    setDisableBtn(true);

                } else if (!(isEmptyBoard(myBoard))) {
                    move = "full";
                    clearBoard();
                    setDisableBtn(true);
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
