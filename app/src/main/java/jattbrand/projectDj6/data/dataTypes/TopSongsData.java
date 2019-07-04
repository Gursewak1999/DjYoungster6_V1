package jattbrand.projectDj6.data.dataTypes;

public class TopSongsData {
//
    private String name, url, cover, artist, artistUrl;

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public String getUrl() {
         return url;
     }

     public void setUrl(String url) {
         this.url = url;
     }

     public String getCover() {
         return cover;
     }

     public void setCover(String cover) {
         this.cover = cover;
     }

     public String getArtist() {
         return artist;
     }

     public void setArtist(String artist) {
         this.artist = artist;
     }

     public String getArtistUrl() {
         return artistUrl;
     }

     public void setArtistUrl(String artistUrl) {
         this.artistUrl = artistUrl;
     }

     public void setData(String name, String url, String cover, String artist, String artistUrl) {
        this.name = name;
        this.url = url;
        this.cover = cover;
        this.artist = artist;
        this.artistUrl = artistUrl;
    }
}
