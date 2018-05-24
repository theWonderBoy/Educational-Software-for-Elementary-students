package Educational_Software;

import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;

class nametest1 extends EasyApp {

    Label A1 = addLabel("New Name", 50, 50, 150, 40, this);
    TextField name = addTextField("", 200, 50, 175, 40, this);
    List testnames = addList("", 80, 120, 250, 200, this);
    Button Name = addButton("Create", 430, 50, 120, 60, this);
    Button Open = addButton("Open", 430, 150, 120, 60, this);
    Button Close = addButton("Close", 430, 230, 120, 60, this);

    nametest1() {
        setTitle("Naming");//Setting the name of window
        setBounds(100, 100, 600, 350);//Setting the size of Window
        A1.setFont(new Font("Arial", 0, 17));//Size of Text
        Name.setFont(new Font("Arial", 0, 17));
        Close.setFont(new Font("Arial", 0, 17));
        Open.setFont(new Font("Arial", 0, 17));
        name.setFont(new Font("Arial", 0, 17));
        testnames.setFont(new Font("Arial", 0, 17));
        List();
    }
    String testName;

    public void actions(Object source, String command) {
        if (source == Close) {
            dispose();
        }
        if (source == Name) {
            Name();
        }
        if (source == Open) {
            testName = testnames.getSelectedItem();
            if (testName == null) {
                output("Select the title");
            } else {
                new Test1(testName);
                if (testName == null) {
                    dispose();
                }
            }
        }
    }

    void List() { //already created tests' names will be displayed in List Window
        testnames.removeAll();
        try {
            RandomAccessFile listTest = new RandomAccessFile("listtest.txt", "rw");
            while (listTest.getFilePointer() != listTest.length()) {
                testnames.add(listTest.readLine());
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    void Name() {
        boolean flag = true;
        if (name.getText().equals("")) {
            output("Please Name the Test ");
        } else {
            try {
                RandomAccessFile listTest = new RandomAccessFile("listtest.txt", "rw");
                while (listTest.getFilePointer() != listTest.length()) {
                    if (listTest.readLine().equals(name.getText())) {
                        flag = false;
                        break;
                    }
                }
                if (flag) {
                    listTest.writeBytes(name.getText() + "\n");
                } else {
                    output(" Change the name;");
                }
            } catch (IOException e) {
                e.getMessage();
            }
            List();
            name.setText("");
        }
    }
}
