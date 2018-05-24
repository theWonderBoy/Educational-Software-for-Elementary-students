package Educational_Software;

import java.awt.*;
import java.io.IOException;
import java.io.RandomAccessFile;

public class studentT2 extends EasyApp {

    Label question = addLabel("", 50, 60, 500, 40, this);
    Label answer = addLabel("Answer:", 100, 150, 100, 40, this);
    TextField answerF = addTextField("", 200, 150, 200, 40, this);
    Button check = addButton("check", 100, 220, 100, 50, this);
    Label result = addLabel("", 240, 220, 300, 40, this);

    String testTitle;//choosen test title to open apropriate txt file
    String[][] test2Array = new String[20][2];//max 20 questions is possible to put in one test
    int countQuestions = 0;//teacher can in one test put not 20, but less questions; 
                           //thus, we need to count them
    int score = 0;
    int i = 0; //go through the array

    public studentT2(String testTitle) {
        setTitle("Test 2 - write the correct answer");
        setBounds(150, 150, 500, 320);
        question.setFont(new Font("Arial", 0, 18));
        question.setForeground(Color.getHSBColor(0.3f, 0.99f, 0.3f));
        answer.setFont(new Font("Arial", 0, 17));
        answerF.setFont(new Font("Arial", 0, 17));
        check.setFont(new Font("Arial", 0, 17));
        result.setFont(new Font("Arial", 0, 25));
        result.setForeground(Color.getHSBColor(0.3f, 0.99f, 0.3f));
        this.testTitle = testTitle;
        createArray();//create String array of questions+answer
        firstQuestion();//to set 1st question in window and to set next questions as well.
    }

    void createArray() {
        String testLine;
        try {
            RandomAccessFile test2F = new RandomAccessFile(testTitle + ".txt", "rw");
            while (test2F.getFilePointer() != test2F.length()) {
                testLine = test2F.readLine();
                //split line in 2 parts = question and answer
                int index1 = testLine.indexOf("=");
                test2Array[countQuestions][0] = testLine.substring(0, index1);
                test2Array[countQuestions][1] = testLine.substring(index1 + 1);
                countQuestions++;
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }

    void firstQuestion() { //i is an array veriable
        question.setText("Question N" + (i + 1) + ": " + test2Array[i][0]);
    }

    public void actions(Object source, String command) {
        if (source == check) {
            if (answerF.getText().equals(test2Array[i][1])) {
                score++;
                output("Correct Answer;");
                result.setText("Your Score = " + score);
            } else {
                output("Incorrect Answer;");
                result.setText("Your Score = " + score);
            }
            answerF.setText("");
            i++;
            if (i < countQuestions) {
                firstQuestion();
            } else {
                output("You finished!\n Your Score = " + score);
                dispose();
            }
        }
    }
}
