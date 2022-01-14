package vsPcMode;

import Video.VideoModel;
import vsPcMode.levels.Move;
import vsPcMode.levels.HardLevel;
import home.Home;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
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
import vsPcMode.levels.EasyLevel;
import vsPcMode.levels.Level;
import vsPcMode.levels.NormalLevel;
import xo.XOModel;
import static xo.XOModel.checkWin;
import static xo.XOModel.isEmptyBoard;
import static xo.XOBoard.buttons;
import static xo.XOBoard.clearBoard;
import static xo.XOBoard.myBoard;
import static xo.XOBoard.setBTNs;
import static xo.XOBoard.setDisableBtn;

public class VsPcScene extends AnchorPane {

    private Label btnX;
    private Label btnO;
    private Label backBTN;
    private Label newGameBTN;
    private Label boardLBL;
    private Move pcMove;
    private Level level;
    boolean flag = true;
    Stage myStage;

    Image player = new Image("Icons/x.png", 60, 60, true, true);
    ImageView x = new ImageView(player);
    char playerChar;

    Image pc = new Image("Icons/o.png", 60, 60, true, true);
    ImageView o = new ImageView(pc);
    char pcChar;
    
        
    private double xOffset = 0;
    private double yOffset = 0;


    EventHandler a = (EventHandler) (Event event) -> {

        Button btnTemp = ((Button) event.getSource());

        int xCord = 0, yCord = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == btnTemp) {
                    xCord = i;
                    yCord = j;
                }
            }
        }

        if (myBoard[xCord][yCord] == '_') {
            btnTemp.setGraphic(new ImageView(player));
            myBoard[xCord][yCord] = playerChar;

            if (checkWin(myBoard)) {
                clearBoard();
                setDisableBtn(true);
                Scene s = new Scene(new VideoModel(myStage, "Win", 2));
                myStage.setScene(s);
            } else if (!(isEmptyBoard(myBoard))) {
                clearBoard();
                setDisableBtn(true);
            } else {
               
                    pcMove = level.findMove(myBoard, pcChar);
              
                buttons[pcMove.row][pcMove.col].setGraphic(new ImageView(pc));
                myBoard[pcMove.row][pcMove.col] = pcChar;

                if (XOModel.checkWin(myBoard)) {
                    clearBoard();
                    setDisableBtn(true);
                    Scene s = new Scene(new VideoModel(myStage, "Lose", 2));
                    myStage.setScene(s);
                } else if (!(XOModel.isEmptyBoard(myBoard))) {
                    clearBoard();
                    setDisableBtn(true);
                }

            }
        }
    };

    public VsPcScene(Stage s) {

        btnO = new Label();
        btnX = new Label();
        backBTN = new Label();
        newGameBTN = new Label();
        boardLBL = new Label();
        pcMove = new Move();
        myStage = s;
        
         
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
        boardLBL.setGraphic(new ImageView(new Image("Icons/board.png", 270, 270, true, true)));

        Button hard = new Button();
        hard.setLayoutX(375);
        hard.setLayoutY(400);
        hard.setPrefSize(80, 80);
        hard.setBackground(null);
        hard.setGraphic(new ImageView(new Image("Icons/hard.png",80,80,true,true)));
       

        Button normal = new Button();
        normal.setLayoutX(375);
        normal.setLayoutY(300);
        normal.setPrefSize(80, 80);
        normal.setBackground(null);
        normal.setGraphic(new ImageView(new Image("Icons/normal.png",80,80,true,true)));
        
        
        Button easy = new Button();
        easy.setLayoutX(375);
        easy.setLayoutY(200);
        easy.setPrefSize(80, 80);
        easy.setBackground(null);
        easy.setGraphic(new ImageView(new Image("Icons/easy.png",80,80,true,true)));
      

        getChildren().add(hard);
        getChildren().add(normal);
        getChildren().add(easy);

        normal.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                setBTNs();
                setButtons();
                clearBoard();
                setDisableBtn(true);
                level = new NormalLevel();
                btnO.setDisable(false);
                btnX.setDisable(false);
                boardLBL.setDisable(false);

                
                getChildren().remove(hard);
                getChildren().remove(normal);
                getChildren().remove(easy);
                

            }
        });
        hard.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                setBTNs();
                setButtons();
                clearBoard();
                setDisableBtn(true);
                level = new HardLevel();
                btnO.setDisable(false);
                btnX.setDisable(false);

                
                getChildren().remove(hard);
                getChildren().remove(normal);
                getChildren().remove(easy);
                
            }
        });
        
         easy.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {

                setBTNs();
                setButtons();
                clearBoard();
                setDisableBtn(true);
                level = new EasyLevel();
                btnO.setDisable(false);
                btnX.setDisable(false);


                getChildren().remove(hard);
                getChildren().remove(normal);
                getChildren().remove(easy);
                
            }
        });


        setId("AnchorPane");
        setPrefHeight(650);
        setPrefWidth(850);

        btnX.setLayoutX(310);
        btnX.setLayoutY(80);
        btnX.setPrefSize(80, 80);
        btnX.setGraphic(new ImageView(new Image("Icons/x.png", 60, 60, true, true)));

        btnO.setLayoutX(470);
        btnO.setLayoutY(80);
        btnO.setPrefSize(80, 80);
        btnO.setGraphic(new ImageView(new Image("Icons/o.png", 60, 60, true, true)));

        btnX.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player = new Image("Icons/x.png", 60, 60, true, true);
                pc = new Image("Icons/o.png", 60, 60, true, true);
                playerChar = 'X';
                pcChar = 'O';
                btnO.setDisable(true);
                btnX.setDisable(true);

                setDisableBtn(false);
            }
        });
        btnO.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                player = new Image("Icons/o.png", 60, 60, true, true);
                pc = new Image("Icons/x.png", 60, 60, true, true);
                playerChar = 'O';
                pcChar = 'X';

                btnO.setDisable(true);
                btnX.setDisable(true);
                setDisableBtn(false);
            }
        });

        btnO.setDisable(true);
        btnX.setDisable(true);

        backBTN.setLayoutX(650);
        backBTN.setLayoutY(80);
        backBTN.setPrefSize(100, 30);
        backBTN.setGraphic(new ImageView(new Image("Icons/Home.png", 80, 80, true, true)));
        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Scene scene = new Scene(new Home(s));
                s.setScene(scene);
            }
        });

        newGameBTN.setLayoutX(130);
        newGameBTN.setLayoutY(80);
        newGameBTN.setPrefSize(100, 30);

        newGameBTN.setGraphic(new ImageView(new Image("Icons/reNew.png", 80, 80, true, true)));
        newGameBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Scene scene = new Scene(new VsPcScene(s));
                s.setScene(scene);
            }
        });

        setBackground(bGround);
        getChildren().add(btnO);
        getChildren().add(btnX);
        getChildren().add(newGameBTN);
        getChildren().add(backBTN);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);

    }
    private void setButtons() {
        
        getChildren().add(boardLBL);
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
