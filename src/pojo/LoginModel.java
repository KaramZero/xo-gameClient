/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pojo;


public class LoginModel {
    private String username;
    private String password;
    private String email;
   
    
     public LoginModel(){
     
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
 
    
    public String getEmail() {
        return email;
    }
 
    
    public void setUsername(String username){
        this.username = username;
    }
    
     public String getUsername(){
       return username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
   
    public String getPassword(){
       return password;
    }
    
}
