package com.diggingquiz.myquiz.Utils;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Tanuj yadav on 18/03/2018.
 */

public class HomeItemData implements Parcelable {
    private int id;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    private String serviceName;
    private String imgurl;
    private String rating;

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
        dest.writeString(serviceName);
        dest.writeString(imgurl);
        dest.writeString(rating);
        dest.writeInt(id);


    }
    public HomeItemData(Parcel in) {
        serviceName = in.readString();
        imgurl = in.readString();
        rating = in.readString();
        id=in.readInt();


    }
    public static final Creator<HomeItemData> CREATOR = new Creator<HomeItemData>() {
        public HomeItemData createFromParcel(Parcel in) {
            return new HomeItemData(in);
        }

        public HomeItemData[] newArray(int size) {
            return new HomeItemData[size];
        }
    };
    public HomeItemData() {
    }
}

