package xogameclient;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
    private Label labelWin;
    private Button[][] buttons;
    private char[][] myBoard;
    private BestMove.Move pcMove;
    private char player , pc;

    EventHandler a = (EventHandler) (Event event) -> {
        Button btnTemp = ((Button) event.getSource());
        if (btnTemp.getText().equalsIgnoreCase("")) {
                btnTemp.setText(String.valueOf(player)); 
        }
        
        copyToBoard();
        if (LocalPlayingrMode.checkWin(myBoard)) {
            labelWin.setText(btnTemp.getText() + " Wins :D ");
            setBTNs();
            setDisableBtn(true);
        }       
        else if (!(LocalPlayingrMode.isEmptyBoard(myBoard))) {
            labelWin.setText(" No one wins .. Play again ");
            setBTNs();
            setDisableBtn(true);
        }
           
        else{
        pcMove =BestMove.findBestMove(myBoard,pc);
        //pcMove = NormalMove.normalMove(myBoard,pc);
        buttons[pcMove.row][pcMove.col].setText(String.valueOf(pc));
        copyToBoard();
        //myBoard[pcMove.row][pcMove.col] ='X';
        System.out.println("row "+pcMove.row+ " col "+pcMove.col);
        if (LocalPlayingrMode.checkWin(myBoard)) {
            labelWin.setText(" Pc Wins :D ");
            setBTNs();
            setDisableBtn(true);
        }
        
        else if (!(LocalPlayingrMode.isEmptyBoard(myBoard))) {
            labelWin.setText(" No one wins .. Play again ");
            setBTNs();
            setDisableBtn(true);
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
                getChildren().remove(buttons[i][j]);
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(50, 50);
                buttons[i][j].setLayoutX(180 + j * 50);
                buttons[i][j].setLayoutY(150 + i * 50);
                buttons[i][j].setMnemonicParsing(false);
                buttons[i][j].setOnAction(a);
                buttons[i][j].setText("");
                myBoard[i][j] = '_';
                getChildren().add(buttons[i][j]);
            }
        }
        btnO.setDisable(false);
        btnX.setDisable(false);
    }
    private void setDisableBtn(boolean status){
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
        labelWin = new Label();
        pcMove = new BestMove.Move();

        setBTNs();
        setDisableBtn(true);

        setId("AnchorPane");
        setPrefHeight(400);
        setPrefWidth(520);

        btnX.setLayoutX(180);
        btnX.setLayoutY(20);
        btnX.setPrefSize(50, 50);
        btnX.setText("X");

        btnO.setLayoutX(280);
        btnO.setLayoutY(20);
        btnO.setPrefSize(50, 50);
        btnO.setText("O");

        btnX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
               player ='X';
               pc = 'O';
               btnO.setDisable(true);
                btnX.setDisable(true);
                labelWin.setText("");
                setDisableBtn(false);
            }
        });
        btnO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                player ='O';
                pc = 'X';
               
                 btnO.setDisable(true);
                 btnX.setDisable(true);
                  labelWin.setText("");
                 setDisableBtn(false);
            }
        });

        labelWin.setLayoutX(230);
        labelWin.setLayoutY(80);
        labelWin.setMinHeight(16);
        labelWin.setMinWidth(69);

        getChildren().add(btnO);
        getChildren().add(btnX);

        getChildren().add(labelWin);

    }

}
