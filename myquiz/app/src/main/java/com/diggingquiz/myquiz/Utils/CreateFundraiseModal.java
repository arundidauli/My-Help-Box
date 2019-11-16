package com.diggingquiz.myquiz.Utils;

import android.graphics.Bitmap;
import android.net.Uri;

import java.io.File;

/**
 * Created by Tanuj yadav on 01/05/2018.
 */

public class CreateFundraiseModal {
    private Uri mFile;
    private Bitmap mBitmap;
    private File imgFile;
    private boolean image;
    private boolean isCamera;
    private File thumbnail;

    ///////
    private String thumbnailString;
    private String id;
    private String campaign_id;
    private String media_type;
    private String video;

    public boolean isCamera() {
        return isCamera;
    }

    public void setCamera(boolean camera) {
        isCamera = camera;
    }



    public String getThumbnailString() {
        return thumbnailString;
    }

    public void setThumbnailString(String thumbnailString) {
        this.thumbnailString = thumbnailString;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCampaign_id() {
        return campaign_id;
    }

    public void setCampaign_id(String campaign_id) {
        this.campaign_id = campaign_id;
    }

    public String getMedia_type() {
        return media_type;
    }

    public void setMedia_type(String media_type) {
        this.media_type = media_type;
    }

    public String getVideo() {
        return video;
    }

    public void setVideo(String video) {
        this.video = video;
    }


    public File getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(File thumbnail) {
        this.thumbnail = thumbnail;
    }



    public File getImgFile() {
        return imgFile;
    }

    public void setImgFile(File imgFile) {
        this.imgFile = imgFile;
    }


    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        mBitmap = bitmap;
    }



    public Uri getFile() {
        return mFile;
    }

    public void setFile(Uri file) {
        mFile = file;
    }

    public boolean isImage() {
        return image;
    }

    public void setImage(boolean image) {
        this.image = image;
    }

}
