package jattbrand.projectDj6.data.dataTypes;

import java.util.ArrayList;

public class HomeSongsData {

    private String heading;
    private String category;
    private ArrayList<String> imageArray;
    private ArrayList<String> nameArray;
    private ArrayList<String> urlArray;
    private ArrayList<String> artistArray;

    public HomeSongsData(String heading,
                         String category,
                         ArrayList<String> imageArray,
                         ArrayList<String> nameArray,
                         ArrayList<String> urlArray) {

        this.heading = heading;
        this.category = category;
        this.imageArray = imageArray;
        this.nameArray = nameArray;
        this.urlArray = urlArray;
    }


    public HomeSongsData(){
    }

    public String getHeading() {
        return heading;
    }

    public String getCategory() {
        return category;
    }

    public ArrayList<String> getImageArray() {

        return imageArray;
    }
    public ArrayList<String> getNameArray() {
        return nameArray;

    }
    public ArrayList<String> getUrlArray() {
        return urlArray;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setImageArray(ArrayList<String> imageArray) {
        this.imageArray = imageArray;
    }
    public void setNameArray(ArrayList<String> nameArray) {
        this.nameArray = nameArray;
    }
    public void setUrlArray(ArrayList<String> urlArray) {
        this.urlArray = urlArray;
    }

    public ArrayList<String> getArtistArray() {
        return artistArray;
    }

    public void setArtistArray(ArrayList<String> artistArray) {
        this.artistArray = artistArray;
    }
}
