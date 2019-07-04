package jattbrand.projectDj6.data.dataTypes;

import java.util.ArrayList;

public class SongData {


    private String name = "";
    private String artistName = "";
    private String coverUrl = "";
    private String pageUrl = "";
    private String streamUrl = "";
    private String url_48 = "";
    private String url_128 = "";
    private String url_192 = "";
    private String url_320 = "";
    private String artistUrl = "";
    private int rating = 0;
    private ArrayList<RecSongsData> recommendedSongs = new ArrayList<>();
    private String lyricsBy = "";
    private String addedOn = "";


    public String getTotalTime() {
        return totalTime;
    }


    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    private String totalTime = "";


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





    public String getStreamUrl() {



        return streamUrl;
    }


    public String getUrl_48() {
        return url_48;
    }

    public String getUrl_128() {
        return url_128;
    }

    public String getUrl_192() {
        return url_192;
    }

    public String getUrl_320() {
        return url_320;
    }






    public String getArtistUrl() {
        return artistUrl;


    }


    public int getRating() {

        return rating;
    }


    public ArrayList<RecSongsData> getRecommendedSongs() {


        return recommendedSongs;
    }

    public String getLyricsBy() {
        return lyricsBy;
    }


    public String getAddedOn() {
        return addedOn;
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

    public void setStreamUrl(String streamUrl) {
        this.streamUrl = streamUrl;
    }

    public void setUrl_48(String url_48) {
        this.url_48 = url_48;
    }

    public void setUrl_128(String url_128) {
        this.url_128 = url_128;
    }

    public void setUrl_192(String url_192) {
        this.url_192 = url_192;
    }

    public void setUrl_320(String url_320) {
        this.url_320 = url_320;
    }

    public void setArtistUrl(String artistUrl) {
        this.artistUrl = artistUrl;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setRecommendedSongs(ArrayList<RecSongsData> recommendedSongs) {
        this.recommendedSongs = recommendedSongs;
    }

    public void setLyricsBy(String lyricsBy) {
        this.lyricsBy = lyricsBy;
    }

    public void setAddedOn(String addedOn) {
        this.addedOn = addedOn;
    }
}
