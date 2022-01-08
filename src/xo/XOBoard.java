/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xo;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class XOBoard {
public static Button[][] buttons = new Button[3][3];
public static char[][] myBoard  = new char[3][3];
    
  private XOBoard(){}
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
