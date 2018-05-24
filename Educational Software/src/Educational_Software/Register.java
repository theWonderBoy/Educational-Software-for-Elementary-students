package Educational_Software;

import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;

class Register extends EasyApp {

    Label username = addLabel("Username", 30, 90, 150, 40, this);
    Label password = addLabel("Password", 30, 150, 150, 40, this);
    TextField Username = addTextField("", 200, 90, 175, 30, this);
//This Button will be used to get input form user, the information will be used to creat account in the program
    TextField Password = addTextField("", 200, 150, 175, 30, this);
//This button will be used to get the password which user will input and it will be used for to create his account 
    Button Register = addButton("Register", 450, 110, 120, 60, this);
    Button Close = addButton("Close", 450, 190, 120, 60, this);
//Close Button is to exit the windows and to go back to main window

    Register() {
        setTitle("Register Window");//Setting the name of window
        setBounds(150, 150, 600, 280);//Setting the size of Window
        username.setFont(new Font("Arial", 0, 20));//Size of Text
        password.setFont(new Font("Arial", 0, 20));//Size of Text
        Username.setFont(new Font("Arial", 0, 18));
        Password.setFont(new Font("Arial", 0, 18));
        Register.setFont(new Font("Arial", 0, 18));
        Close.setFont(new Font("Arial", 0, 18));
   }
//Buttons Main Functionality 

    public void actions(Object source, String command) {
        if (source == Close) {
            dispose();
        }
        if (source == Register) {
            register();
        }
    }
//Method Main body 

    private void register() {
        String str1 = Username.getText();//input from User, which will be used in creating new account
        String str2 = Password.getText();//input from User, which will be used in creating new account
        //Testing in Case the user left one Text field or both empaty 
        boolean flag = true;
        if (str1.equals("") || str2.equals("")) {
            output("Please Make Sure you filled the Username or Password ");
        } //If the User filled All textfield, The String will be writtin to file to Save it 
        else {
            try {
                RandomAccessFile Users = new RandomAccessFile("Users.txt", "rw");
                while (Users.getFilePointer() != Users.length()) {
                    String str = Users.readLine();
                    if (str.substring(0, str.indexOf("/")).equals(str1)) {
                        output("You are already registered!");
                        flag = false;
                    }
                }
                
                if (flag) {
                    Users.writeBytes(str1 + "/" + str2 + "\n");
                    output("You have Succsfully registered your account ");
                    Username.setText("");
                    Password.setText("");
                }
            } catch (IOException e) {
                e.getMessage();
            }
        }
    }
}
