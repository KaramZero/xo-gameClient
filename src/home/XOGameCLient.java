/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import home.Home;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class XOGameCLient extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new Home(stage);
        Scene scene = new Scene(root);
        
        stage.setScene(scene);
        stage.setResizable(false);
        
       // stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }


    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
