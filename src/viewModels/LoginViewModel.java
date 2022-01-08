
package viewModels;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import pojo.LoginModel;
import repository.Repo;

public class LoginViewModel {
    private Repo repo;
    
    public LoginViewModel(){
        repo = Repo.getInstance();
    }
    public  void sendLoginData(LoginModel loginData){
         repo.sendLoginData(loginData);
    }
    public  String getLoginData(){
         return repo.getLoginData();
    } 
    
     public  void sendregisterData(LoginModel loginData){
         repo.sendRegisterData(loginData);
    }
    public  String getregisterData(){
         return repo.getLoginData();
    } 
}
