package jattbrand.projectDj6.data.dataTypes;

public class TrendingSongsData {

    private final String name;
    private final String url;
    private final String releaseDate;
    private String coverUrl;
    private final String artist;
    private int rating;


    public TrendingSongsData(String name, String artist, String url, String releaseDate){
        this.artist = artist;
        this.name = name;
        this.releaseDate = releaseDate;
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }
    public String getReleaseDate() {

        return releaseDate;
    }
    public String getCoverUrl() {
        return coverUrl;
    }
 
 public String getArtist() {
        return artist;
    }
    public int getRating() {

        return rating;
    }
}
