package jattbrand.projectDj6.data.dataTypes;

public class RecSongsData {

    private String name;
    private String artistName;
    private String coverUrl;
    private String pageUrl;

    public RecSongsData(String name, String artistName, String coverUrl, String pageUrl) {
        this.name = name;
        this.artistName = artistName;
        this.coverUrl = coverUrl;
        this.pageUrl = pageUrl;
    }

    public RecSongsData(){}

    public String getName() {
        return name;
    }

    public String getArtistName() {
        return artistName;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public void setCoverUrl(String coverUrl) {
        this.coverUrl = coverUrl;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }
}
