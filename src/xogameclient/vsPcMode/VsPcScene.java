package xogameclient.vsPcMode;

import xogameclient.vsPcMode.levels.Move;
import xogameclient.vsPcMode.levels.HardLevel;
import xogameclient.vsPcMode.levels.EasyLevel;
import xogameclient.home.Home;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static xogameclient.home.Home.bGround;
import static xogameclient.home.Home.closeLBL;
import static xogameclient.home.Home.minimizeLBL;
import xogameclient.pojo.XOModel;

public class VsPcScene extends AnchorPane {
 private Label btnX;
    private Label btnO;
    private Label backBTN;
    private Label newGameBTN;
    private Label boardLBL;
    private Button[][] buttons;
    private char[][] myBoard;
    private Move pcMove;
    boolean flag = true;
    
    Image player = new Image("Icons/x.png",60,60,true,true);
    ImageView x = new ImageView(player);
    char playerChar;

    Image pc =new Image("Icons/o.png",60,60,true,true);
    ImageView o = new ImageView(pc);
    char pcChar;


    EventHandler a = (EventHandler) (Event event) -> {

        Button btnTemp = ((Button) event.getSource());
        
         int xCord=0, yCord=0;
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

           
            if (XOModel.checkWin(myBoard)) {               
                clearBoard();
                setDisableBtn(true);
            } else if (!(XOModel.isEmptyBoard(myBoard))) {
                clearBoard();
                setDisableBtn(true);
            } else {
                if (flag) {
                    pcMove = HardLevel.findBestMove(myBoard, pcChar);
                } else {
                    pcMove = EasyLevel.findEasyMove(myBoard, pcChar);
                }
                buttons[pcMove.row][pcMove.col].setGraphic(new ImageView(pc));
                 myBoard[pcMove.row][pcMove.col] = pcChar;
                //copyToBoard();
               // myBoard[pcMove.row][pcMove.col] = pc;
             
                if (XOModel.checkWin(myBoard)) {
                  //  labelWin.setText(" Pc Wins :D ");
                    clearBoard();
                    setDisableBtn(true);
                } else if (!(XOModel.isEmptyBoard(myBoard))) {
                    //labelWin.setText(" No one wins .. Play again ");
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
                buttons[i][j].setLayoutX(310 + j * 90);
                buttons[i][j].setLayoutY(210 + i * 90);
                buttons[i][j].setMnemonicParsing(false);
                buttons[i][j].setBackground(null);
                buttons[i][j].setOnAction(a);
                getChildren().add(buttons[i][j]);
                myBoard[i][j] = '_';
            }
        }

    }

    private void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
               buttons[i][j].setBackground(null);
               
                myBoard[i][j] = '_';
            }
        }
      //  btnO.setDisable(false);
       // btnX.setDisable(false);
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
        btnO = new Label();
        btnX = new Label();
        backBTN = new Label();
        newGameBTN = new Label();
        boardLBL = new Label();
        pcMove = new Move();
            
        boardLBL.setLayoutX(310);
        boardLBL.setLayoutY(210);
        boardLBL.setPrefSize(270, 270);
        boardLBL.setGraphic(new ImageView(new Image("Icons/board.png",270,270,true,true)));
        
       getChildren().add(boardLBL);
        


        Button hard = new Button();
        hard.setLayoutX(310);
        hard.setLayoutY(200);
        hard.setPrefSize(80, 80);
        hard.setText("Hard");
       
        Button normal = new Button();
        normal.setLayoutX(500);
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
        btnX.setLayoutY(80);
        btnX.setPrefSize(80, 80);
        btnX.setGraphic(new ImageView(new Image("Icons/x.png",60,60,true,true)));

        btnO.setLayoutX(470);
        btnO.setLayoutY(80);
        btnO.setPrefSize(80, 80);
        btnO.setGraphic(new ImageView(new Image("Icons/o.png",60,60,true,true)));

        btnX.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                player = new Image("Icons/x.png",60,60,true,true);
                pc =  new Image("Icons/o.png",60,60,true,true);
                playerChar = 'X';
                pcChar = 'O';
                btnO.setDisable(true);
                btnX.setDisable(true);

                setDisableBtn(false);
            }
        });
        btnO.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                player = new Image("Icons/o.png",60,60,true,true);
                pc =  new Image("Icons/x.png",60,60,true,true);
                playerChar = 'O';
                pcChar = 'X';

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
        backBTN.setGraphic(new ImageView(new Image("Icons/Home.png",80,80,true,true)));
        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                Scene scene = new Scene(new Home(s));
                s.setScene(scene);
            }
        });

        newGameBTN.setLayoutX(130);
        newGameBTN.setLayoutY(20);
        newGameBTN.setPrefSize(100, 30);
      
        newGameBTN.setGraphic(new ImageView(new Image("Icons/reNew.png",80,80,true,true)));
        newGameBTN.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                Scene scene = new Scene(new VsPcScene(s));
                s.setScene(scene);
            }
        });

      
        setBackground(bGround);


        getChildren().add(btnO);
        getChildren().add(btnX);

        getChildren().add(newGameBTN);
        getChildren().add(backBTN);

        
         getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);

    }

}
