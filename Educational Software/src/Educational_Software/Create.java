package Educational_Software;

import java.awt.Button;
import java.awt.Font;

class Create extends EasyApp {

    Button test1 = addButton("Test 1", 50, 50, 130, 80, this);
    Button test2 = addButton("Test 2", 220, 50, 130, 80, this);
    Button test3 = addButton("Test 3", 390, 50, 130, 80, this);
    Button Close = addButton("Close", 220, 160, 130, 50, this);

    Create() {
        setTitle("Create - Choose Test");//Setting the name of window
        setBounds(150, 150, 560, 250);//Setting the size of Window
        test1.setFont(new Font("Arial", 0, 17));
        test2.setFont(new Font("Arial", 0, 17));
        test3.setFont(new Font("Arial", 0, 17));
        Close.setFont(new Font("Arial", 0, 17));
    }

    public void actions(Object source, String command) {
        if (source == Close) {
            dispose();
        }
        if (source == test1) {
            new nametest1(); //multiple choice questions Test
            dispose();
        }
        if (source == test2) {
            new nametest2(); //Questions with one answers
            dispose();
        }
        if (source == test3) {
            new nametest3();//graphic tests
            dispose();
        }
    }
}
