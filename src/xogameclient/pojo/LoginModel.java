/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.pojo;


public class LoginModel {
    private String username;
    private String password;
    private String email;
    private String name;
    private int score;
    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }
    private static int idToken;
    
    public LoginModel(){
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
