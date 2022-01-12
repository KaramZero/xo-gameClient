
package modules;

import pojo.LoginModel;
import repository.Repo;

public class LoginModule {
    private Repo repo;
    
    public LoginModule(){
        repo = Repo.getInstance();
    }
    public  void sendLoginData(LoginModel loginData){
         repo.sendLoginData(loginData);
    }
    public  String getLoginData(){
         return repo.getLoginData();
    } 
    
      
    public String getErrors(){
        
        String t = Repo.errors;
        Repo.errors = null;
        return t;
        
    }
    
}
