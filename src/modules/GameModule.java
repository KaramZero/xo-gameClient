/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import repository.Repo;
import vsPcMode.levels.Move;

/**
 *
 * @author PC.M
 */
public class GameModule {
    private Repo repo;

    
    public GameModule(){
        repo = Repo.getInstance();      
    }
    
    public void sendRequestGame(String username){
          repo.sendRequestGame(username);
    }
    public void sendRequestConfirm(String username, String res) {
        repo.sendRequestConfirm(username, res);
    }
    public void sendMove(int x, int y , String move) {
        repo.sendMove(x, y,move);
    }  
    public void sendPlayingChar(String s){
    
        repo.sendPlayingChar(s);
    }    
    public ObservableList<String> getListUsersOnline() {

        ObservableList<String> listUsersOnline = Repo.listUsersOnline;
        Repo.listUsersOnline = null;
        return listUsersOnline;

    }
    public String getGameRequest() {

        String gameRequest = Repo.gameRequest;
        Repo.gameRequest = null;
        return gameRequest;
    }
    public String[] getRequestConfirm() {

        String requestConfirm[] = Repo.requestConfirm;
        Repo.requestConfirm = null;
        return requestConfirm;
    }
    public Move getMove() {

        Move move = Repo.move;
        Repo.move = null;
        return move;
    }
    public String getPlayingChar() {

        String t = Repo.playingChar;
        Repo.playingChar = null;
        return t;

    }
    
    public String getErrors(){
        
        String t = Repo.errors;
        if (Repo.errors != null) {
            repo.t.stop();
            try {
                repo.dis.close();
                repo.ps.close();
                Repo.mySocket.close();
                Repo.mySocket = null;
            } catch (IOException ex) {
                Logger.getLogger(GameModule.class.getName()).log(Level.SEVERE, null, ex);
            }
            Repo.setNull();
            Repo.errors = null;
        }
        return t;
        
    }
     public static int getScore(){
        return Repo.score;
        
    }
    
  
}
