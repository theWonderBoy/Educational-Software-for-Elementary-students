package Educational_Software;

import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class studentT1 extends EasyApp {

    Label question = addLabel("", 50, 50, 400, 40, this);
    Label A1 = addLabel("", 50, 100, 500, 40, this);
    Label A2 = addLabel("", 50, 150, 500, 40, this);
    Label A3 = addLabel("", 50, 200, 500, 40, this);
    Label A4 = addLabel("", 50, 250, 500, 40, this);
    Choice answer = addChoice("1|2|3|4", 70, 310, 50, 50, this);
    Button check = addButton("check", 200, 310, 100, 50, this);
    Label result = addLabel("", 340, 310, 200, 40, this);

    String testTitle; //choosen test title to open apropriate txt file
    String[][] test1Array = new String[20][6];//max 20 questions is possible to put in one test
    String correctAnswer;//count correct answers
    int countQuestions = 0;//teacher can in one test put not 20, but less questions; 
                           //thus, we need to count them
    int score = 0;
    int i = 0; //go through the array

    public studentT1(String testTitle) {
        setTitle("Test 1 - choose the correct answer");
        setBounds(150, 150, 560, 420);
        question.setFont(new Font("Arial", 0, 18));
        question.setForeground(Color.getHSBColor(0.3f, 0.99f, 0.3f));
        A1.setFont(new Font("Arial", 0, 17));
        A2.setFont(new Font("Arial", 0, 17));
        A3.setFont(new Font("Arial", 0, 17));
        A4.setFont(new Font("Arial", 0, 17));
        check.setFont(new Font("Arial", 0, 17));
        answer.setFont(new Font("Arial", 0, 17));
        result.setFont(new Font("Arial", 0, 25));
        result.setForeground(Color.getHSBColor(0.3f, 0.99f, 0.3f));
        this.testTitle = testTitle;
        createArray(); //create String array of questions+answers+correct answer
        firstQuestion(); //to set 1st question in window and to set next questions as well.
    }

    void firstQuestion() { // at the beggining i=0; after each question, i=i+1
        question.setText("Question N" + (i + 1) + ": " + test1Array[i][0]);
        A1.setText("answer 1: " + test1Array[i][1]);
        A2.setText("answer 2: " + test1Array[i][2]);
        A3.setText("answer 3: " + test1Array[i][3]);
        A4.setText("answer 4: " + test1Array[i][4]);
        correctAnswer = test1Array[i][5];
    }

    void createArray() {
        String testLine;
        try {
            RandomAccessFile test1F = new RandomAccessFile(testTitle + ".txt", "rw");
            while (test1F.getFilePointer() != test1F.length()) {
                testLine = test1F.readLine();
                //split line in 7 parts
                int index1 = testLine.indexOf("/");
                int index2 = testLine.indexOf("/", index1 + 1);
                int index3 = testLine.indexOf("/", index2 + 1);
                int index4 = testLine.indexOf("/", index3 + 1);
                int index5 = testLine.indexOf("/", index4 + 1);
                int index6 = testLine.indexOf("/", index5 + 1);
                test1Array[countQuestions][0] = testLine.substring(0, index1);
                test1Array[countQuestions][1] = testLine.substring(index1 + 1, index2);
                test1Array[countQuestions][2] = testLine.substring(index2 + 1, index3);
                test1Array[countQuestions][3] = testLine.substring(index3 + 1, index4);
                test1Array[countQuestions][4] = testLine.substring(index4 + 1, index5);
                if (testLine.substring(index6 + 1).equals(test1Array[countQuestions][1])) {
                    test1Array[countQuestions][5] = "1";
                } else if (testLine.substring(index6 + 1).equals(test1Array[countQuestions][2])) {
                    test1Array[countQuestions][5] = "2";
                } else if (testLine.substring(index6 + 1).equals(test1Array[countQuestions][3])) {
                    test1Array[countQuestions][5] = "3";
                } else {
                    test1Array[countQuestions][5] = "4";
                }
                countQuestions++;  //how many questions (lines) are written in the file
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public void actions(Object source, String command) {
        if (source == check) {//students answer is correct or not
            if (answer.getSelectedItem().equals(correctAnswer)) {
                score++;
                output("Correct Answer;");
                result.setText("Score = " + score);
            } else {
                output("Incorrect Answer;");
                result.setText("Score = " + score);
            }
            i++;
            if (i < countQuestions) {
                firstQuestion(); 
           //because i is variable, this method sets new questions from array
            } else {
                output("You finished!\n Your Score = " + score);
                dispose();
            }
        }
    }
}
