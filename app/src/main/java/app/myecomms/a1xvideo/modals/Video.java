package app.myecomms.a1xvideo.modals;

public class Video {

    public int video;
    public int paly;
    private String videoUrl;

    public Video(int video, int paly) {
        this.video = video;
        this.paly = paly;

    }
    public Video(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }
}
