package jattbrand.projectDj6.data.dataTypes;

import java.util.ArrayList;

public class AlbumData {

    private String name = "";
    private String url = "";
    private String artist = "";
    private String cover = "";
    private String releasedOn = "";
    private String composer = "";
    private int tracks = 0;
    private ArrayList<String> songName;
    private ArrayList<String> songUrl;
    private ArrayList<String> songArtist;

    public String getArtist() {
        return artist;
    }

// --Commented out by Inspection START (23/06/2019 12:43 PM):
    public void setSongArtistUrl(ArrayList<String> songArtistUrl) {
        this.songArtistUrl = songArtistUrl;
    }
// --Commented out by Inspection STOP (23/06/2019 12:43 PM)

// --Commented out by Inspection START (23/06/2019 12:43 PM):
public ArrayList<String> getSongArtistUrl() {
        return songArtistUrl;
    }
// --Commented out by Inspection STOP (23/06/2019 12:43 PM)

    private ArrayList<String> songArtistUrl;
    private ArrayList<String> songCover;

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getCover() {
        return cover;
    }

// --Commented out by Inspection START (23/06/2019 12:43 PM):
//// --Commented out by Inspection START (23/06/2019 12:43 PM):
    public String getReleasedOn() {
// --Commented out by Inspection STOP (23/06/2019 12:43 PM)
        return releasedOn;
    }
// --Commented out by Inspection START (23/06/2019 12:43 PM):
//// --Commented out by Inspection STOP (23/06/2019 12:43 PM)
//
//// --Commented out by Inspection START (23/06/2019 12:43 PM):
// --Commented out by Inspection STOP (23/06/2019 12:43 PM)
// --Commented out by Inspection START (23/06/2019 12:43 PM):
    public String getComposer() {
//// --Commented out by Inspection START (23/06/2019 12:43 PM):
// --Commented out by Inspection STOP (23/06/2019 12:43 PM)
        return composer;
    }
//// --Commented out by Inspection STOP (23/06/2019 12:43 PM)
// --Commented out by Inspection STOP (23/06/2019 12:43 PM)

    public int getTracks() {
        return tracks;
    }

    public ArrayList<String> getSongName() {
        return songName;
    }

    public ArrayList<String> getSongUrl() {
        return songUrl;
    }

    public ArrayList<String> getSongArtist() {
        return songArtist;
    }

    public ArrayList<String> getSongCover() {
        return songCover;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public void setReleasedOn(String releasedOn) {
        this.releasedOn = releasedOn;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public void setTracks(int tracks) {
        this.tracks = tracks;
    }

    public void setSongName(ArrayList<String> songName) {
        this.songName = songName;
    }

    public void setSongUrl(ArrayList<String> songUrl) {
        this.songUrl = songUrl;
    }

    public void setSongArtist(ArrayList<String> songArtist) {
        this.songArtist = songArtist;
    }

    public void setSongCover(ArrayList<String> songCover) {
        this.songCover = songCover;
    }

    public void setArtist(String text) {
        this.artist = text;
    }
}
