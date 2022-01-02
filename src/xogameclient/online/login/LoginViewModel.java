
package xogameclient.online.login;

import java.util.ArrayList;

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
    public  ArrayList<String> getListUserOnline(){
         return repo.getListUserOnline();
    } 
}
