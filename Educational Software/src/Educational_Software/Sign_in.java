package Educational_Software;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

class Sign_in extends EasyApp {

    Label username = addLabel("Username", 30, 50, 150, 40, this);
    Label password = addLabel("Password", 30, 120, 150, 40, this);
    TextField Username = addTextField("", 200, 50, 175, 40, this);
    TextField Password = addTextField("", 200, 120, 175, 40, this);
    Button Sign_in = addButton("Sign in", 450, 110, 120, 60, this);
    Button Close = addButton("Close", 450, 180, 120, 60, this);

    Sign_in() {
        setTitle("Sign in");//Setting the name of window
        setBounds(150, 150, 600, 270);//Setting the size of Window
        username.setFont(new Font("Arial", 0, 20));//Size of Text
        password.setFont(new Font("Arial", 0, 20));//Size of Text
        Username.setFont(new Font("Arial", 0, 17));
        Password.setFont(new Font("Arial", 0, 17));
        Sign_in.setFont(new Font("Arial", 0, 17));
        Close.setFont(new Font("Arial", 0, 17));
    }
//Buttons  Functionality 

    public void actions(Object source, String command) {
        if (source == Close) {
            dispose();
        }
        if (source == Sign_in) {
            try {
                Signin();
            } catch (IOException ex) {
                Logger.getLogger(Sign_in.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    private void Signin() throws IOException {
        String str1 = Username.getText();//String will be compared to already created value 
        String str2 = Password.getText();//String will be compared to already created value
        File file = new File("Users.txt");
        Scanner Scan = new Scanner(file);
        while (Scan.hasNextLine()) {
            String str3 = Scan.nextLine();
            String str4 = str3.substring(0, str3.indexOf("/"));
            String str5 = str3.substring(str3.indexOf("/") + 1, str3.length());
            if (str1.equals(str4) && str2.equals(str5)) {
                new Create();
                dispose();
                break;
            }
            if (Scan.hasNextLine() == false) {
                output("Username or Password is Wrong!!!\nTry Again!");
            }
        }
    }
}
