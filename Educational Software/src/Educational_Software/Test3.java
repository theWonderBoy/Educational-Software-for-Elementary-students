package Educational_Software;

import java.awt.*;
import java.io.*;

class Test3 extends EasyApp {

    boolean checkMap;//check Map is correct or not
    int testS = 0; //count Start button on the Map
    int testE = 0; //count Exit button on the Map
    Label CR = addLabel("Map of labyrinth", 50, 50, 250, 50, this);
    Button close = addButton("Close", 950, 50, 100, 50, this);
    String[][] Array = new String[5][10];//buttons names
    String str = ""; //read button label

    Label tools = addLabel("T o o l s", 50, 100, 250, 50, this);
    //tools we need to create map of labyrinth
    Button A1 = addButton(" ", 50, 150, 100, 50, this);
    Button A2 = addButton("|", 160, 150, 100, 50, this);//walls
    Button A3 = addButton("S", 270, 150, 100, 50, this);//start button
    Button A4 = addButton("E", 380, 150, 100, 50, this);//end button

    Label menu = addLabel("M e n u", 650, 100, 250, 50, this);
    Button testB = addButton("Test", 650, 150, 100, 50, this);//teacher tests the map
    Button checkB = addButton("Check", 760, 150, 100, 50, this);
    //one starts and one end button should be on the map
    Button saveB = addButton("Save", 870, 150, 100, 50, this);
    //save the map in .txt file

    Button[][] A = new Button[5][10];
    String testName;
    String ST = "T"; //Playlabr class is called from teacher and student class

    Test3(String testName) {
        setBounds(100, 100, 1100, 500);
        setTitle("Way out of labyrinth");
        CR.setFont(new Font("Arial", 0, 20));
        tools.setFont(new Font("Arial", 0, 18));
        menu.setFont(new Font("Arial", 0, 18));
        testB.setFont(new Font("Arial", 0, 17));
        checkB.setFont(new Font("Arial", 0, 17));
        saveB.setFont(new Font("Arial", 0, 17));
        A2.setFont(new Font("Arial", 0, 17));
        A3.setFont(new Font("Arial", 0, 17));
        A4.setFont(new Font("Arial", 0, 17));

        this.testName = testName;
        //check, weather the test is already created or not
        checkTest();
        //create 50 buttons

    }

    public void actions(Object source, String command) {
        if (source == close) {
            dispose();
        }
        if (source == checkB) {
            check();
        }
        if (source == saveB) {
            save();
        }

        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (source == A[i][j]) {
                    if (str.equals("")) {
                        output("select the tool");
                    } else {
                        Array[i][j] = str;
                        A[i][j].setLabel(str);
                    }
                }
            }
        }

        if (source == testB) {
            readFromFile();
            new Playlabr(Array, testName, ST);
            dispose();
        }
        if (source == A1) {
            str = A1.getLabel();
        }
        if (source == A2) {
            str = A2.getLabel();
        }
        if (source == A3) {
            str = A3.getLabel();
        }
        if (source == A4) {
            str = A4.getLabel();
        }
    }

    void checkTest() {
        try {
            RandomAccessFile testFile = new RandomAccessFile(testName + ".txt", "rw");
            if (testFile.length() == 0) {
                //test is empty and we should initialaize an array
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 10; j++) {
                        A[i][j] = addButton("", 50 + 100 * j, 220 + 50 * i, 100, 50, this);
                        Array[i][j] = " ";
                    }
                }
            } else {
                //test is created and let's read the test
                readFromFile();
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    void readFromFile() {
        //test is created and let's read the test
        String line = "";
        int i = 0;
        try {
            RandomAccessFile testFile = new RandomAccessFile(testName + ".txt", "rw");
            while (i < 5) {
                line = testFile.readLine();
                for (int j = 0; j < 9; j++) {
                    Array[i][j] = line.substring(j, j + 1);
                    A[i][j] = addButton(Array[i][j], 50 + 100 * j, 220 + 50 * i, 100, 50, this);
                }
                Array[i][9] = line.substring(9);
                A[i][9] = addButton(Array[i][9], 50 + 100 * 9, 220 + 50 * i, 100, 50, this);
                i++;
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    String mapCode;

    private void save() {
        mapCode = "";
        check();
        if (!checkMap) {
            output("The Map is not correct! \nPlease, correct it");
        } else {
            try {
                RandomAccessFile MapF = new RandomAccessFile(testName + ".txt", "rw");
                MapF.setLength(0);
                for (int i = 0; i < 5; i++) {
                    for (int j = 0; j < 10; j++) {
                        mapCode = mapCode + Array[i][j];
                    }
                    MapF.writeBytes(mapCode + "\n");
                    mapCode = "";
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }

    private void check() {
        testS = 0;
        testE = 0;
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 10; j++) {
                if (Array[i][j].equals("S")) {
                    testS++;
                }
                if (Array[i][j].equals("E")) {
                    testE++;
                }
            }
        }

        if (testS == 1 && testE == 1) {
            checkMap = true;
        } else {
            checkMap = false;
        }
    }
}
