package jattbrand.projectDj6.data.dataTypes;


class LatestSongsData {

    private String name;

    private String url;
    private String releaseDate;
    private String coverUrl;
    private String artist;
    private int rating;

    public void LatestSongsData(String name, String artist, String url, String releaseDate) {

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
