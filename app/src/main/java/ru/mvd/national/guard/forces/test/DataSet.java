package ru.mvd.national.guard.forces.test;

import android.content.Context;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class DataSet {

    private int SET_CAPACITY = 500;
    private int size;

    private DataField[] dataSet;


    public DataSet(Context context) throws IOException  {
        dataSet = new DataField[SET_CAPACITY];
        size = 0;
        loadFile(context, "const.txt");
        //loadFile(context, "fire.txt"); //TODO Set correct file encoding
        loadFile(context, "ovo.txt");
    }

    private void loadFile(Context context, String name) {
        try {
            InputStream inputStream = context.getAssets().open(name);
            if (inputStream != null) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String row;
                while ((row = bufferedReader.readLine()) != null) {
                    dataSet[size] = new DataField(row);
                    size++;
                }
                inputStream.close();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }


    public DataField getDataField(int i) {
        return dataSet[i];
    }

    public int getSize() {
        return size;
    }
}
