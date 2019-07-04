package jattbrand.projectDj6.processes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.data.dataTypes.RecSongsData;
import jattbrand.projectDj6.data.strings;

public class getLatest extends Thread{



    @Override
    public void run() {
        super.run();
        get();
    }

    private void get() {

        RunTimeData.isLatest_loaded = false;

        ArrayList<RecSongsData> recSongsData = new ArrayList<>();
        String name = "", artist = "", cover = "", url = "";
        Document doc = null;
        try {
            doc = Jsoup.connect(strings.top_punjabi_songs).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Element li : doc.select("ul").select("li")) {
            if (li.attr("itemprop").equals("track")) {
                cover = li.select("div").select("img").attr("abs:data-src");

                for (Element sub : li.select("span")) {
                    if (sub.attr("itemprop").equals("name")) {

                        name = sub.select("a").text();
                        url = sub.select("a").attr("abs:href");
                    } else if (sub.attr("itemprop").equals("byArtist")) {

                        artist = sub.text();
                    }
                }


                recSongsData.add(new RecSongsData(name, artist, cover, url));
            }
        }

        RunTimeData.latestSongs = recSongsData;
        RunTimeData.isLatest_loaded = true;

    }
}
