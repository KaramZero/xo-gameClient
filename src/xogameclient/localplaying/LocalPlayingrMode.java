/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.localplaying;

import javafx.scene.control.Button;

public class LocalPlayingrMode {
    
    
    //check any player win 
    public static boolean checkWin(char [][] myBoard){
        boolean result = false;
        for(int i =0; i< 3 ; i++){
               // check rows
                if((myBoard[i][0] != '_')&& (myBoard[i][0] == myBoard[i][1]) && (myBoard[i][0]== myBoard[i][2])){
                    result = true; 
                    break;
                }
                // check col
                 if((myBoard[0][i] != '_')&& (myBoard[0][i] == myBoard[1][i]) && (myBoard[0][i]==myBoard[2][i])){
                    result = true;
                    break;
                }     
        }
        // check x
        if((myBoard[0][0]!='_') &&(myBoard[0][0] == myBoard[1][1]) && (myBoard[0][0]==myBoard[2][2]))
            result = true;
        
           if((myBoard[0][2]!='_') &&(myBoard[0][2] == myBoard[1][1]) && (myBoard[0][2]==myBoard[2][0]))
            result = true;
        
        return result;
    }
    public static boolean isEmptyBoard(char [][] board){
        boolean result = false;
        
        for(int i =0 ; i< 3 && !result ; i++){
            for(int j =0; j <3;j++){
                 if(board[i][j]=='_'){
                     result = true;
                 }
            }
        }
       

        return result;
    }
    
}
