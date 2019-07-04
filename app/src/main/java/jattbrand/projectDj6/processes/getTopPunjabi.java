package jattbrand.projectDj6.processes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.data.dataTypes.TopSongsData;
import jattbrand.projectDj6.data.strings;


class getTopPunjabi extends Thread {

     private static String _url = strings.top_artists;

    @Override
    public void run() {
        super.run();
        if (RunTimeData.topArtists == null) {
            get();
        } else RunTimeData.isTopArtists_loaded = true;
    }

    private void get() {
        RunTimeData.isTopPunjabiLoaded = false;

        Document document = null;
        try {
            document = Jsoup.connect(strings.top_punjabi_songs).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<TopSongsData> topSongsDataArrayList = null;
        if (document != null) {

            String cover = "", name = "", url = "", artist = "", artistUrl = "";
            TopSongsData topSongsData = new TopSongsData();
            topSongsDataArrayList = null;

            for (Element li : document.select("li")) {

                topSongsData = new TopSongsData();

                if (li.attr("itemprop").contains("track")) {
                    cover = li.select("div").select("img").attr("data-src");
                    for (Element span : li.select("span")) {
                        if (span.attr("itemprop").contains("name")) {
                            name = span.text();
                            url = span.select("a").attr("abs:href");
                        } else if (span.attr("itemprop").contains("byArtist")) {
                            artist = span.text();
                            artistUrl = span.select("a").attr("abs:href");
                        }
                    }
                    topSongsData.setData(name, url, cover, artist, artistUrl);
                    topSongsDataArrayList.add(topSongsData);
                }
            }
        }

        RunTimeData.topSongsData = topSongsDataArrayList;
        RunTimeData.isTopPunjabiLoaded = true;

    }
}
