package Record;

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
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.scene.control.ListView;
import xo.XOBoard;
import static xo.XOBoard.buttons;
import static xo.XOBoard.setBTNs;
import static xo.XOBoard.setDisableBtn;

public class recordedGamePlayerScene extends AnchorPane {

    private Label backBTN;
    private Label labelWin;
    private Label boardLBL;
    boolean flag = true;

    RecordModel recordModel;
    private Label LoadBTN;
    ListView<String> list;
    String[][] loadArray;

    private Stage myStage;

    Image xIMG = new Image("Icons/x.png", 60, 60, true, true);
    ImageView x = new ImageView(xIMG);

    Image oIMG = new Image("Icons/o.png", 60, 60, true, true);
    ImageView o = new ImageView(oIMG);

    EventHandler a = (EventHandler) (Event event) -> {
        Button t = ((Button) event.getSource());
        int xCord = 0, yCord = 0;

        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (buttons[i][j] == t) {
                    xCord = i;
                    yCord = j;
                }
            }
        }

        if (flag) {
            Platform.runLater(() -> {
                t.setGraphic(new ImageView(xIMG));
                labelWin.setGraphic(new ImageView(oIMG));
            });
            flag = false;
        } else if (!flag) {
            Platform.runLater(() -> {
                t.setGraphic(new ImageView(oIMG));
                labelWin.setGraphic(new ImageView(xIMG));
            });
            flag = true;
        }
        
    };

    public recordedGamePlayerScene(Stage s) {

        loadArray = new String[9][3];
        recordModel = new RecordModel();
        LoadBTN = new Label();
        list = new ListView<String>();

        myStage = s;

        boardLBL = new Label();
        backBTN = new Label();
        labelWin = new Label();

        LoadBTN.setLayoutX(150);
        LoadBTN.setLayoutY(150);
        LoadBTN.setPrefSize(80, 80);
        LoadBTN.setGraphic(new ImageView(new Image("Icons/reNew.png", 60, 60, true, true)));

        LoadBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

                list.setItems(recordModel.getFile());
                list.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent event) {
                        if (event.getClickCount() == 2) {

                            list.setDisable(true);
                            for (int i = 0; i < 3; i++) {
                                for (int j = 0; j < 3; j++) {
                                    buttons[i][j].setGraphic(null);
                                }
                            }

                            playRecord();

                        }
                    }
                });

                getChildren().add(list);

                LoadBTN.setDisable(true);
            }

        });

        getChildren().add(LoadBTN);

        boardLBL.setLayoutX(310);
        boardLBL.setLayoutY(210);
        boardLBL.setPrefSize(270, 270);
        boardLBL.setGraphic(new ImageView(new Image("Icons/board.png", 270, 270, true, true)));

        

        setBTNs();
        setButtons();
        setDisableBtn(true);

        setId("AnchorPane");
        setPrefHeight(650);
        setPrefWidth(850);

        backBTN.setLayoutX(650);
        backBTN.setLayoutY(60);
        backBTN.setPrefSize(80, 80);
        backBTN.setGraphic(new ImageView(new Image("Icons/Home.png", 80, 80, true, true)));
        backBTN.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                Scene scene = new Scene(new Home(s));
                s.setScene(scene);
            }
        });

      
        labelWin.setLayoutX(310);
        labelWin.setLayoutY(10);
        labelWin.setMinHeight(50);
        labelWin.setMinWidth(270);
        labelWin.setAlignment(Pos.CENTER);

        getChildren().add(boardLBL);
        setBackground(bGround);
        getChildren().add(minimizeLBL);
        getChildren().add(closeLBL);
        getChildren().add(backBTN);
        getChildren().add(labelWin);

    }

    private void playRecord() {
        new Thread() {
            @Override
            public void run() {
                try {
                    String s = "G:\\ITI\\Java\\xo-game\\" + list.getSelectionModel().getSelectedItem();
                    loadArray = recordModel.loadRecord(s);

                    System.out.println("");
                    int row;
                    int col;

                    if (loadArray[0][0].equals("O")) {
                        flag = false;
                    } else {
                        flag = true;
                    }
                    XOBoard.setDisableBtn(false);

                    for (int i = 0; i < 9 && loadArray[i][1] != null; i++) {
                        row = Integer.parseInt(loadArray[i][1]);
                        col = Integer.parseInt(loadArray[i][2]);

                        buttons[row][col].fire();

                        try {
                            sleep(1000);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(recordedGamePlayerScene.class.getName()).log(Level.SEVERE, null, ex);
                        }

                    }
                    list.setDisable(false);

                    RecordModel.counter = 0;
                } catch (IOException ex) {
                    //  Logger.getLogger(LocalPlayingScene.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

        }.start();

    }

    private void setButtons() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                buttons[i][j].setOnAction(a);
                getChildren().add(buttons[i][j]);
            }
        }
    }
}
