package xo;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class XOBoard {
public static Button[][] buttons = new Button[3][3];
public static char[][] myBoard  = new char[3][3];
public static Label backBTN;
public static Label btnX;
public static  Label btnO;
public static  Label boardLBL;

  static{
      btnO = new Label();
      btnX = new Label();
      boardLBL = new Label();
      
        boardLBL.setLayoutX(310);
        boardLBL.setLayoutY(210);
        boardLBL.setPrefSize(270, 270);
        boardLBL.setGraphic(new ImageView(new Image("Icons/board.png", 270, 270, true, true)));
      
       btnX.setLayoutX(310);
        btnX.setLayoutY(80);
        btnX.setPrefSize(80, 80);
        btnX.setGraphic(new ImageView(new Image("Icons/x.png", 60, 60, true, true)));

        btnO.setLayoutX(470);
        btnO.setLayoutY(80);
        btnO.setPrefSize(80, 80);
        btnO.setGraphic(new ImageView(new Image("Icons/o.png", 60, 60, true, true)));

      backBTN = new Label();
      backBTN.setLayoutX(650);
      backBTN.setLayoutY(20);
      backBTN.setPrefSize(100, 30);
      backBTN.setDisable(true);
      backBTN.setGraphic(new ImageView(new Image("Icons/Home.png", 80, 80, true, true)));
}
    
  private XOBoard(){ 
      
  }
  public static  void setBTNs() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {

                buttons[i][j] = new Button();
                buttons[i][j].setPrefSize(80, 80);
                buttons[i][j].setLayoutX(310 + j * 90);
                buttons[i][j].setLayoutY(210 + i * 90);
                buttons[i][j].setMnemonicParsing(false);
                buttons[i][j].setBackground(null);
                myBoard[i][j] = '_';
            }}

    }
  public static void setDisableBtn(boolean status) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setDisable(status);
            }
        }
        backBTN.setDisable(status); 
    } 
  public static void clearBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setBackground(null);
                myBoard[i][j] = '_';
            }
        }
  }
}
