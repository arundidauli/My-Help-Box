package com.diggingquiz.myquiz.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tanuj yadav on 01/12/2017.
 */

public class QuestionAnswerData implements Parcelable {
    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getOp1() {
        return op1;
    }

    public void setOp1(String op1) {
        this.op1 = op1;
    }

    public String getOp2() {
        return op2;
    }

    public void setOp2(String op2) {
        this.op2 = op2;
    }

    public String getOp3() {
        return op3;
    }

    public void setOp3(String op3) {
        this.op3 = op3;
    }

    public String getCurrectAns() {
        return currectAns;
    }

    public void setCurrectAns(String currectAns) {
        this.currectAns = currectAns;
    }

    private String question;
    private String op1;
    private String op2;
    private String op3;
    private String currectAns;
    private int id;

    public String getUserSelectAns() {
        return userSelectAns;
    }

    public void setUserSelectAns(String userSelectAns) {
        this.userSelectAns = userSelectAns;
    }

    private String userSelectAns;
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }





    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(question);
        dest.writeString(op1);
        dest.writeString(op2);
        dest.writeString(op3);
        dest.writeInt(id);
        dest.writeString(currectAns);
        dest.writeString(userSelectAns);





    }
    public QuestionAnswerData(Parcel in) {
        question = in.readString();
        op1 = in.readString();
        op2 = in.readString();
        op3= in.readString();
        id=in.readInt();
       currectAns= in.readString();
        userSelectAns=in.readString();


    }
    public static final Creator<QuestionAnswerData> CREATOR = new Creator<QuestionAnswerData>() {
        public QuestionAnswerData createFromParcel(Parcel in) {
            return new QuestionAnswerData(in);
        }

        public QuestionAnswerData[] newArray(int size) {
            return new QuestionAnswerData[size];
        }
    };
    public QuestionAnswerData() {
    }
}
