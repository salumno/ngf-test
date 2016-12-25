package ru.mvd.national.guard.forces.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by salumno on 24.12.16.
 */

public class DataFieldResult implements Parcelable {

    private String question;
    private String rightAns;
    private String userIncorrectAnswer;


    public DataFieldResult(DataField dataField, String userIncorrectAnswer) {
        question = dataField.getQuestion();
        rightAns = dataField.getRightAns();
        this.userIncorrectAnswer = userIncorrectAnswer;
    }

    public String getUserIncorrectAnswer() {
        return userIncorrectAnswer;
    }

    public String getQuestion() {
        return question;
    }

    public String getRightAns() {
        return rightAns;
    }

    public String toString() {
        String s = this.question + "\n";
        return s;
    }

    protected DataFieldResult(Parcel in) {
        question = in.readString();
        rightAns = in.readString();
        userIncorrectAnswer = in.readString();
    }

    public static final Creator<DataFieldResult> CREATOR = new Creator<DataFieldResult>() {
        @Override
        public DataFieldResult createFromParcel(Parcel in) {
            return new DataFieldResult(in);
        }

        @Override
        public DataFieldResult[] newArray(int size) {
            return new DataFieldResult[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(question);
        parcel.writeString(rightAns);
        parcel.writeString(userIncorrectAnswer);
    }
}
