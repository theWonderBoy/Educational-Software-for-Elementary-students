package Educational_Software;

import java.awt.*;
import java.io.*;

public class Start extends EasyApp {

    String str; //saves test name
    Label title = addLabel("Choose the test (select a test and Start)", 50, 50, 380, 50, this);
    List Test1 = addList("", 50, 120, 200, 200, this);
    List Test2 = addList("", 300, 120, 200, 200, this);
    List Test3 = addList("", 550, 120, 200, 200, this);
    Button test1 = addButton("Start", 100, 340, 100, 50, this);
    Button test2 = addButton("Start", 350, 340, 100, 50, this);
    Button test3 = addButton("Start", 600, 340, 100, 50, this);

    Button Close = addButton("Close", 680, 50, 100, 50, this);
    String ST = "S"; //Playlabr class is called from teacher and student class;
    //in students case, "back" button should close the test
    //in teachers case, "back" button should close the test and return in editing/creating window
    

    Start() {
        setTitle("Choose test");
        setBounds(150, 150, 800, 440);
        title.setFont(new Font("Arial", 0, 20));
        title.setForeground(Color.getHSBColor(0.3f, 0.99f, 0.3f));
        Test1.setFont(new Font("Arial", 0, 15));
        Test2.setFont(new Font("Arial", 0, 15));
        Test3.setFont(new Font("Arial", 0, 15));
        test1.setFont(new Font("Arial", 0, 15));
        test2.setFont(new Font("Arial", 0, 15));
        test3.setFont(new Font("Arial", 0, 15));
        ref1(); //read test names from listtest.txt file and put in List window
        ref2(); //read test names from listtest2.txt file and put in List window
        ref3(); //read test names from listtest3.txt file and put in List window
    }

    String[][] Array = new String[5][10];//read data from txt file, where labyrinth map is created

    public void actions(Object source, String command) {
        if (source == test1) {
            str = Test1.getSelectedItem(); //test name
            if (str == null || str.equals("") || str.equals("Created Test  List:")) {
                output("Plese, select item!");
            } else {
                try {
                    RandomAccessFile testFile = new RandomAccessFile(str + ".txt", "rw");
                    if (testFile.length() == 0) { //in case teacher created only title without exercises
                        output("Sorry! The test is not finished yest.\nTry another one!");
                    } else {
                        new studentT1(str);
                        dispose();
                    }
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        if (source == test2) {
            str = Test2.getSelectedItem();//test name
            if (str == null || str.equals("") || str.equals("Created Test  List:")) {
                output("Plese, select item!");
            } else {
                try {
                    RandomAccessFile testFile = new RandomAccessFile(str + ".txt", "rw");
                    if (testFile.length() == 0) {//in case teacher created only title without exercises
                        output("Sorry! The test is not finished yest.\nTry another one!");
                    } else {
                        new studentT2(str);
                        dispose();
                    }
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        if (source == test3) {
            str = Test3.getSelectedItem();//test name
            if (str == null || str.equals("") || str.equals("Created Test  List:")) {
                output("Plese, select item!");
            } else {
                try {
                    RandomAccessFile testFile = new RandomAccessFile(str + ".txt", "rw");
                    if (testFile.length() == 0) {//in case teacher created only title without exercises
                        output("Sorry! The test is not finished yest.\nTry another one!");
                    } else {
                        createArray();
                        new Playlabr(Array, str, ST);
                        dispose();
                    }
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        }
        if (source == Close) {
            dispose();
        }
    }

    private void ref1() {
        try {
            RandomAccessFile Quiz = new RandomAccessFile("listtest.txt", "rw");
            Test1.removeAll();
            Test1.add("Created Test  List:");
            Test1.add("");
            while (Quiz.length() != Quiz.getFilePointer()) {
                Test1.add(Quiz.readLine());
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void ref2() {
        try {
            RandomAccessFile Math = new RandomAccessFile("listtest2.txt", "rw");
            Test2.removeAll();
            Test2.add("Created Test  List:");
            Test2.add("");
            while (Math.length() != Math.getFilePointer()) {
                Test2.add(Math.readLine());
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    private void ref3() {
        try {
            RandomAccessFile WOL = new RandomAccessFile("listtest3.txt", "rw");
            Test3.removeAll();
            Test3.add("Created Test  List:");
            Test3.add("");
            while (WOL.length() != WOL.getFilePointer()) {
                Test3.add(WOL.readLine());
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    void createArray() {
        String line;
        try {
            RandomAccessFile labyrinthMap = new RandomAccessFile(str + ".txt", "rw");
            for (int i = 0; i < 5; i++) {
                line = labyrinthMap.readLine();
                for (int j = 0; j < 9; j++) {
                    Array[i][j] = line.substring(j, j + 1);
                }
                Array[i][9] = line.substring(9);
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
