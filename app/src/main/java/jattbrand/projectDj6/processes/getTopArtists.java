package jattbrand.projectDj6.processes;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.data.dataTypes.RecSongsData;
import jattbrand.projectDj6.data.strings;

public class getTopArtists extends Thread {

    private static final String _url = strings.top_artists;

    @Override
    public void run() {
        super.run();
        if(RunTimeData.topArtists == null) {
            get();
        }else RunTimeData.isTopArtists_loaded = true;
    }

    private void get() {
        RunTimeData.isTopArtists_loaded = false;

        Log.e("Loading Top Artists :: ", "Started");
            RecSongsData recSongsData;
            ArrayList<RecSongsData> recArray = new ArrayList<>();

            Document doc = null;
            try {
                doc = Jsoup.connect(_url).userAgent("Mozilla/5.0 (Macintosh; Intel Mac OS X 10_9_2) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/33.0.1750.152 Safari/537.36").get();

            } catch (IOException e) {
                e.printStackTrace();
            }

            if (doc != null) {
                for (Element main : doc.select("p")) {

                    recSongsData = new RecSongsData();
                    try {
                        if (main.hasClass("dj")) {
                            recSongsData.setName(main.select("a").text());
                          //  Log.e("names ", main.select("a").text());
                            recSongsData.setPageUrl(main.select("a").attr("abs:href"));

                            Document doc_cover = Jsoup.connect("https://www.last.fm/music/" + recSongsData.getPageUrl().substring("https://www.djpunjab.net/artist/".length()).replace("-", "+"))
                                    .get();

                            for (Element div : doc_cover.select("div")) {

                                if (div.hasClass("expand-image-show-on-focus header-avatar-inner-wrap")) {
                                    recSongsData.setCoverUrl(div.select("a").select("img").attr("src"));
                                     Log.e("data ::---> ",div.select("a").select("img").attr("src"));
                                    break;
                                }
                            }
                            recArray.add(recSongsData);
                        }
                    } catch (Exception e) {
                        Log.e("Error :: ", e.getMessage());
                    }

                }
            } else Log.e("Null Doc :: ", "GetTopArtists");

            RunTimeData.topArtists = recArray;
            Log.e("TopArtists :: ", String.valueOf(RunTimeData.topArtists.size()));
            RunTimeData.isTopArtists_loaded = true;

    }
}
