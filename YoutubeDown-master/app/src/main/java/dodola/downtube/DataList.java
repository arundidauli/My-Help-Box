package dodola.downtube;

public class DataList {
    String imageUrl,videoUrl;

    public DataList(String imageUrl, String videoUrl) {
        this.imageUrl = imageUrl;
        this.videoUrl = videoUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public DataList() {

    }
    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }
}
