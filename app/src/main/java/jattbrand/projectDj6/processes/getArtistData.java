package jattbrand.projectDj6.processes;

import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import jattbrand.projectDj6.ArtistActivity;
import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.data.dataTypes.ArtistData;

public class getArtistData extends Thread {

    private static String _name;
    private String name , cover, url;
    private ArrayList<String> nameAr , coverAr, urlAr;
    private int canAdd;
    private String _url;

    public getArtistData(String name) {
        _name = name;
        Log.e("name ", _name);
    }

    @Override
    public void run() {
        super.run();
        get();
    }

    private void get() {
        RunTimeData.isArtist_loaded = false;

        ArtistData artistData = new ArtistData();
        name = cover = url = "";
        nameAr = new ArrayList<>();
        coverAr = new ArrayList<>();
        urlAr = new ArrayList<>();
        artistData.setName(_name);
        artistData.setUrl("https://djyoungster.me/artist/"+_name.trim().replace(" ", "+")+".html");

        try {
            _url = "https://djyoungster.me/artist/"+_name.trim().replace(" ", "+")+".html";
            Document doc = Jsoup.connect(_url).get();

            Log.e("Artist url :: ", _url);
            for(Element li : doc.select("ul").select("li")){

                if (li.attr("itemprop").equals("track")){

                    canAdd = 0;

                    cover = li.select("div").select("img").attr("data-src");
                    for(Element span : li.select("span")){
                        if(span.attr("itemprop").equals("name")){
                            name = span.text();
                           // Log.e("name ", name);

                            url = span.select("a").attr("abs:href");
                        }
                        if(span.hasClass("right_icon")){

                            if(span.text().toLowerCase().contains("mp3")){
                               canAdd = 1;
                            }else{
                                canAdd = 0;
                            }
                        }

                    }
                    if(canAdd == 1){
                        nameAr.add(name);
                        coverAr.add(cover);
                        urlAr.add(url);
                    }
                }
            }

            artistData.setSongName(nameAr);
            artistData.setSongCover(coverAr);
            artistData.setSongUrl(urlAr);

            RunTimeData.artistData = artistData;

        } catch (IOException e) {
            e.printStackTrace();
        }

        Log.e("Artist Data", String.valueOf(RunTimeData.artistData.getSongName().size()));
        RunTimeData.isArtist_loaded = true;
        if (RunTimeData.isArtistActivity){
            ArtistActivity.loadData();
        }

    }
}
