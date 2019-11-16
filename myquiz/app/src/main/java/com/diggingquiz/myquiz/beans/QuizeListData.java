package com.diggingquiz.myquiz.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tanuj yadav on 01/12/2017.
 */

public class QuizeListData implements Parcelable {

    public String getQuizeName() {
        return quizeName;
    }

    public void setQuizeName(String quizeName) {
        this.quizeName = quizeName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getWinningamount() {
        return winningamount;
    }

    public void setWinningamount(double winningamount) {
        this.winningamount = winningamount;
    }

    private String quizeName;
    private String date;
    private double winningamount;
      private int id;
    private long expireTime;
    private String quizeDateTime;

    public int getIsPass() {
        return isPass;
    }

    public void setIsPass(int isPass) {
        this.isPass = isPass;
    }

    private int isPass;


    public long getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    public String getQuizeDateTime() {
        return quizeDateTime;
    }

    public void setQuizeDateTime(String quizeDateTime) {
        this.quizeDateTime = quizeDateTime;
    }




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
        dest.writeString(quizeName);
        dest.writeString(date);
        dest.writeDouble(winningamount);
          dest.writeInt(id);
          dest.writeString(quizeDateTime);
          dest.writeLong(expireTime);
          dest.writeInt(isPass);






    }
    public QuizeListData(Parcel in) {
        quizeName = in.readString();
        date = in.readString();
        winningamount = in.readDouble();

        id=in.readInt();
        quizeDateTime=in.readString();
        expireTime=in.readLong();
        isPass=in.readInt();



    }
    public static final Creator<QuizeListData> CREATOR = new Creator<QuizeListData>() {
        public QuizeListData createFromParcel(Parcel in) {
            return new QuizeListData(in);
        }

        public QuizeListData[] newArray(int size) {
            return new QuizeListData[size];
        }
    };
    public QuizeListData() {
    }
}
