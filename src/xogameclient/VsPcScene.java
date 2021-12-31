package xogameclient;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xogameclient.ai.BestMove;
import xogameclient.ai.NormalMove;
import xogameclient.localplaying.LocalPlayingrMode;

public class VsPcScene extends AnchorPane {

    private Button btnX;
    private Button btnO;
    private Button backBTN;
    private Button newGameBTN;
    private Label labelWin;
    private Button[][] buttons;
    private char[][] myBoard;
    private BestMove.Move pcMove;
    private char player, pc;

    boolean flag = true;

    EventHandler a = (EventHandler) (Event event) -> {

        Button btnTemp = ((Button) event.getSource());
        if (btnTemp.getText().isEmpty()) {
            if (btnTemp.getText().equalsIgnoreCase("")) {
                btnTemp.setText(String.valueOf(player));
            }

            copyToBoard();
            if (LocalPlayingrMode.checkWin(myBoard)) {
                labelWin.setText(btnTemp.getText() + " Wins :D ");
                clearBoard();
                setDisableBtn(true);
            } else if (!(LocalPlayingrMode.isEmptyBoard(myBoard))) {
                labelWin.setText(" No one wins .. Play again ");
                clearBoard();
                setDisableBtn(true);
            } else {
                if (flag) {
                    pcMove = BestMove.findBestMove(myBoard, pc);
                } else {
                    pcMove = NormalMove.normalMove(myBoard, pc);
                }
                buttons[pcMove.row][pcMove.col].setText(String.valueOf(pc));
                //copyToBoard();
                myBoard[pcMove.row][pcMove.col] = pc;
             
                if (LocalPlayingrMode.checkWin(myBoard)) {
                    labelWin.setText(" Pc Wins :D ");
                    clearBoard();
                    setDisableBtn(true);
                } else if (!(LocalPlayingrMode.isEmptyBoard(myBoard))) {
                    labelWin.setText(" No one wins .. Play again ");
                    clearBoard();
                    setDisableBtn(true);
                }

            }
        }
    };

    private void copyToBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getText().equalsIgnoreCase("")) {
                    myBoard[i][j] = '_';
                } else {
                    myBoard[i][j] = buttons[i][j].getText().charAt(0);
                }
            }
        }
    }

    private void setBTNs() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(80, 80);
                buttons[i][j].setLayoutX(310 + j * 80);
                buttons[i][j].setLayoutY(210 + i * 80);
                buttons[i][j].setMnemonicParsing(false);
                buttons[i][j].setOnAction(a);
                getChildren().add(buttons[i][j]);
            }
        }

    }

    private void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                myBoard[i][j] = '_';
            }
        }
        btnO.setDisable(false);
        btnX.setDisable(false);
    }

    private void setDisableBtn(boolean status) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setDisable(status);
            }
        }
    }

    public VsPcScene(Stage s) {

        buttons = new Button[3][3];
        myBoard = new char[3][3];
        btnO = new Button();
        btnX = new Button();
        backBTN = new Button();
        newGameBTN = new Button();
        labelWin = new Label();
        pcMove = new BestMove.Move();

        Button hard = new Button();
        hard.setLayoutX(310);
        hard.setLayoutY(200);
        hard.setPrefSize(80, 80);
        hard.setText("Hard");
       
        Button normal = new Button();
        normal.setLayoutX(470);
        normal.setLayoutY(200);
        normal.setPrefSize(80, 80);
        normal.setText("Normal");
       

        getChildren().add(hard);
        getChildren().add(normal);
        
         normal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                setBTNs();
                clearBoard();
                setDisableBtn(true);
                flag = false;
                btnO.setDisable(false);
                btnX.setDisable(false);
                getChildren().remove(hard);
                getChildren().remove(normal);

            }
        });
         
          hard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                setBTNs();
                clearBoard();
                setDisableBtn(true);
                flag = true;
                btnO.setDisable(false);
                btnX.setDisable(false);

                getChildren().remove(hard);
                getChildren().remove(normal);
            }
        });


        setId("AnchorPane");
        setPrefHeight(650);
        setPrefWidth(850);

        btnX.setLayoutX(310);
        btnX.setLayoutY(50);
        btnX.setPrefSize(80, 80);
        btnX.setText("X");

        btnO.setLayoutX(470);
        btnO.setLayoutY(50);
        btnO.setPrefSize(80, 80);
        btnO.setText("O");

        btnX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player = 'X';
                pc = 'O';
                btnO.setDisable(true);
                btnX.setDisable(true);

                setDisableBtn(false);
            }
        });
        btnO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player = 'O';
                pc = 'X';

                btnO.setDisable(true);
                btnX.setDisable(true);

                setDisableBtn(false);
            }
        });

        btnO.setDisable(true);
        btnX.setDisable(true);

        backBTN.setLayoutX(650);
        backBTN.setLayoutY(20);
        backBTN.setPrefSize(100, 30);
        backBTN.setText("Back");
        backBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene scene = new Scene(new Home(s));
                s.setScene(scene);
            }
        });

        newGameBTN.setLayoutX(130);
        newGameBTN.setLayoutY(20);
        newGameBTN.setPrefSize(100, 30);
        newGameBTN.setText("New Game");
        newGameBTN.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                Scene scene = new Scene(new VsPcScene(s));
                s.setScene(scene);
            }
        });

        labelWin.setLayoutX(310);
        labelWin.setLayoutY(150);
        labelWin.setMinHeight(50);
        labelWin.setMinWidth(240);
        labelWin.setAlignment(Pos.CENTER);
        labelWin.setText("Who will win?");

        getChildren().add(btnO);
        getChildren().add(btnX);

        getChildren().add(newGameBTN);
        getChildren().add(backBTN);

        getChildren().add(labelWin);

    }

}
