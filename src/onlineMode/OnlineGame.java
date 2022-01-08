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
    private Label newGameBTN;
    private Label boardLBL;
    private GameViewModel gameViewModel;
    boolean flag = true;
    boolean playFlag = true;

    Image player = new Image("Icons/x.png", 60, 60, true, true);
    ImageView x = new ImageView(player);
    char playerChar;

    Image pc = new Image("Icons/o.png", 60, 60, true, true);
    ImageView o = new ImageView(pc);
    char pcChar; 

    public OnlineGame(Stage s , boolean myTurn) {

        btnO = new Label();
        btnX = new Label();
        backBTN = new Label();
        newGameBTN = new Label();
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
        setButtons();
        clearBoard();
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
        
        
        if(!myTurn){getMyChar();}

        backBTN.setLayoutX(650);
        backBTN.setLayoutY(20);
        backBTN.setPrefSize(100, 30);
        backBTN.setGraphic(new ImageView(new Image("Icons/Home.png", 80, 80, true, true)));
        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
            
                s.setScene(Home.onlineScene);
            }
        });

        newGameBTN.setLayoutX(130);
        newGameBTN.setLayoutY(20);
        newGameBTN.setPrefSize(100, 30);

        newGameBTN.setGraphic(new ImageView(new Image("Icons/reNew.png", 80, 80, true, true)));
        newGameBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Scene scene = new Scene(new VsPcScene(s));
                s.setScene(scene);
            }
        });

        setBackground(bGround);

        Platform.runLater(() -> {
            getChildren().add(btnO);
            getChildren().add(btnX);
            getChildren().add(newGameBTN);
            getChildren().add(backBTN);
            getChildren().add(minimizeLBL);
            getChildren().add(closeLBL);
        });
    }
    
    void getMyChar() {
        btnO.setDisable(true);
        btnX.setDisable(true);
        
        new Thread(){
            @Override
            public void run() {
                
                while(true){
                    try {
                        sleep(500);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(OnlineGame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    
                    String s = gameViewModel.getPlayingChar();
                    if(s != null){
                        if(s.equals("O")) setx();
                        else if (s.equals("X")) seto();
                        break;
                    }
                
                }
            }
        
        }.start();

    }
    
    void seto(){
    
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
    void setx(){
    
          player = new Image("Icons/x.png", 60, 60, true, true);
                pc = new Image("Icons/o.png", 60, 60, true, true);
                playerChar = 'X';
                pcChar = 'O';
                btnO.setDisable(true);
                btnX.setDisable(false);
                btnX.setOnMouseClicked(null);

                setDisableBtn(false);
    
    }
    
     Thread t = new Thread(){
            @Override
            public void run() {
                while (true) {
                    Move m = gameViewModel.getMove();
                    if (m != null) {
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
                gameViewModel.sendMove(xCord, yCord);
                playFlag = false;

                if (checkWin(myBoard)) {
                    clearBoard();
                    setDisableBtn(true);
                } else if (!(isEmptyBoard(myBoard))) {
                    clearBoard();
                    setDisableBtn(true);
                }              
            }
        }
        t.resume();
    };
      private void setButtons(){
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
