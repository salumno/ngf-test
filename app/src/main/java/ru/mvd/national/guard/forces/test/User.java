package ru.mvd.national.guard.forces.test;

import java.util.ArrayList;

/**
 * Created by salumno on 19.12.16.
 */

public class User {

    private int rightAnswerCount;
    private TestQuestionSet testQuestionSet;
    private ArrayList<DataField> incorrectAnswerSet;


    public User(DataSet dataSet) {
        testQuestionSet = new TestQuestionSet(dataSet);
        rightAnswerCount = 0;
        incorrectAnswerSet = new ArrayList<>();
    }

    public DataField getTestQuestion(int index) {
        return testQuestionSet.getQuestion(index);
    }

    public int getSize() {
        return testQuestionSet.getSize();
    }

    public int getRightAnswerCount() {
        return rightAnswerCount;
    }

    public void setRightAnswerCount(int rightAnswer) {
        this.rightAnswerCount = rightAnswer;
    }

    public void addIncorrectAnswerToList(DataField incorrectAnswer) {
        incorrectAnswerSet.add(incorrectAnswer);
    }

}
