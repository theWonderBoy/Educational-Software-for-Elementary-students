package Educational_Software;
import java.awt.*;
import java.io.*;

public class Test2 extends EasyApp {
    List Questions = addList("", 50, 50, 350, 300, this);
    Label QuestionL = addLabel("Type Question", 50, 370, 120, 40, this);
    Label AnswerL = addLabel("Type answer", 50, 420, 120, 40, this);
    TextField Question = addTextField("", 180, 370, 350, 40, this);
    TextField Answer = addTextField("", 180, 420, 350, 40, this);
    Button Refresh = addButton("Refresh", 430, 200, 100, 60, this);
    Button Add = addButton("Add", 100, 510, 120, 60, this);
    Button Delete = addButton("Delete", 240, 510, 120, 60, this);
    Button close = addButton("Close", 400, 510, 120, 60, this);
    String testName;
    
public Test2 (String name) {
        setTitle("Math Quiz");
        setBounds(100, 100, 600, 600);
        testName = name;
        Question.setFont(new Font("Arial", 0, 17));
        Answer.setFont(new Font("Arial", 0, 17));
        Add.setFont(new Font("Arial", 0, 17));
        Delete.setFont(new Font("Arial", 0, 17));
        close.setFont(new Font("Arial", 0, 17));
        Refresh.setFont(new Font("Arial", 0, 17));
        QuestionL.setFont(new Font("Arial", 0, 17));
        AnswerL.setFont(new Font("Arial", 0, 17));
        Questions.setFont(new Font("Arial", 0, 17));
        Refresh();
    }
public void actions(Object source, String command) {
        if (source == Add) {
            Add();
        }
        if (source == Delete) {
            Delete();
        }
        if (source == Refresh) {
            Refresh();
        }
        if (source == close) {
            dispose();
        }
    }
    
    private void Add() {
        try {
            RandomAccessFile MathQuiz = new RandomAccessFile(testName + ".txt", "rw");
            MathQuiz.seek(MathQuiz.length());
            MathQuiz.writeBytes(Question.getText() + "=" + Answer.getText() + "\n");
            Question.setText("");
            Answer.setText("");
            
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    String[] questionArray = new String[20];
    int questionsNumber;
    
    void QuestionArray() {
        questionsNumber = 0;
        try {
            RandomAccessFile MathQuiz = new RandomAccessFile(testName + ".txt", "rw");
            while (MathQuiz.length() != MathQuiz.getFilePointer()) {
                questionArray[questionsNumber] = MathQuiz.readLine();
                questionsNumber++;
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
    
    void Delete() {
        String str = Questions.getSelectedItem();
        QuestionArray();
        
        try {
            RandomAccessFile MathQuiz = new RandomAccessFile(testName + ".txt", "rw");
            MathQuiz.setLength(0);
            for (int i = 0; i < questionsNumber; i++) {
                if (!questionArray[i].equals(str)) {
                    MathQuiz.writeBytes(questionArray[i] + "\n");
                }
            }
        } catch (IOException e) {
            e.getMessage();
        }
   }
    
    private void Refresh() {
        try {
            RandomAccessFile Mathquiz = new RandomAccessFile(testName + ".txt", "rw");
            Questions.removeAll();
            while (Mathquiz.length() != Mathquiz.getFilePointer()) {
                Questions.add(Mathquiz.readLine());
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
