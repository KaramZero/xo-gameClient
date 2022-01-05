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
import xogameclient.ai.Move;

public class Repo extends Thread {

    private static Repo repo;
    private Socket mySocket;
    private DataInputStream dis;
    private PrintStream ps;
    private JSONObject json;
    private String str;
    public static ObservableList<String> listUsersOnline;
    public static String gameRequest;
    public Move move;
    Thread t;

    private Repo() {
        try {
            mySocket = new Socket("127.0.0.1", 7001);
            ps = new PrintStream(mySocket.getOutputStream());
            dis = new DataInputStream(mySocket.getInputStream());
            str = null;
            json = new JSONObject();
            listUsersOnline = null;
            gameRequest = null;

            t = new Thread() {
                @Override
                public void run() {
                    while (true) {
                        readStream();
                    }
                }
            };

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

    public void sendLoginData(LoginData loginData) {
        try {
            json.put("username", loginData.getUsername());
            json.put("password", loginData.getPassword());
            ps.println(json.toString());
        } catch (JSONException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getLoginData() {
        try {
            str = dis.readLine();
        } catch (IOException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (str.equals("true")) {
            t.start();
        }
        return str;
    }

    public void readStream() {
        try {

            str = dis.readLine();

            JSONObject js = new JSONObject(str);

            if (js.get("header").equals("usersList")) {
                listUsersOnline = FXCollections.observableArrayList();
                String s = (String) js.get("list");
                if (s.length() > 0) {
                    String[] arr = s.split("\\*");
                    for (int i = 0; i < arr.length; i++) {
                        listUsersOnline.add(arr[i]);
                    }
                }
            }
            else if (js.get("header").equals("request")) {
                gameRequest =new String();
                gameRequest = (String) js.get("username");
            }
            else if (js.get("header").equals("playing")) {
                move = new Move();
                move.row = (int) js.get("row");
                move.col = (int) js.get("col");
            }

        } catch (IOException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JSONException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void sendRequestGame(String username) {
         JSONObject js = new JSONObject();
        try {
           js.put("header","request");
            js.put("username",username);
            ps.println(js.toString());
            System.out.println("request sent from repo to " + username);
        
        }catch (JSONException ex) {
            Logger.getLogger(Repo.class.getName()).log(Level.SEVERE, null, ex);
        }
      
    }
}
