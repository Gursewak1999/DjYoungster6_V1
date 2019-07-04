package jattbrand.projectDj6.data;

import android.media.MediaPlayer;

import java.util.ArrayList;

import jattbrand.projectDj6.data.dataTypes.RecSongsData;

public class onGoingAudio {

    public static MediaPlayer mp = new MediaPlayer();


    public static float currentTime = 0;

    public static float totalTime = 0;

    public static float secondaryProgress = 0;
    public static String pageUrl = "", url = "";
    public static String name = "";

    public static String artist = "";


    public static String cover = "";
    public static Boolean ismpPlaying = false;

    public static Boolean ismpPaused = false;
    public static int position;
    public static ArrayList<RecSongsData> que;

    public static byte[] coverbytes;

    public static void setRawData(String _name, String _artist, String _songUrl, String _url, String _cover) {
        name = _name;
        pageUrl = _songUrl;
        url = _url;
        cover = _cover;
        artist = _artist;
    }

    public static void setRawData(String _name, String _artist, String _songUrl, String _url, byte[] _cover) {
        name = _name;
        pageUrl = _songUrl;
        url = _url;
        coverbytes = _cover;
        artist = _artist;
    }
}
