package Educational_Software;

import java.awt.*;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Main extends EasyApp { 
//Extending the Class to EasyApp which I will use to create graphics 

    public static void main(String[] args) {
        new Main();
    }
    // Intializing Buttons for the first Windows
    Button Exit = addButton("Exit", 420, 350, 120, 60, this);//for Closing the Windows
    Button Register = addButton("Register",40, 240, 120, 60, this);// to create account
    Button Sign_in = addButton("Sign in", 40, 170, 120, 60, this);// to log in with already created account
    Button Start = addButton("Start", 40, 100, 120, 60, this);// The function of this button is the move to second windows where user choose which quiz he want to do 
    ImageIcon icon1 = new ImageIcon(getClass().getResource("pic1.jpg"));//Adding image, since the Teacher asked me to make the program look Friendly !!
    JLabel pic1 = addJLabel(icon1, 200, 50, 300, 300, this);// Setting the place and size of image
    Label CR = addLabel(" @ Copyright Mahmoud", 50, 380, 280, 50, this);// CopyRight

    public Main() {
        setTitle("Main Window");// Naming the Main Window
        setBounds(100, 100, 600, 450); //  Main window size
        Register.setFont(new Font("Arial", 0, 17));
        Sign_in.setFont(new Font("Arial", 0, 17));
        Start.setFont(new Font("Arial", 0, 17));
        Exit.setFont(new Font("Arial", 0, 17));
    }
    //Buttons Functionality 
    public void actions(Object source, String command) {
        if (source == Exit) {
            System.exit(0);
        }
        if (source == Register) {
            new Register(); 
        }
        if (source == Sign_in) {
            new Sign_in();  
        }
        if (source == Start) {
            new Start();    
        }
    }
}
