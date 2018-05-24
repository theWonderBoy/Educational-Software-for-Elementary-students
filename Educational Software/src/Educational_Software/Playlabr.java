package Educational_Software;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Playlabr extends EasyApp {

    int Startx = 0;
    int Starty = 0;
    ImageIcon icon1 = new ImageIcon(getClass().getResource("grass.png"));
    ImageIcon icon2 = new ImageIcon(getClass().getResource("ball.png"));
    ImageIcon icon3 = new ImageIcon(getClass().getResource("Goall.png"));
    ImageIcon icon4 = new ImageIcon(getClass().getResource("white.png"));
    String[][] array;  // gets information from Test3 class
    JLabel[][] jlabes = new JLabel[5][10];
    String testName;
    String ST;

    public Playlabr(String[][] array, String testName, String ST) {
        this.array = array;
        this.testName = testName;
        this.ST = ST;
        for (int i = 0; i < 5; i++) {
            for (int k = 0; k < 10; k++) {

                if (array[i][k].equals("|")) {
                    jlabes[i][k] = addJLabel(icon1, (k + 1) * 55, (i + 1) * 55, 55, 55, this);
                } else if (array[i][k].equals("E")) {
                    jlabes[i][k] = addJLabel(icon3, (k + 1) * 55, (i + 1) * 55, 55, 55, this);
                } else if (array[i][k].equals("S")) {
                    jlabes[i][k] = addJLabel(icon2, (k + 1) * 55, (i + 1) * 55, 55, 55, this);
                    Startx = i;
                    Starty = k;
                } else {
                    jlabes[i][k] = addJLabel(icon4, (k + 1) * 55, (i + 1) * 55, 55, 55, this);
                }
            }
        }

        setTitle("P L A Y");
        setBounds(100, 100, 940, 420);
        A1.setFont(new Font("Arial", 0, 17));
        A2.setFont(new Font("Arial", 0, 17));
        A3.setFont(new Font("Arial", 0, 17));
        A4.setFont(new Font("Arial", 0, 17));
        back.setFont(new Font("Arial", 0, 17));

    }
    int newStartx;
    int newStarty;

    Button A1 = addButton("up", 710, 100, 120, 60, this);
    Button A2 = addButton("down ", 710, 240, 120, 60, this);
    Button A3 = addButton("left", 650, 170, 120, 60, this);
    Button A4 = addButton("Right", 780, 170, 120, 60, this);
    Button back = addButton("Back", 800, 340, 100, 60, this);

    public void actions(Object source, String command) {
        if (source == back) {
            if (ST.equals("T")) {
                new Test3(testName);
                dispose();
            } else {
                dispose();
            }
        }
        if (source == A1) {//Up
            newStartx = Startx - 1;//new possition
            newStarty = Starty;
            //check 1: if new possition is in range
            if (!(newStartx >= 0 && newStartx < 5 && newStarty >= 0 && newStarty < 10)) {
                output("step is not acceptable;\nOut of range!");
            } else {
                if (array[newStartx][newStarty].equals("|")) {
                    output("step is not acceptable;\nthere ia a wall!");
                } else {
                    if (array[newStartx][newStarty].equals("E")) {
                        output("You win the game!");
                        if (ST.equals("T")) {
                            new Test3(testName);
                            dispose();
                        } else {
                            dispose();
                        }
                    } else {
                        jlabes[Startx][Starty].setIcon(null);
                        jlabes[Startx - 1][Starty].setIcon(icon2);
                        array[Startx][Starty] = " ";
                        array[Startx - 1][Starty] = "S";
                        Startx = Startx - 1;
                    }
                }
            }
        }

        if (source == A2) {//Down
            newStartx = Startx + 1;//new possition
            newStarty = Starty;
            //check 1: if new possition is in range
            if (!(newStartx >= 0 && newStartx < 5 && newStarty >= 0 && newStarty < 10)) {
                output("step is not acceptable.\nOut of range!");
            } else {
                if (array[newStartx][newStarty].equals("|")) {
                    output("step is not acceptable;\nthere ia a wall!");
                } else {
                    if (array[newStartx][newStarty].equals("E")) {
                        output("You win the game!");
                        new Test3(testName);
                        dispose();
                    } else {
                        jlabes[Startx][Starty].setIcon(null);
                        jlabes[Startx + 1][Starty].setIcon(icon2);
                        array[Startx][Starty] = " ";
                        array[Startx + 1][Starty] = "S";
                        Startx = Startx + 1;
                    }
                }
            }
        }

        if (source == A4) {//right
            newStartx = Startx;//new possition
            newStarty = Starty + 1;
            //check 1: if new possition is in range
            if (!(newStartx >= 0 && newStartx < 5 && newStarty >= 0 && newStarty < 10)) {
                output("step is not acceptable.\nOut of range!");
            } else {
                if (array[newStartx][newStarty].equals("|")) {
                    output("step is not acceptable;\nthere ia a wall!");
                } else {
                    if (array[newStartx][newStarty].equals("E")) {
                        output("You win the game!");
                        new Test3(testName);
                        dispose();
                    } else {
                        jlabes[Startx][Starty].setIcon(null);
                        jlabes[Startx][Starty + 1].setIcon(icon2);
                        array[Startx][Starty] = " ";
                        array[Startx][Starty + 1] = "S";
                        Starty = Starty + 1;
                    }
                }
            }
        }

        if (source == A3) {//left
            newStartx = Startx;//new possition
            newStarty = Starty - 1;
            //check 1: if new possition is in range
            if (!(newStartx >= 0 && newStartx < 5 && newStarty >= 0 && newStarty < 10)) {
                output("step is not acceptable.\nOut of range!");
            } else {
                if (array[newStartx][newStarty].equals("|")) {
                    output("step is not acceptable;\nthere ia a wall!");
                } else {
                    if (array[newStartx][newStarty].equals("E")) {
                        output("You win the game!");
                        new Test3(testName);
                        dispose();
                    } else {
                        jlabes[Startx][Starty].setIcon(null);
                        jlabes[Startx][Starty - 1].setIcon(icon2);
                        array[Startx][Starty] = " ";
                        array[Startx][Starty - 1] = "S";
                        Starty = Starty - 1;
                    }
                }
            }
        }
    }
}
