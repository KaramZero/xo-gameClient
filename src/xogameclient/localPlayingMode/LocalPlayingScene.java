package xogameclient.localPlayingMode;

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
import javafx.scene.layout.Border;
import javafx.stage.Stage;
import static xogameclient.home.Home.bGround;
import static xogameclient.home.Home.closeLBL;
import static xogameclient.home.Home.minimizeLBL;
import xogameclient.pojo.XOModel;
import javafx.scene.layout.Background;
import xogameclient.home.Home;


public class LocalPlayingScene extends AnchorPane {

    private Label btnX;
    private Label btnO;
    private Label backBTN;
    private Label newGameBTN;
    private Label labelWin;
    private Label boardLBL;
    private Button[][] buttons;
    private char[][] myBoard;
    boolean flag = true;
    
    Image xIMG = new Image("Icons/x.png",60,60,true,true);
    ImageView x = new ImageView(xIMG);

    Image oIMG =new Image("Icons/o.png",60,60,true,true);
    ImageView o = new ImageView(oIMG);

        
    EventHandler a = (EventHandler) (Event event) -> {
        Button t = ((Button) event.getSource());
        
        int xCord=0, yCord=0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == t) {
                    xCord = i;
                    yCord = j;
                }
            }
        }
        
        if (flag && myBoard[xCord][yCord] == '_') {
            t.setGraphic(new ImageView(xIMG));
            myBoard[xCord][yCord] = 'X';
            flag = false;
        } else if (!flag && myBoard[xCord][yCord] == '_') {
            t.setGraphic(new ImageView(oIMG));
             myBoard[xCord][yCord] = 'O';
            flag = true;
        }
        if(flag){
           labelWin.setGraphic(new ImageView(xIMG));
        }
        else{
           labelWin.setGraphic(new ImageView(oIMG));
        }
       // copyToBoard();

        if (XOModel.checkWin(myBoard)) {
            labelWin.setGraphic(null);
            clearBoard();
            setDisableBtn(true);
        }
        
        else if (!(XOModel.isEmptyBoard(myBoard))) {
            //labelWin.setText(" No one wins .. Play again ");
            clearBoard();
            setDisableBtn(true);

        }
    };

    private void copyToBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j].getGraphic() == null) {
                    myBoard[i][j] = '_';
                } else if (buttons[i][j].getGraphic() == x) {
                    
                    myBoard[i][j] = 'X';
                } else if (buttons[i][j].getGraphic() == o) {
                    
                    myBoard[i][j] = 'O';
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
                buttons[i][j].setOnAction(a);
                buttons[i][j].setBackground(null);
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
                buttons[i][j].setBackground(null);
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
        btnO = new Label();
        btnX = new Label();
        boardLBL = new Label();
        backBTN = new Label();
        newGameBTN = new Label();
        labelWin = new Label();

         
        boardLBL.setLayoutX(310);
        boardLBL.setLayoutY(210);
        boardLBL.setPrefSize(270, 270);
        boardLBL.setGraphic(new ImageView(new Image("Icons/board.png",270,270,true,true)));
        
       getChildren().add(boardLBL);
        

        
        setBTNs();
        setDisableBtn(true);

        setId("AnchorPane");
        setPrefHeight(650);
        setPrefWidth(850);
        
       
        btnX.setLayoutX(350);
        btnX.setLayoutY(100);
        btnX.setPrefSize(80, 80);
        
        btnX.setGraphic(x);
       // btnX.setText("X");

        btnO.setLayoutX(470);
        btnO.setLayoutY(100);
        btnO.setPrefSize(80, 80);
        btnO.setGraphic(o);

       // btnO.setText("O");
        
        backBTN.setLayoutX(650);
        backBTN.setLayoutY(60);
        backBTN.setPrefSize(80, 80);
        backBTN.setGraphic(new ImageView(new Image("Icons/Home.png",80,80,true,true)));

        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                Scene scene = new Scene(new Home(s));
                s.setScene(scene);
            }
        });
        
        newGameBTN.setLayoutX(130);
        newGameBTN.setLayoutY(60);
        newGameBTN.setPrefSize(80, 80);
        newGameBTN.setGraphic(new ImageView(new Image("Icons/reNew.png",80,80,true,true)));

        newGameBTN.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                Scene scene = new Scene(new LocalPlayingScene(s));
                s.setScene(scene);
            }
        });

        btnX.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                  flag = true;
               
               
               btnO.setDisable(true);
                btnX.setDisable(true);
             
                setDisableBtn(false);
            }
        });
        btnO.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                   flag = false;
              
               
                 btnO.setDisable(true);
                btnX.setDisable(true);
                
                 setDisableBtn(false);
            }
        });

        labelWin.setLayoutX(310);
        labelWin.setLayoutY(10);
        labelWin.setMinHeight(50);
        labelWin.setMinWidth(270);
        labelWin.setAlignment(Pos.CENTER);
        

        setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);
        getChildren().add(btnO);
        getChildren().add(btnX);

        getChildren().add(newGameBTN);
        getChildren().add(backBTN);
               
        getChildren().add(labelWin);

    }

}
