/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.online.login;


public class LoginData {
    private String username;
    private String password;
    private static int idToken;
    
    public LoginData(){
        idToken =0;
    }
    
    public void setUsername(String username){
        this.username = username;
    }
    
    public void setPassword(String password){
        this.password = password;
    }
    
    public String getUsername(){
       return username;
    }
    
    public String getPassword(){
       return password;
    }
    
}
