package Video;



import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.media.MediaView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;
import localPlayingMode.LocalPlayingScene;
import onlineMode.OnlineGameScene;
import vsPcMode.VsPcScene;


public class VideoModel extends AnchorPane implements Initializable {
        private Stage myStage;
        private MediaView mediaView;
        File file;
        Media media;
        MediaPlayer mediaPlayer;
        AnchorPane pane;
        Button skipBtn;
        int pageFlag;
        

   public VideoModel(Stage stage , String status, int flag){
       pageFlag=flag;
       myStage=stage;
       skipBtn= new Button();
       skipBtn.setLayoutX(stage.getWidth()-100);
       skipBtn.setLayoutY(20.0);
       skipBtn.setText("Skip ->");
       skipBtn.setBackground(null);
       skipBtn.setTextFill(Color.WHITE);
       skipBtn.setPrefSize(100, 30);
       
       mediaView=new MediaView();
       mediaView.setLayoutX(0);
       mediaView.setLayoutY(0);
       
       mediaView.setFitHeight(540.0);
       mediaView.setFitWidth(960.0);
      
      
       setMaxHeight(USE_PREF_SIZE);
       setMaxWidth(USE_PREF_SIZE);
       setMinHeight(USE_PREF_SIZE);
       setMinWidth(USE_PREF_SIZE);
       setPrefHeight(540.0);
       setPrefWidth(960.0);
       if (status.equals("Win")) {
           file = new File("win.mp4");
       }
       else if (status.equals("Lose")){
          file = new File("lose.mp4"); 
       }
       //file= new File("win.mp4");
        media=new Media(file.toURI().toString());
        //System.out.println(file.toURI().toString());
        mediaPlayer= new MediaPlayer(media);
        mediaView.setMediaPlayer(mediaPlayer);
        
       mediaPlayer.setOnEndOfMedia(() -> {
           mediaPlayer.seek(Duration.ZERO);
       });
       mediaPlayer.play();
     
      
      skipBtn.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                mediaPlayer.stop();
                if (pageFlag == 1) {
                    Scene scene = new Scene(new LocalPlayingScene(myStage));
                    myStage.setScene(scene);
                }
                else if(pageFlag == 2){
                    Scene scene = new Scene(new VsPcScene(myStage));
                    myStage.setScene(scene);
                }
                else if (pageFlag == 3) {
                    if (status.equals("Win")) {
                        OnlineGameScene.score++;
                    }
                    Scene scene = new Scene(new OnlineGameScene(myStage));
                    myStage.setScene(scene);
                }
           
                
            }
        });
      getChildren().add(mediaView);
       getChildren().add(skipBtn);
       
    }

   @Override
    public void initialize(URL location, ResourceBundle resources) {
    }

    
    
    
}
