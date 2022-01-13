
package modules;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
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
      if (Repo.errors != null) {
            repo.t.stop();
             try {
                repo.dis.close();
                repo.ps.close();
                Repo.mySocket.close();
                Repo.mySocket = null;
            } catch (IOException ex) {
                Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
            }
            Repo.setNull();
            Repo.errors = null;
        }
        return t;
        
    }
    
    public void resetRepo(){
        repo.t.stop();
             try {
                repo.dis.close();
                repo.ps.close();
                Repo.mySocket.close();
                Repo.mySocket = null;
            } catch (IOException ex) {
                Logger.getLogger(LoginModule.class.getName()).log(Level.SEVERE, null, ex);
            }
            Repo.setNull();
            Repo.errors = null;
    
    }
    
    
}
