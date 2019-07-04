package jattbrand.projectDj6.data.dataTypes;

public class YoutubeData {

    private String url;
    private String cover;

    public void setId(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    private String id;

    public String getUrl() {
        return url;
    }

    public String getCover() {
        return cover;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
