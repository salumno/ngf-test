package ru.mvd.national.guard.forces.test;

import java.util.ArrayList;

public class User {

    private String userName;
    private int rightAnswerCount;
    private TestQuestionSet testQuestionSet;
    private ArrayList<DataFieldResult> incorrectAnswerSet;


    public User(DataSet dataSet, String userName) {
        testQuestionSet = new TestQuestionSet(dataSet);
        rightAnswerCount = 0;
        incorrectAnswerSet = new ArrayList<>();
        this.userName = userName;
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

    public String getUserName() {
        return userName;
    }

    public ArrayList<DataFieldResult> getIncorrectAnswerSet() {
        return incorrectAnswerSet;
    }

    public void addIncorrectAnswerToList(int index, String userAnswer) {
        DataFieldResult dataFieldResult = new DataFieldResult(testQuestionSet.getQuestion(index), userAnswer);
        incorrectAnswerSet.add(dataFieldResult);
    }
}
