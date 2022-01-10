/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vsPcMode.levels;

import vsPcMode.levels.Move;

public class NormalLevel implements Level {

    public NormalLevel() {
    }

    
    
     public Move findMove(char[][] board,char c){
         
         // Check Winning 
       Move temp = checkWinning(board, c);
        char t;
         if(c == 'X') t='O';
           else t = 'X';
        
         // Check Blocking
        if(temp.row == -1 ) temp = checkWinning(board, t);
        
        
        if(temp.row == -1){
            for(int i = 0 ; i < 3 &&temp.row ==-1 ; i++){
                for(int j = 0; j < 3&&temp.row ==-1 ; j++){
                    if(board[i][j] == '_'){
                    temp.row = i;
                    temp.col = j;
                    }
                }
            }
        }        
        return temp;
    }
    
    private Move checkWinning(char[][] board,char c){
      Move temp = new Move();
            //   Check for winning Rows
        for(int i = 0 ; i < 3 ; i++)
           {
                if(board[i][0] == c && board[i][0] == board[i][1] && board[i][2] == '_') {
                    temp.row = i;
                    temp.col = 2;
                    break;
                }else if(board[i][1] == c && board[i][1] == board[i][2] && board[i][0] == '_') {
                    temp.row = i;
                    temp.col = 0;
                    break;
                }else if(board[i][2] == c && board[i][2] == board[i][0] && board[i][1] == '_') {
                    temp.row = i;
                    temp.col = 1;
                    break;
                }            
        }
        //    Check for winning Columns 
         for(int i = 0 ; i < 3 ; i++)
           {
                if(board[0][i] == c && board[0][i] == board[1][i] && board[2][i] == '_') {
                    temp.row = 2;
                    temp.col = i;
                    break;
                }else if(board[1][i] == c && board[1][i] == board[2][i] && board[0][i] == '_') {
                    temp.row = 0;
                    temp.col = i;
                    break;
                }else if(board[2][i] == c && board[2][i] == board[0][i] && board[1][i] == '_') {
                    temp.row = 1;
                    temp.col = i;
                    break;
                }            
        }
         //   Chwck for winning X 
         
         if(board[0][0] == c && board[0][0] == board[1][1] && board[2][2] == '_'){
             temp.row = 2;
             temp.col = 2;
         }else if(board[1][1] == c && board[1][1] == board[2][2] && board[0][0] == '_'){
             temp.row = 0;
             temp.col = 0;
         }else if(board[2][2] == c && board[2][2] == board[0][0] && board[1][1] == '_'){
             temp.row = 1;
             temp.col = 1;
         }else if(board[0][2] == c && board[0][2] == board[1][1] && board[2][0] == '_'){
             temp.row = 2;
             temp.col = 0;
         }else if(board[1][1] == c && board[1][1] == board[2][0] && board[0][2] == '_'){
             temp.row = 0;
             temp.col = 2;
         }else if(board[2][0] == c && board[2][0] == board[0][2] && board[1][1] == '_'){
             temp.row = 1;
             temp.col = 1;
         }
         
        return temp;    
    }

}

