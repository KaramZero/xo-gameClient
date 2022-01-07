/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package xogameclient.repository;

import xogameclient.pojo.LoginModel;
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
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import xogameclient.vsPcMode.levels.Move;

public class Repo extends Thread {

    private static Repo repo;
    private Socket mySocket;
    private DataInputStream dis;
    private PrintStream ps;
    private JSONObject json;
    private String response;
    public static String requestConfirm[];
    public static ObservableList<String> listUsersOnline;
    public static String gameRequest;
    public static Move move;
    public static String IpAddress;

    private Repo() {
        try {
            System.out.println("ip"+IpAddress);
            mySocket = new Socket(IpAddress, 7001);
            ps = new PrintStream(mySocket.getOutputStream());
            dis = new DataInputStream(mySocket.getInputStream());
            response = null;
            json = new JSONObject();
            listUsersOnline = null;
            gameRequest = null;
            requestConfirm = null;
            move = null;

        } catch (IOException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Repo getInstance() {
        if (repo == null) {
            repo = new Repo();
        }
        return repo;
    }
    public void sendLoginData(LoginModel loginData) {
        try {
            json.put("header", "login");
            json.put("username", loginData.getUsername());
            json.put("password", loginData.getPassword());
            ps.println(json.toString());
        } catch (JSONException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public String getLoginData() {
        try {
            response = dis.readLine();
            if (response.equals("true")) {
                t.start();
            }
        } catch (IOException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
        return response;
    }
     public void sendRegisterData(LoginModel loginData) {
        try {
            json.put("header", "register");
            json.put("username", loginData.getUsername());
            json.put("password", loginData.getPassword());
            json.put("name", loginData.getName());
            json.put("email", loginData.getEmail());
            ps.println(json.toString());
        } catch (JSONException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   /*  public String getRegisterData() {
        try {
            str = dis.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (str.equals("true")) {
            t.start();
        }
        return str;
    }*/

    public void readStream() {
        try {
            response = dis.readLine();
             json = new JSONObject(response);
             if(json.get("header").equals("getOnlineUsers")){
                listUsersOnline = FXCollections.observableArrayList();
                JSONArray jsonArray = json.getJSONArray("listUsersOnline");
                for (int i = 0; i <jsonArray.length(); i++) {
                    listUsersOnline.add(jsonArray.getString(i));
                }
                 System.out.println("size"+listUsersOnline.size());
             }
            else if (json.get("header").equals("request")) {
                gameRequest =new String();
                gameRequest = (String) json.get("username");
            }
            else if (json.get("header").equals("requestConfirm")) {
                requestConfirm =new String[2];
               requestConfirm[0] = (String) json.get("username");
               requestConfirm[1] = (String) json.get("res");
            }
            else if (json.get("header").equals("move")) {
                move = new Move();
                move.row = (int) json.get("row");
                move.col = (int) json.get("col");
            }

        } catch (IOException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void sendRequestGame(String username) {
        json = new JSONObject();
        try {
            json.put("header","request");
            json.put("username",username);
            ps.println(json.toString());
            System.out.println("request sent from repo to " + username);
        
        }catch (JSONException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    public void sendRequestConfirm(String username,String res) {
        json = new JSONObject();
        try {
           json.put("header","requestConfirm");
            json.put("username",username);
            json.put("res", res);
            ps.println(json.toString());
           
        }catch (JSONException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
    public void sendMove(int x,int y){
      json = new JSONObject();
        try {
           json.put("header","move");
            json.put("row",x);
            json.put("col", y);
            ps.println(json.toString());
           
        }catch (JSONException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
     Thread t = new Thread(){
        @Override
         public void run() {
             while (true) {
                 readStream();
            }
        }
    };         
}
