package huanvdph35061.fpoly.moviestime.model;

public class Movie {
    private String title;
    private String des;
    private int thumb;
    private String rating;
    private String streamLink;
    private int coverPhoto;

    public Movie(String title, int thumb) {
        this.title = title;
        this.thumb = thumb;
    }

    public Movie(String title, String des, int thumb, String rating, String streamLink, int coverPhoto) {
        this.title = title;
        this.des = des;
        this.thumb = thumb;
        this.rating = rating;
        this.streamLink = streamLink;
        this.coverPhoto = coverPhoto;
    }

    public Movie(String title, int thumb, int coverPhoto, String des,String streamLink) {
        this.title = title;
        this.thumb = thumb;
        this.coverPhoto = coverPhoto;
        this.des = des;
        this.streamLink = streamLink;
    }

    public int getCoverPhoto() {
        return coverPhoto;
    }

    public void setCoverPhoto(int coverPhoto) {
        this.coverPhoto = coverPhoto;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public int getThumb() {
        return thumb;
    }

    public void setThumb(int thumb) {
        this.thumb = thumb;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getStreamLink() {
        return streamLink;
    }

    public void setStreamLink(String streamLink) {
        this.streamLink = streamLink;
    }
}
