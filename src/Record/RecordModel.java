package Record;

import java.io.*;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import localPlayingMode.LocalPlayingScene;

public class RecordModel {

    // record
    BufferedWriter buffer;
    BufferedReader buffer2;
    FileWriter fileWriter;
    FileReader fileReader;
    File file;
    FilenameFilter filter;
    boolean recordFlag;
    public static int counter;

    ListView<String> list;

    public RecordModel() {
        file = new File("G:\\ITI\\Java\\xo-game");
        counter = 0;

        // file filter 
        FilenameFilter filter = new FilenameFilter() {

            public boolean accept(File f, String name) {
                return name.endsWith("game.txt");
            }
        };

    }  // end of constructor 

    // saverecord method  
    public void saveRecord(String arr[][], int counter) {

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yy HH-mm-ss");
        Date date = new Date();
        try {

            fileWriter = new FileWriter("G:\\ITI\\Java\\xo-game\\" + dateFormat.format(date) + "game.txt");
            buffer = new BufferedWriter(fileWriter);

            // buffer.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        try {
            for (int i = 0; i <= counter; i++) {

                buffer.write((arr[i][0] + " " + arr[i][1] + " " + arr[i][2] + "\n"));

            }

            buffer.close();

        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public ObservableList<String> getFile() {
        // file filter 
        FilenameFilter filter = new FilenameFilter() {

            public boolean accept(File f, String name) {
                return name.endsWith("game.txt");
            }
        };
        File[] files = file.listFiles(filter);
        list = new ListView<String>();
        ObservableList<String> items = FXCollections.observableArrayList();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].getName());

            items.add(files[i].getName());

        }
        return items;

    }

    // record loadrecord method 
    public String[][] loadRecord(String fname) throws IOException {

        Button[][] btn = new Button[3][3];
        String[][] movearray = new String[9][3];
        fileReader = new FileReader(fname);
        buffer2 = new BufferedReader(fileReader);

        // draw board
        for (int i = 0; i < 9; i++) {

            String s;
            try {

                s = buffer2.readLine();
                if (s != null) {
                    counter++;
                    String[] arr = s.split(" ");
                    String str = arr[0];

                    String row = (arr[1]);
                    String col = (arr[2]);

                    if (str.equals("X")) {

                        movearray[i][0] = "X";
                        movearray[i][1] = row;
                        movearray[i][2] = col;

                    } else if (str.equals("O")) {

                        movearray[i][0] = "O";
                        movearray[i][1] = row;
                        movearray[i][2] = col;

                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(LocalPlayingScene.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        return movearray;

    }

}
