package jattbrand.projectDj6.processes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.data.dataTypes.TrendingSongsData;
import jattbrand.projectDj6.data.strings;

public class getTrendings extends Thread{

    @Override
    public void run() {
        super.run();
        get();
    }

    private void get() {
        RunTimeData.isTrending_loaded = false;

        String url = "", name = "", artist = "", addedOn = "";

        ArrayList<TrendingSongsData> trendingSongsData = new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect(strings.trending).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        int i = 0 ;

        if (doc != null) {
            for (Element tr : doc.select("tr")) {

                i=1;
                if(!tr.select("td").hasClass("main_td")) {
                    for (Element td : tr.select("td")) {

                        if (i == 2) {
                            url = td.select("a").attr("abs:href");
                            name = td.select("a").select("span").text();
                            artist = td.select("a").select("small").text();
                        } else if (i == 3) {
                            addedOn = td.text();
                        }
                        i++;
                    }
                    if(!url.equals(""))
                    trendingSongsData.add(new TrendingSongsData(name,artist, url, addedOn));
                }
            }
        }

        RunTimeData.trendingSongs = trendingSongsData;
        RunTimeData.isTrending_loaded = true;

    }
}
