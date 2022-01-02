/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.online.login;

import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONException;
import org.json.JSONObject;


public class Repo extends Thread{
    private static Repo repo;
    private Socket mySocket;
    private DataInputStream dis ;
    private PrintStream ps;
    private JSONObject json;
    private  String  str;
    private ObservableList<String> listUsersOnline;

  
    private Repo(){
        try {
            mySocket = new Socket("127.0.0.1", 7001);
            ps = new PrintStream(mySocket.getOutputStream());
            dis = new DataInputStream(mySocket.getInputStream());
            str  = null;
             listUsersOnline= FXCollections.observableArrayList();

            json = new JSONObject();
        
        }catch (IOException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static Repo getInstance(){
        if(repo==null){
            repo = new Repo();
        }
        return repo;
    }
    public void sendLoginData(LoginData loginData){
        try {
            json.put("username",loginData.getUsername());
            json.put("password", loginData.getPassword());
            //json = new JSONObject(loginData);
            ps.println(json.toString());
        } catch (JSONException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getLoginData(){
        try {
            str = dis.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
            System.out.println(str);
         return str;
    }
    public ObservableList<String> getListUserOnline(){
                 try {
                  while(true){
                ps.println("getusers");
               str = dis.readLine();
            if(str.length()!=0){
            String[] arr = str.split("\\*");
                             for (int i = 0; i < arr.length; i++) {
                                 listUsersOnline.add(arr[i]);
                                 System.out.println("hhhhhh"+arr[i]);
                             }
                           break;
            }
            }
        } catch (IOException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
       System.out.println("size"+listUsersOnline.size());

      return listUsersOnline;                        
    }
}
