package ru.mvd.national.guard.forces.test;

/**
 * Created by salumno on 19.12.16.
 */

public class User {
    private int rightAnswer;
    private TestQuestionSet testQuestionSet;
    private DataField[] incorrectAnswer;
    private int incorrectAnswerArraySize;

    public User(DataSet dataSet) {
        testQuestionSet = new TestQuestionSet(dataSet);
        rightAnswer = 0;
        incorrectAnswer = new DataField[testQuestionSet.getSize()];
        incorrectAnswerArraySize = 0;
    }

    public DataField getTestQuestion(int index) {
        return testQuestionSet.getQuestion(index);
    }

    public int getSize() {
        return testQuestionSet.getSize();
    }
}
