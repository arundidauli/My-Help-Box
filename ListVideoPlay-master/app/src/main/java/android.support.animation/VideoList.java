package android.support.animation;

public class VideoList {
    private String coverImage;
    private String videoUrl;
    private   int position;

    public VideoList(String coverImage, String videoUrl, int position) {
        this.coverImage = coverImage;
        this.videoUrl = videoUrl;
        this.position = position;
    }

    public String getCoverImage() {
        return coverImage;
    }

    public void setCoverImage(String coverImage) {
        this.coverImage = coverImage;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }
}
