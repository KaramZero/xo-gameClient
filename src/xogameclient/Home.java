package xogameclient;

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
import javafx.stage.Stage;

public  class Home extends AnchorPane {

    public static Label closeLBL;
    public static Label minimizeLBL;
    public static   Background bGround;

    protected final Button btnLocal;
    protected final Button btnOnline;
    protected final Button btnVsPc;
    protected final Label label;
    private Stage myStage;
    

    public Home(Stage stage) {

        btnLocal = new Button();
        btnOnline = new Button();
        btnVsPc = new Button();
        label = new Label();
        myStage = stage;
        closeLBL = new Label();
        minimizeLBL = new Label();

       

         Image closeIMG = new Image("Icons/close.png",50,50,true,true);

          Image miniIMG = new Image("Icons/minimize.png",50,50,true,true);
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
        
        Image back =new Image("Icons/Back.jpg");
       
        BackgroundImage bImg = new BackgroundImage(back, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
       
         bGround = new Background(bImg);
       
        setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);
        setMaxHeight(USE_PREF_SIZE);
        setMaxWidth(USE_PREF_SIZE);
        setMinHeight(USE_PREF_SIZE);
        setMinWidth(USE_PREF_SIZE);
        setPrefHeight(650.0);
        setPrefWidth(850.0);

        btnLocal.setLayoutX(350.0);
        btnLocal.setLayoutY(300.0);
        btnLocal.setMnemonicParsing(false);
        btnLocal.setPrefHeight(63.0);
        btnLocal.setPrefWidth(144.0);
        btnLocal.setText("Local Playing");
        
        btnLocal.setOnAction((event) -> {
           Scene scene = new Scene(new LocalPlayingScene(stage));
            myStage.setScene(scene);

        });

        btnOnline.setLayoutX(350.0);
        btnOnline.setLayoutY(198.0);
        btnOnline.setMnemonicParsing(false);
        btnOnline.setPrefHeight(72.0);
        btnOnline.setPrefWidth(144.0);
        btnOnline.setText("Online Mode");
        
        btnOnline.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
             Scene scene = new Scene(new OnlineLoginScene(stage));
            myStage.setScene(scene);
            }
        });

        btnVsPc.setLayoutX(350.0);
        btnVsPc.setLayoutY(386.0);
        btnVsPc.setMnemonicParsing(false);
        btnVsPc.setPrefHeight(63.0);
        btnVsPc.setPrefWidth(144.0);
        btnVsPc.setText("VS Pc");
        
         btnVsPc.setOnAction((event) -> {
           Scene scene = new Scene(new VsPcScene(stage));
            myStage.setScene(scene);
        });

        label.setAlignment(javafx.geometry.Pos.CENTER);
        label.setLayoutX(91.0);
        label.setLayoutY(67.0);
        label.setPrefHeight(43.0);
        label.setPrefWidth(634.0);
        label.setText("Welcome to my Game");

        getChildren().add(btnLocal);
        getChildren().add(btnOnline);
        getChildren().add(btnVsPc);
        getChildren().add(label);
              

    }
}
