
package xogameclient.online.login;

import java.util.ArrayList;
import javafx.collections.ObservableList;

public class LoginViewModel {
    public static ObservableList<String> listUsersOnline ;
    public static String gameRequest;
    private Repo repo;
    
    public LoginViewModel(){
        repo = Repo.getInstance();
        listUsersOnline = repo.listUsersOnline;
        gameRequest = repo.gameRequest;
    }
    public  void sendLoginData(LoginData loginData){
         repo.sendLoginData(loginData);
    }
    public  String getLoginData(){
         return repo.getLoginData();
    } 
    
     public  void sendregisterData(LoginData loginData){
         repo.sendLoginData(loginData);
    }
    public  String getregisterData(){
         return repo.getLoginData();
    } 
  
    public void sendRequestGame(String username){
        repo.sendRequestGame(username);
    }
    
    
}
