package ru.mvd.national.guard.forces.test;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by salumno on 24.12.16.
 */

public class DataFieldResult implements Parcelable {

    private String question;
    private String ans1;
    private String ans2;
    private String ans3;
    private String ans4;
    private String rightAns;
    private String userIncorrectAnswer;


    public DataFieldResult(DataField dataField, String userIncorrectAnswer) {
        question = dataField.getQuestion();
        ans1 = dataField.getAns1();
        ans2 = dataField.getAns2();
        ans3 = dataField.getAns3();
        ans4 = dataField.getAns4();
        rightAns = dataField.getRightAns();
        this.userIncorrectAnswer = userIncorrectAnswer;
    }

    public String getUserIncorrectAnswer() {
        return userIncorrectAnswer;
    }

    public String toString() {
        String s = this.question + "\n" + "1. " + this.ans1 + "\n" + "2. " + this.ans2 + "\n" + "3. " + this.ans3 + "\n" + "4. " + this.ans4;
        return s;
    }

    protected DataFieldResult(Parcel in) {
        question = in.readString();
        ans1 = in.readString();
        ans2 = in.readString();
        ans3 = in.readString();
        ans4 = in.readString();
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
        parcel.writeString(ans1);
        parcel.writeString(ans2);
        parcel.writeString(ans3);
        parcel.writeString(ans4);
        parcel.writeString(rightAns);
        parcel.writeString(userIncorrectAnswer);
    }
}
