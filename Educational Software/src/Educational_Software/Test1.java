package Educational_Software;

import java.awt.*;
import java.io.*;

class Test1 extends EasyApp {

    List Questions = addList("", 100, 50, 700, 300, this);
    Label A1 = addLabel("Write The Question ", 200, 370, 200, 30, this);
    Label A2 = addLabel("Write First Choice", 200, 400, 200, 30, this);
    Label A3 = addLabel("Write Second Choice", 200, 430, 200, 30, this);
    Label A4 = addLabel("Write Third Choice", 200, 460, 200, 30, this);
    Label A5 = addLabel("Write Fourth Choice", 200, 490, 200, 30, this);
    Label A6 = addLabel("Choose the Correct Answer", 120, 520, 250, 30, this);
    TextField Text = addTextField("", 400, 370, 400, 26, this);
    TextField choice1 = addTextField("", 400, 400, 400, 26, this);
    TextField choice2 = addTextField("", 400, 430, 400, 26, this);
    TextField choice3 = addTextField("", 400, 460, 400, 26, this);
    TextField choice4 = addTextField("", 400, 490, 400, 26, this);
    Choice Answer = addChoice("Nothing", 400, 520, 400, 26, this);
    Button Refresh = addButton("Refresh", 440, 560, 120, 40, this);
    Button Add = addButton("Add", 560, 560, 120, 40, this);
    Button Edit = addButton("Edit", 680, 560, 120, 40, this);
    Button close = addButton("Close", 750, 630, 120, 50, this);
    Button SaveEdit = new Button();
    String testName;

    Test1(String name) {
        testName = name;
        setTitle("Creating Quiz");
        setBounds(100, 100, 900, 720);
        A1.setFont(new Font("Arial", 0, 17));
        A2.setFont(new Font("Arial", 0, 17));
        A3.setFont(new Font("Arial", 0, 17));
        A4.setFont(new Font("Arial", 0, 17));
        A5.setFont(new Font("Arial", 0, 17));
        A6.setFont(new Font("Arial", 0, 17));
        Questions.setFont(new Font("Arial", 0, 17));
        Add.setFont(new Font("Arial", 0, 15));
        Edit.setFont(new Font("Arial", 0, 15));
        close.setFont(new Font("Arial", 0, 15));
        Refresh.setFont(new Font("Arial", 0, 15));
        SaveEdit.setFont(new Font("Arial", 0, 15));

        newRefresh();//read all questions from file and display in the Questions List Window
    }

    public void actions(Object source, String command) {
        if (source == Refresh) { //only for answer choice
            Answer.removeAll();
            Answer.add(choice1.getText());
            Answer.add(choice2.getText());
            Answer.add(choice3.getText());
            Answer.add(choice4.getText());
        }
        if (source == Add) {
            Add();
            newRefresh();
        }
        if (source == Edit) {
            Edit(); //edit already created questions/records
        }
        if (source == close) {
            dispose();
        }
        if (source == SaveEdit) {
            SaveEditQuestion();
        }
    }

    private void Add() {
        String str1 = Text.getText();
        String str2 = choice1.getText();
        String str3 = choice2.getText();
        String str4 = choice3.getText();
        String str5 = choice4.getText();
        String str6;
        if (str1.equals("") || str2.equals("") || str3.equals("") || str4.equals("") || str5.equals("")) {
            output("Make Sure you fill all fields");
        } else {
            str6 = Answer.getSelectedItem();
            if (str6.equals("Nothing")) {
                output("click on Refresh button to choose correct answer!");
            } else {
                try {
                    RandomAccessFile questionF = new RandomAccessFile(testName + ".txt", "rw");
                    questionF.seek(questionF.length());
                    questionF.writeBytes(str1 + "/" + str2 + "/" + str3 + "/" + str4 + "/" + str5 + "/Answer/" + str6 + "\n");
                } catch (IOException e) {
                    e.getMessage();
                }
                clean();
            }
        }
    }

    void clean() { //delete information from all fields to be read for next input
        Text.setText("");
        choice1.setText("");
        choice2.setText("");
        choice3.setText("");
        choice4.setText("");
        Answer.removeAll();
        Answer.add("Nothing");
    }

    String[] qArray = new String[20]; //to upload questions from file in array for later changes
    int countQuestion = 0; //count questions

    void questionArray() {
        try {
            RandomAccessFile question = new RandomAccessFile(testName + ".txt", "rw");
            while (question.length() != question.getFilePointer()) {
                qArray[countQuestion] = question.readLine();
                countQuestion++;
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    int editQuestionIndex;

    private void Edit() {
        questionArray();//work with array is better 
        SaveEdit = addButton("Save Changes", 500, 630, 160, 50, this);
        String change = Questions.getSelectedItem();
        for (int i = 0; i < countQuestion; i++) {
            if (change.equals(qArray[i])) {
                int index1 = change.indexOf("/");
                int index2 = change.indexOf("/", index1 + 1);
                int index3 = change.indexOf("/", index2 + 1);
                int index4 = change.indexOf("/", index3 + 1);
                int index5 = change.indexOf("/", index4 + 1);
                Text.setText(change.substring(0, index1));
                choice1.setText(change.substring(index1 + 1, index2));
                choice2.setText(change.substring(index2 + 1, index3));
                choice3.setText(change.substring(index3 + 1, index4));
                choice4.setText(change.substring(index4 + 1, index5));
                editQuestionIndex = i;
                break;
            }
        }
    }

    void SaveEditQuestion() {
        String str1 = Text.getText();
        String str2 = choice1.getText();
        String str3 = choice2.getText();
        String str4 = choice3.getText();
        String str5 = choice4.getText();
        String str6;
        if (str1.equals("") || str2.equals("") || str3.equals("") || str4.equals("") || str5.equals("")) {
            output("Make Sure you fill all fields");
        } else {
            str6 = Answer.getSelectedItem();
            if (str6.equals("Nothing")) {
                output("click on Refresh button to choose correct answer!");
            } else {

                qArray[editQuestionIndex] = str1 + "/" + str2 + "/" + str3 + "/" + str4 + "/" + str5 + "/Answer/" + str6;
                clean();

                //Update file
                try {
                    RandomAccessFile question = new RandomAccessFile(testName + ".txt", "rw");
                    question.setLength(0);
                    for (int i = 0; i < countQuestion; i++) {
                        question.writeBytes(qArray[i] + "\n");
                    }
                } catch (IOException e) {
                    e.getMessage();
                }
                newRefresh();
            }
        }
    }

    private void newRefresh() {
        //read all questions from file and display in the Questions List Window
        try {
            RandomAccessFile Quiz = new RandomAccessFile(testName + ".txt", "rw");
            Questions.removeAll();
            Questions.add("Question  List:");
            while (Quiz.length() != Quiz.getFilePointer()) {
                Questions.add(Quiz.readLine());
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
