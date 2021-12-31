package xogameclient;


import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class TwoPlayerScene extends AnchorPane {

    protected final Button btn1 = new Button();;
    protected final Label  label = new Label();
    protected  Button[][] button;
    
    boolean flag = true;
    
    EventHandler a = (EventHandler) (Event event) -> {
        Button btnTemp = ((Button) event.getSource());
        if(btnTemp.getText().isEmpty()){
        if (flag && ((Button) event.getSource()).getText().equalsIgnoreCase("")) {
            ((Button) event.getSource()).setText("X");
            flag = false;
        } else if (!flag && ((Button) event.getSource()).getText().equalsIgnoreCase("")) {
            ((Button) event.getSource()).setText("O");
            flag = true;
        }
       
        if(checkWin()==1){
        label.setText(((Button) event.getSource()).getText()+" Wins :D ");
        setBTNs();
        }else  if(checkWin()==2){
        label.setText(" No one wins .. Play again ");
        setBTNs();
        }
        }
    };
    
    
    int checkWin() {
        int f = 2;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(button[i][j].getText().equals("")) f= 0;
            }
        }

        for ( int i = 0; i < 3; i++) {

            if (button[i][0].getText().equals(button[i][1].getText()) && button[i][0].getText().equals(button[i][2].getText())) {
                if (!button[i][0].getText().equals("")) {
                    f = 1;
                   
                    break;
                }
            }
            if (button[0][i].getText().equals(button[1][i].getText()) && button[0][i].getText().equals(button[2][i].getText())) {
                if (!button[0][i].getText().equals("")) {
                   
                    f = 1;
                    break;
                }
            }

        }

        if (button[0][0].getText().equals(button[1][1].getText()) && button[0][0].getText().equals(button[2][2].getText()) && !button[0][0].getText().equals("")) {
            f = 1;
        
        }
        if (button[0][2].getText().equals(button[1][1].getText()) && button[0][2].getText().equals(button[2][0].getText()) && !button[0][2].getText().equals("")) {
            f = 1;
           
        }

        return f;

    }
    final void setBTNs(){
      for(int i = 0 ; i < 3 ; i++ ){
            for(int j = 0 ; j < 3 ; j++){
                button[i][j] = new Button();
                button[i][j].setPrefSize(50, 50);
                button[i][j].setLayoutX(180+j*50);
                button[i][j].setLayoutY(150+i*50);
                button[i][j].setMnemonicParsing(false);
                button[i][j].setOnAction(a);

                button[i][j].setText("");
                
                 getChildren().add(button[i][j]);
              
            }
        }
    
    }
    

    public TwoPlayerScene(Stage s) {

               
        button = new Button[3][3];
        
        setBTNs();
               
        
     
        setId("AnchorPane");
        setPrefHeight(400);
        setPrefWidth(520);

        btn1.setLayoutX(217.0);
        btn1.setLayoutY(28.0);
       
        btn1.setText("to scene2");
        

        label.setLayoutX(200);
        label.setLayoutY(50);
        label.setMinHeight(16);
        label.setMinWidth(69);
        label.setText(" Who will win ? ");

        

      //  getChildren().add(btn1);
        getChildren().add(label);
       

    }

}
