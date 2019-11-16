package com.diggingquiz.myquiz.beans;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tanuj yadav on 01/12/2017.
 */

public class WalletHistoryData implements Parcelable {
    private String remark;
    private String walletAddAMount;
    private String walletWithdrawalAMount;
    private String tranjectionMode;
    private String tranjectionDate;
    private int id;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getWalletAddAMount() {
        return walletAddAMount;
    }

    public void setWalletAddAMount(String walletAddAMount) {
        this.walletAddAMount = walletAddAMount;
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




    }
    public WalletHistoryData(Parcel in) {
        remark = in.readString();
        walletWithdrawalAMount = in.readString();
        walletWithdrawalAMount = in.readString();
        id=in.readInt();
        tranjectionMode= in.readString();
        tranjectionDate= in.readString();


    }
    public static final Creator<WalletHistoryData> CREATOR = new Creator<WalletHistoryData>() {
        public WalletHistoryData createFromParcel(Parcel in) {
            return new WalletHistoryData(in);
        }

        public WalletHistoryData[] newArray(int size) {
            return new WalletHistoryData[size];
        }
    };
    public WalletHistoryData() {
    }
}
