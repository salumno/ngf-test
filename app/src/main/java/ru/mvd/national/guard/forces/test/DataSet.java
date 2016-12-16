package ru.mvd.national.guard.forces.test;

import android.util.Log;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;


/**
 * Created by salumno on 15.12.16.
 */

public class DataSet {

    private static final String TAG = "MyLog";

    private int SET_CAPACITY = 500;
    private int size = 0;

    private DataField[] dataSet = new DataField[SET_CAPACITY];

    public DataSet() throws FileNotFoundException {
        /*String cS = "Форма правления Российской Федерации:*#республиканская*монархическая*теократическая*конституционная";
        dataSet[size] = new DataField(cS);
        size++;
        cS = "По соотношению наименований государства:*#Российская Федерация и Россия равнозначны*Российская Федерация – самое корректное*Россия – самое корректное*Русь, Российская империя, СССР, Российская Федерация и Россия равнозначны";
        dataSet[size] = new DataField(cS);
        size++;*/

    }

    public DataField getDataField(int i) {
        return dataSet[i];
    }

    public int getSize() {
        return SET_CAPACITY;
    }
}
