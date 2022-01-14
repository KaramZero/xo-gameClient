package home;

import Record.recordedGamePlayerScene;
import localPlayingMode.LocalPlayingScene;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import onlineMode.OnlineGameScene;
import onlineMode.OnlineMode;
import vsPcMode.VsPcScene;

public class Home extends AnchorPane {

    public static Label closeLBL;
    public static Label minimizeLBL;
    public static Background bGround;

    protected final Button btnLocal;
    protected final Button btnOnline;
    protected final Button btnVsPc;
    protected final Label label;
    public static Stage myStage;
    public static boolean onlineFlag = false;
    private Label LoadBTN;
    
    private double xOffset = 0;
    private double yOffset = 0;

    public static Scene onlineScene;

    public Home(Stage stage) {

        myStage = stage;
        btnLocal = new Button();
        btnOnline = new Button();
        btnVsPc = new Button();
        label = new Label();
        LoadBTN = new Label();

        closeLBL = new Label();
        minimizeLBL = new Label();
          
        
 
        setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        setOnMouseDragged((MouseEvent event) -> {
            myStage.setX(event.getScreenX() - xOffset);
            myStage.setY(event.getScreenY() - yOffset);
        });
        
        
        
        

        Image closeIMG = new Image("Icons/close.png", 50, 50, true, true);
        Image miniIMG = new Image("Icons/minimize.png", 50, 50, true, true);
        Image twoPlayersIMG = new Image("Icons/TwoPlayers.png", 100, 100, true, true);
        Image onlineIMg = new Image("Icons/Online.png", 100, 100, true, true);
        Image vsPCIMG = new Image("Icons/VsPc.png", 100, 100, true, true);
        Image unmute = new Image("Icons/unmute.png", 50, 50, true, true);
        Image mute = new Image("Icons/mute.png", 50, 50, true, true);

        closeLBL.setLayoutX(800);
        closeLBL.setLayoutY(5);
        closeLBL.setPrefSize(50, 50);
        closeLBL.setGraphic(new ImageView(closeIMG));
        closeLBL.setOnMouseClicked((MouseEvent event) -> {
            System.exit(0);
        });

        minimizeLBL.setLayoutX(750);
        minimizeLBL.setLayoutY(5);
        minimizeLBL.setPrefSize(50, 50);
        minimizeLBL.setGraphic(new ImageView(miniIMG));
        minimizeLBL.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                stage.setIconified(true);

            }
        });

        Label soundLBL = new Label();
        soundLBL.setLayoutX(30);
        soundLBL.setLayoutY(5);
        soundLBL.setPrefSize(50, 50);
        soundLBL.setGraphic(new ImageView(mute));
        soundLBL.setOnMouseClicked((MouseEvent event) -> {
            if (XOGameCLient.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                soundLBL.setGraphic(new ImageView(mute));
                XOGameCLient.mediaPlayer.pause();
            } else {
                soundLBL.setGraphic(new ImageView(unmute));
                XOGameCLient.mediaPlayer.play();
            }
        });


        LoadBTN.setLayoutX(150);
        LoadBTN.setLayoutY(150);
        LoadBTN.setPrefSize(80, 80);
        LoadBTN.setGraphic(new ImageView(new Image("Icons/recordedGames.png", 60, 60, true, true)));
        LoadBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Scene scene = new Scene(new recordedGamePlayerScene(stage));
                myStage.setScene(scene);
            }
        });

        Image back = new Image("Icons/Back.jpg");

        BackgroundImage bImg = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);

        bGround = new Background(bImg);

        setBackground(bGround);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(650.0);
        setPrefWidth(850.0);

        btnLocal.setLayoutX(350);
        btnLocal.setLayoutY(180);
        btnLocal.setMnemonicParsing(false);
        btnLocal.setPrefHeight(150);
        btnLocal.setPrefWidth(150);
        btnLocal.setBackground(null);
        btnLocal.setGraphic(new ImageView(twoPlayersIMG));

        btnLocal.setOnAction((event) -> {
            Scene scene = new Scene(new LocalPlayingScene(stage));
            myStage.setScene(scene);

        });

        btnOnline.setLayoutX(350);
        btnOnline.setLayoutY(300);
        btnOnline.setMnemonicParsing(false);
        btnOnline.setPrefHeight(150);
        btnOnline.setPrefWidth(150);
        btnOnline.setBackground(null);
        btnOnline.setGraphic(new ImageView(onlineIMg));

        btnOnline.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                if (onlineFlag) {
                    Scene s = new Scene(new OnlineGameScene(myStage));
                    myStage.setScene(s);
                } else {
                    Scene scene = new Scene(new OnlineMode(stage));
                    myStage.setScene(scene);
                }
            }
        });

        btnVsPc.setLayoutX(350);
        btnVsPc.setLayoutY(420);
        btnVsPc.setMnemonicParsing(false);
        btnVsPc.setPrefHeight(150);
        btnVsPc.setPrefWidth(150);
        btnVsPc.setBackground(null);
        btnVsPc.setGraphic(new ImageView(vsPCIMG));

        btnVsPc.setOnAction((event) -> {
            Scene scene = new Scene(new VsPcScene(stage));
            myStage.setScene(scene);
        });

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setLayoutX(91.0);
        label.setLayoutY(77.0);
        label.setPrefHeight(43.0);
        label.setPrefWidth(634.0);
        label.setTextFill(Color.AQUA);
        label.setFont(new Font(30));
        label.setText("Welcome to my Game");


        getChildren().add(LoadBTN);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);
        getChildren().add(soundLBL);
        getChildren().add(btnLocal);
        getChildren().add(btnOnline);
        getChildren().add(btnVsPc);
        getChildren().add(label);

    }
}
