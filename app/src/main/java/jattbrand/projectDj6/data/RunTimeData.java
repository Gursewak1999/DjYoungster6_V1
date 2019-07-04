package jattbrand.projectDj6.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;

import jattbrand.projectDj6.data.dataTypes.AlbumData;
import jattbrand.projectDj6.data.dataTypes.ArtistData;
import jattbrand.projectDj6.data.dataTypes.HomeSongsData;
import jattbrand.projectDj6.data.dataTypes.RecSongsData;
import jattbrand.projectDj6.data.dataTypes.SongData;
import jattbrand.projectDj6.data.dataTypes.TopSongsData;
import jattbrand.projectDj6.data.dataTypes.TrendingSongsData;
import jattbrand.projectDj6.data.dataTypes.YoutubeData;

public class RunTimeData {

    public static ArrayList<YoutubeData> youtubeData;
    public static ArrayList<HomeSongsData> homeData;
    public static ArrayList<TrendingSongsData> trendingSongs;
    public static ArrayList<RecSongsData> recommendedSongs;
    public static ArrayList<RecSongsData> latestSongs;
    public static ArrayList<RecSongsData> topArtists;
    public static ArtistData artistData;

    public static SongData currentSongData;
    public static AlbumData currentAlbumsData;

    public static Boolean isYoutube_loaded;
    public static Boolean isHome_loaded;
    public static Boolean isTrending_loaded;
    public static Boolean isRecommended_loaded;
    public static Boolean isLatest_loaded;
    public static volatile Boolean isSong_loaded;
    public static Boolean isAlbum_loaded;
     public static String serviceSongUrl;
    public static Boolean isTopArtists_loaded;
     public static Boolean isLyrics_loaded;
    public static boolean isArtist_loaded;

    public static boolean isArtistActivity = false;
    public static Boolean isMainActivity;
    public static Boolean isPlayerActivity = false;
    public static boolean isTopPunjabiLoaded;
    public static ArrayList<TopSongsData> topSongsData;

    public static String getDATA() {

        Gson gson = new Gson();

        ArrayList<String> data = new ArrayList<>();
        data.add(gson.toJson(youtubeData));
        data.add(gson.toJson(homeData));
        data.add(gson.toJson(trendingSongs));
        data.add(gson.toJson(recommendedSongs));
        data.add(gson.toJson(latestSongs));
        data.add(gson.toJson(topArtists));

        return gson.toJson(data);
    }

    public static boolean setData(String data){

        Gson gson = new Gson();
        ArrayList arrayList = gson.fromJson(data, ArrayList.class);

        int i=0;
        for(Object json_string : arrayList){

            String data_string = (String) json_string;
            switch (i){

                case 0 : youtubeData = gson.fromJson(data_string, new TypeToken<ArrayList<YoutubeData>>(){}.getType()); break;
                case 1 : homeData = gson.fromJson(data_string,  new TypeToken<ArrayList<HomeSongsData>>(){}.getType()); break;
                case 2 : trendingSongs = gson.fromJson(data_string,  new TypeToken<ArrayList<TrendingSongsData>>(){}.getType()); break;
                case 3 : recommendedSongs = gson.fromJson(data_string,  new TypeToken<ArrayList<RecSongsData>>(){}.getType()); break;
                case 4 : latestSongs = gson.fromJson(data_string,  new TypeToken<ArrayList<RecSongsData>>(){}.getType()); break;
                case 5 : topArtists = gson.fromJson(data_string,  new TypeToken<ArrayList<RecSongsData>>(){}.getType()); break;
            }
            i++;
        }

        return true;
    }
}
