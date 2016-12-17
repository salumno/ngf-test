package ru.mvd.national.guard.forces.test;

import java.util.Arrays;
import java.util.Random;

/**
 * Created by salumno on 17.12.16.
 */

public class TestQuestionSet {

    private DataField[] questionSet;
    private int size = 60;
    private Random randomIndex;

    public TestQuestionSet(DataSet dataSet) {
        questionSet = new DataField[size];
        randomIndex = new Random();
        int dataSize = dataSet.getSize();
        boolean[] numbersCheckSet = new boolean[dataSize];
        Arrays.fill(numbersCheckSet, false);
        int currentIndex;
        for (int i = 0; i < size; i++) {
            do {
                currentIndex = randomIndex.nextInt(dataSize);
            } while (numbersCheckSet[currentIndex]);
            numbersCheckSet[currentIndex] = true;
            questionSet[i] = dataSet.getDataField(currentIndex);
        }
    }

    public DataField getQuestion(int index) {
        return questionSet[index];
    }
    public int getSize() {
        return size;
    }
}
