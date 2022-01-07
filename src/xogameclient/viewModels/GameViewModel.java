/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.viewModels;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import xogameclient.repository.Repo;

/**
 *
 * @author PC.M
 */
public class GameViewModel {
    private Repo repo;
    public  ObservableList<String> listUsersOnline ;
    public  String gameRequest ;
    public  String requestConfirm[];
    
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
    public void getReadStream(){
         listUsersOnline = repo.listUsersOnline;
        gameRequest = repo.gameRequest;
        requestConfirm = repo.requestConfirm;
    }
 
}
