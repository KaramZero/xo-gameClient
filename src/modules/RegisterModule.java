/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modules;

import pojo.LoginModel;
import pojo.RegisterModel;
import repository.Repo;

public class RegisterModule {
    private Repo repo;
    
    public RegisterModule(){
        repo = Repo.getInstance();
    }
    public  void sendregisterData(RegisterModel registerModel){
         repo.sendRegisterData(registerModel);
    }
    public  String getregisterData(){
         return repo.getLoginData();
    }
    
}
