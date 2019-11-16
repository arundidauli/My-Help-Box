package com.diggingquiz.myquiz.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tanuj yadav on 01/12/2017.
 */

public class StatementListData implements Parcelable {
    private String remark;
    private String walletWithdrawalAMount;
    private String tranjectionMode;
    private String tranjectionDate;

    public String getQuizeName() {
        return quizeName;
    }

    public void setQuizeName(String quizeName) {
        this.quizeName = quizeName;
    }

    public int getQuizStatus() {
        return quizStatus;
    }

    public void setQuizStatus(int quizStatus) {
        this.quizStatus = quizStatus;
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    private String quizeName;
    private int quizStatus;
    private int id;
    private String balance;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public String getWalletWithdrawalAMount() {
        return walletWithdrawalAMount;
    }

    public void setWalletWithdrawalAMount(String walletWithdrawalAMount) {
        this.walletWithdrawalAMount = walletWithdrawalAMount;
    }

    public String getTranjectionMode() {
        return tranjectionMode;
    }

    public void setTranjectionMode(String tranjectionMode) {
        this.tranjectionMode = tranjectionMode;
    }

    public String getTranjectionDate() {
        return tranjectionDate;
    }

    public void setTranjectionDate(String tranjectionDate) {
        this.tranjectionDate = tranjectionDate;
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
        dest.writeString(remark);
        dest.writeString(walletWithdrawalAMount);
        dest.writeString(walletWithdrawalAMount);
        dest.writeInt(id);
        dest.writeString(tranjectionMode);
        dest.writeString(tranjectionDate);
        dest.writeString(quizeName);
        dest.writeInt(quizStatus);
        dest.writeString(balance);






    }
    public StatementListData(Parcel in) {
        remark = in.readString();
        walletWithdrawalAMount = in.readString();
        walletWithdrawalAMount = in.readString();
        id=in.readInt();
        tranjectionMode= in.readString();
        tranjectionDate= in.readString();

        quizeName= in.readString();
        quizStatus= in.readInt();
        balance= in.readString();



    }
    public static final Creator<StatementListData> CREATOR = new Creator<StatementListData>() {
        public StatementListData createFromParcel(Parcel in) {
            return new StatementListData(in);
        }

        public StatementListData[] newArray(int size) {
            return new StatementListData[size];
        }
    };
    public StatementListData() {
    }
}
