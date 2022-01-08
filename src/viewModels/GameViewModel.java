/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import repository.Repo;
import vsPcMode.levels.Move;

/**
 *
 * @author PC.M
 */
public class GameViewModel {
    private Repo repo;

    
    public GameViewModel(){
        repo = Repo.getInstance();      
    }
    
     public void sendRequestGame(String username){
          repo.sendRequestGame(username);
    }
    public void sendRequestConfirm(String username, String res) {
        repo.sendRequestConfirm(username, res);
    }
    public void sendMove(int x, int y) {
        repo.sendMove(x, y);
    }
    
    public void sendPlayingChar(String s){
    
        repo.sendPlayingChar(s);
    }
    
    public ObservableList<String> getListUsersOnline() {

        ObservableList<String> listUsersOnline = Repo.listUsersOnline;
        if (Repo.listUsersOnline != null) {
            Repo.listUsersOnline = null;
        }
        return listUsersOnline;

    }

    public String getGameRequest() {

         String gameRequest = Repo.gameRequest;
        if (Repo.gameRequest != null) {
            Repo.gameRequest = null;
        }
        return gameRequest;
    }
    
    public String[] getRequestConfirm(){
    
         String requestConfirm[] = Repo.requestConfirm;
        if(Repo.requestConfirm != null) Repo.requestConfirm = null;
        return requestConfirm;
    }
    
    public Move getMove(){
    
        Move move = Repo.move;
        if(Repo.move != null) Repo.move = null;
        return move;
    }
    public String getPlayingChar() {
        
        String t = Repo.playingChar;
        if (Repo.playingChar != null) {
            Repo.playingChar = null;
        }
        return t;
        
    }
}
