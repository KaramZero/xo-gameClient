
package xogameclient.online.login;

import java.util.ArrayList;
import javafx.collections.ObservableList;

public class LoginViewModel {
    private Repo repo;
    
    public LoginViewModel(){
        repo = Repo.getInstance();
    }
    public  void sendLoginData(LoginData loginData){
         repo.sendLoginData(loginData);
    }
    public  String getLoginData(){
         return repo.getLoginData();
    }  
    public ObservableList<String> getListUserOnline(){
         return repo.getListUserOnline();
    }
    public String sendRequestGame(String username){
        return repo.sendRequestGame(username);
    }
}
