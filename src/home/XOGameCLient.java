/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package home;

import home.Home;
import java.io.File;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

public class XOGameCLient extends Application {
    
    
    private Media sound;
    public static MediaPlayer mediaPlayer ;

    
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = new Home(stage);
        Scene scene = new Scene(root);
        
        sound = new Media(new File("sound.mp3").toURI().toString());
        mediaPlayer = new MediaPlayer(sound);       
        mediaPlayer.setAutoPlay(false);
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.seek(Duration.ZERO);
        });
       
        
        stage.setScene(scene);
        stage.setResizable(false);
        
        stage.initStyle(StageStyle.UNDECORATED);
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
