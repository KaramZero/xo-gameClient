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
import xogameclient.localplaying.LocalPlayingrMode;

public class LocalPlayingScene extends AnchorPane {

    private Button btnX;
    private Button btnO;
    private Button backBTN;
    private Button newGameBTN;
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
            clearBoard();
            setDisableBtn(true);
        }
        
        else if (!(LocalPlayingrMode.isEmptyBoard(myBoard))) {
            labelWin.setText(" No one wins .. Play again ");
            clearBoard();
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
                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(80, 80);
                buttons[i][j].setLayoutX(310 + j * 80);
                buttons[i][j].setLayoutY(210 + i * 80);
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
    
    
    private void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setText("");
                myBoard[i][j] = '_';
            }
        }
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
        backBTN = new Button();
        newGameBTN = new Button();
        labelWin = new Label();

        setBTNs();
        setDisableBtn(true);

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
                Scene scene = new Scene(new LocalPlayingScene(s));
                s.setScene(scene);
            }
        });

        btnX.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                flag = true;
               
               
               btnO.setDisable(true);
                btnX.setDisable(true);
             
                setDisableBtn(false);
            }
        });
        btnO.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                flag = false;
              
               
                 btnO.setDisable(true);
                btnX.setDisable(true);
                
                 setDisableBtn(false);
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
