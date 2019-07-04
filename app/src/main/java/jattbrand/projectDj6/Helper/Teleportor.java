package jattbrand.projectDj6.Helper;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import java.util.ArrayList;

import jattbrand.projectDj6.ArtistActivity;
import jattbrand.projectDj6.DetailsActivity;
import jattbrand.projectDj6.PlayerActivity;
import jattbrand.projectDj6.data.dataTypes.AlbumData;
import jattbrand.projectDj6.data.dataTypes.ArtistData;
import jattbrand.projectDj6.data.dataTypes.RecSongsData;
import jattbrand.projectDj6.data.dataTypes.SongData;
import jattbrand.projectDj6.data.dataTypes.TrendingSongsData;

public class Teleportor {

    private final Context context;

    private String url;
    private TrendingSongsData trendingSongsData;
    private RecSongsData recSongsData;
    private SongData songData;
    private AlbumData albumsData;
    private ArtistData artistData;

    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\-----------Constructors------------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\



    public Teleportor(Context context, String url) {
        this.context = context;
        this.url = url;
        fromUrl();
    }
 



    public Teleportor(Context context, RecSongsData recSongsData) {
        this.context = context;
        this.recSongsData = recSongsData;
        fromRecSongs();
    }
 
 

    public Teleportor(Context context, TrendingSongsData trendingSongsData) {
        this.context = context;
        this.trendingSongsData = trendingSongsData;
        fromTrendingSongs();
    }

    public Teleportor(Context context, SongData songData) {
        this.context = context;
        this.songData = songData;
        fromSongsData();
    }


    public Teleportor(Context context, AlbumData albumsData) {
        this.context = context;

        this.albumsData = albumsData;
        fromAlbumData();
    }
 

    public Teleportor(Context context, ArtistData artistData) {
 
        this.context = context;
        this.artistData = artistData;
        fromArtistdata();
    }

    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\----------------------Functions-------------------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


    private void fromUrl() {

        if(url.contains("https:i.ytimg.com/vi/")) {
            targetyoutube(url);
        }else if(url.contains("djyoungster.me/music/") && !url.contains("djyoungster.me/music/-")){
           // is a song
            targetSong("Legend","Sidhu", url,url);
        }
    }

    private void fromRecSongs() {

        targetSong(recSongsData.getName(),
                recSongsData.getArtistName(),
                recSongsData.getPageUrl(),
                recSongsData.getCoverUrl());
    }

    private void fromTrendingSongs() {

        targetSong(trendingSongsData.getName(),
                trendingSongsData.getArtist(),
                trendingSongsData.getUrl(),
                trendingSongsData.getCoverUrl());
    }

    private void fromSongsData() {

        targetSong(songData.getName(),
                songData.getArtistName(),
                songData.getPageUrl(),
                songData.getCoverUrl());
    }

    private void fromAlbumData() {

        targetAlbum(albumsData.getName(),
                albumsData.getArtist(),
                albumsData.getUrl(),
                albumsData.getCover());
    }

    private void fromArtistdata(){

        targetArtist(artistData.getName(),
                artistData.getCover(),
                artistData.getSongName(),
                artistData.getSongUrl(),
                artistData.getSongCover());
    }

    //\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\-------------------RealTime Movers---------------\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\

    private void targetyoutube(String url){
        url = url.substring("https:i.ytimg.com/vi/".length(), url.length() - "/hqdefault.jpg".length());
        Log.e("Youtube ID ", url);
       // Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + url));
        //Intent webIntent = new Intent(Intent.ACTION_VIEW,
              //  Uri.parse("http:www.youtube.com/watch?v=" + id));
        try {
            Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            context.startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW,
                                //Uri.parse("http:www.youtube.com/watch?v=" + id));
                                    Uri.parse(url));
            context.startActivity(webIntent);
        }

    }

    private void targetAlbum(String name, String artist, String pageUrl, String cover) {


        context.startActivity(new Intent(context, DetailsActivity.class).putExtra("pageUrl", pageUrl)
                .putExtra("cover", cover)
                .putExtra("name", name)
                .putExtra("artist", artist));
    }

    private void targetSong(String name, String artist, String pageUrl, String cover) {

        context.startActivity(new Intent(context, PlayerActivity.class)
                .putExtra("url", pageUrl)
                .putExtra("cover", cover)
                .putExtra("name", name)
                .putExtra("artist", artist));
    }

    private void targetArtist(String name, String cover, ArrayList<String> songName, ArrayList<String> songUrl, ArrayList<String> songCover) {

        context.startActivity(new Intent(context, ArtistActivity.class)
                .putExtra("name", name)
                .putExtra("cover", cover)
                .putExtra("songName", songName)
                .putExtra("songUrl", songUrl)
                .putExtra("songCover", songCover)
        );
    }


}
