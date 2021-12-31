package xogameclient;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import xogameclient.localplaying.LocalPlayingrMode;

public class LocalPlayingScene extends AnchorPane {

    private Button btnX;
    private Button btnO;
    private Label labelWin;
    private Button[][] buttons;
    private char[][] myBoard;
    boolean flag = true;

    EventHandler a = (EventHandler) (Event event) -> {
        if (flag && ((Button) event.getSource()).getText().equalsIgnoreCase("")) {
            ((Button) event.getSource()).setText("X");
            flag = false;
        } else if (!flag && ((Button) event.getSource()).getText().equalsIgnoreCase("")) {
            ((Button) event.getSource()).setText("O");
            flag = true;
        }
        if(flag){
           labelWin.setText("X turn");
        }
        else{
           labelWin.setText("O turn");
        }
        copyToBoard();

        if (LocalPlayingrMode.checkWin(myBoard)) {
            labelWin.setText(((Button) event.getSource()).getText() + " Wins :D ");
            setBTNs();
            setDisableBtn(true);
        }
        
        else if (!(LocalPlayingrMode.isEmptyBoard(myBoard))) {
            labelWin.setText(" No one wins .. Play again ");
            setBTNs();
            setDisableBtn(true);

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
    public LocalPlayingScene(Stage s) {

        buttons = new Button[3][3];
        myBoard = new char[3][3];
        btnO = new Button();
        btnX = new Button();
        labelWin = new Label();

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
                flag = true;
               
               
               btnO.setDisable(true);
                btnX.setDisable(true);
                labelWin.setText("");
                setDisableBtn(false);
            }
        });
        btnO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                flag = false;
              
               
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
