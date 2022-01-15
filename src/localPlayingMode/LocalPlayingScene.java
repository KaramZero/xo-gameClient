package localPlayingMode;

import Video.VideoModel;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import static home.Home.bGround;
import static home.Home.closeLBL;
import static home.Home.minimizeLBL;
import home.Home;
import home.XOGameCLient;
import javafx.scene.media.MediaPlayer;
import xo.XOBoard;
import static xo.XOBoard.buttons;
import static xo.XOBoard.clearBoard;
import static xo.XOBoard.myBoard;
import static xo.XOBoard.setBTNs;
import static xo.XOBoard.setDisableBtn;
import static xo.XOModel.checkWin;
import static xo.XOModel.isEmptyBoard;


public class LocalPlayingScene extends AnchorPane {

    private Label btnX;
    private Label btnO;
    private Label backBTN;
    private Label newGameBTN;
    private Label labelWin;
    private Label boardLBL;
    boolean flag = true;
    private Stage myStage;

    private double xOffset = 0;
    private double yOffset = 0;


    
    
    Image xIMG = new Image("Icons/x.png",60,60,true,true);
    ImageView x = new ImageView(xIMG);

    Image oIMG =new Image("Icons/o.png",60,60,true,true);
    ImageView o = new ImageView(oIMG);

        
    EventHandler a = (EventHandler) (Event event) -> {
        Button t = ((Button) event.getSource());   
        int xCord=0, yCord=0;
        
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == t) {
                    xCord = i;
                    yCord = j;
                }
            }
        }
        
        if (flag && myBoard[xCord][yCord] == '_') {
            t.setGraphic(new ImageView(xIMG));
            myBoard[xCord][yCord] = 'X';
            flag = false;
        } else if (!flag && myBoard[xCord][yCord] == '_') {
            t.setGraphic(new ImageView(oIMG));
            myBoard[xCord][yCord] = 'O';
            flag = true;
        }
        if(flag){
           labelWin.setGraphic(new ImageView(xIMG));
        }
        else{
           labelWin.setGraphic(new ImageView(oIMG));
        }
     
        if (checkWin(myBoard)) {
            labelWin.setGraphic(null);
           clearBoard();
           setDisableBtn(true);
           Scene s= new Scene(new VideoModel(myStage,"Win",1));
           myStage.setScene(s);
        }
        
        else if (!(isEmptyBoard(myBoard))) {
           clearBoard();
           setDisableBtn(true);
        }
    };    
   
    public LocalPlayingScene(Stage s) {
        myStage=s;
        btnO = new Label();
        btnX = new Label();
        boardLBL = new Label();
        backBTN = new Label();
        newGameBTN = new Label();
        labelWin = new Label();
 
        
        this.setOnMousePressed((MouseEvent event) -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        this.setOnMouseDragged((MouseEvent event) -> {
            myStage.setX(event.getScreenX() - xOffset);
            myStage.setY(event.getScreenY() - yOffset);
        });
         
        boardLBL.setLayoutX(310);
        boardLBL.setLayoutY(210);
        boardLBL.setPrefSize(270, 270);
        boardLBL.setGraphic(new ImageView(new Image("Icons/board.png",270,270,true,true)));
     
        getChildren().add(boardLBL);

        setBTNs();
        setButtons();
        setDisableBtn(true);

        setId("AnchorPane");
        setPrefHeight(650);
        setPrefWidth(850);
       
        btnX.setLayoutX(350);
        btnX.setLayoutY(100);
        btnX.setPrefSize(80, 80);   
        btnX.setGraphic(x);
     

        btnO.setLayoutX(470);
        btnO.setLayoutY(100);
        btnO.setPrefSize(80, 80);
        btnO.setGraphic(o);

        
        backBTN.setLayoutX(650);
        backBTN.setLayoutY(80);
        backBTN.setPrefSize(80, 80);
        backBTN.setGraphic(new ImageView(new Image("Icons/Home.png",80,80,true,true)));
        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                Scene scene = new Scene(new Home(s));
                s.setScene(scene);
            }
        });
        
        newGameBTN.setLayoutX(130);
        newGameBTN.setLayoutY(80);
        newGameBTN.setPrefSize(80, 80);
        newGameBTN.setGraphic(new ImageView(new Image("Icons/reNew.png",80,80,true,true)));

        newGameBTN.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                Scene scene = new Scene(new LocalPlayingScene(s));
                s.setScene(scene);
            }
        });

        btnX.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                  flag = true;
               btnO.setDisable(true);
                btnX.setDisable(true);
                XOBoard.setDisableBtn(false);
            }
        });
        btnO.setOnMouseClicked(new EventHandler<MouseEvent>(){
            @Override
           public void handle(MouseEvent event)  {
                   flag = false; 
                 btnO.setDisable(true);
                btnX.setDisable(true);
                 XOBoard.setDisableBtn(false);
            }
        });

        labelWin.setLayoutX(310);
        labelWin.setLayoutY(10);
        labelWin.setMinHeight(50);
        labelWin.setMinWidth(270);
        labelWin.setAlignment(Pos.CENTER);
        
        Label soundLBL = new Label();
        soundLBL.setLayoutX(30);
        soundLBL.setLayoutY(5);
        soundLBL.setPrefSize(50, 50);
        soundLBL.setGraphic(new ImageView(new Image("Icons/mute.png", 50, 50, true, true)));
        soundLBL.setOnMouseClicked((MouseEvent event) -> {
            if (XOGameCLient.mediaPlayer.getStatus() == MediaPlayer.Status.PLAYING) {
                soundLBL.setGraphic(new ImageView(new Image("Icons/mute.png", 50, 50, true, true)));
                XOGameCLient.mediaPlayer.pause();
            } else {
                soundLBL.setGraphic(new ImageView(new Image("Icons/unmute.png", 50, 50, true, true)));
                XOGameCLient.mediaPlayer.play();
            }
        });
        getChildren().add(soundLBL);
        

        setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);
        getChildren().add(btnO);
        getChildren().add(btnX);
        getChildren().add(newGameBTN);
        getChildren().add(backBTN);
        getChildren().add(labelWin);

    }

     private void setButtons(){
       for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnAction(a);
                getChildren().add(buttons[i][j]);
            }
    }
       btnO.setDisable(false);
       btnX.setDisable(false);
  }   
}
