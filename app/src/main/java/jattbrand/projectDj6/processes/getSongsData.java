package jattbrand.projectDj6.processes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

import jattbrand.projectDj6.data.dataTypes.RecSongsData;
import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.data.dataTypes.SongData;

public class getSongsData extends Thread {

    private static String _url;

    public getSongsData(String url) {
        _url = url;
    }

    @Override
    public void run() {
        super.run();
        get();
    }

    private void get() {
        RunTimeData.isSong_loaded = false;

        SongData songData = new SongData();
        ArrayList<RecSongsData> recArray = new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect(_url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }


        for (Element main : Objects.requireNonNull(doc).select("div")) {

            if (main.hasClass("youngster-thumb")) {
                songData.setCoverUrl(main.select("img").attr("abs:data-src"));
            }

            if (main.hasClass("youngster-div")) {
                for (Element sub : main.select("h1").select("span")) {
                    if (sub.hasClass("youngster-singer")) {

                        songData.setArtistName(sub.text());
                        songData.setArtistUrl(sub.select("a").attr("abs:href"));
                    }
                    if (sub.hasClass("youngster-title")) {
                        songData.setName(sub.text().substring(0, sub.text().length() - ".mp3".length()));
                    }
                }

                for (Element sub : main.select("ul").select("li")) {

                    String key = sub.select("strong").text();
                    String value = sub.text().substring(sub.select("strong").text().length());

                    if (key.toLowerCase().contains("lyrics")) {
                        songData.setLyricsBy(value);
                    } else if (key.toLowerCase().contains("added")) {
                        songData.setAddedOn(value);
                    }else if(key.toLowerCase().contains("runtime")){
                        songData.setTotalTime(value);
                    }

                }
            }

            if (main.hasClass("youngster-load-section")) {
                for (Element sub : main.select("a")) {
                    if (sub.hasClass("download-link")) {

                        String key = sub.text();
                        String value = sub.attr("abs:href");

                        if (key.contains("48")) {
                            songData.setUrl_48(value);
                        } else if (key.contains("128")) {
                            songData.setUrl_128(value);
                        } else if (key.contains("192")) {
                            songData.setUrl_192(value);
                        } else if (key.contains("320")) {
                            songData.setUrl_320(value);
                        }
                    }
                }
            }

            if(main.hasClass("row")){

                for(Element a : main.select("a")){
                    RecSongsData recSongsData = new RecSongsData();
                    recSongsData.setPageUrl(a.attr("abs:href"));
                    recSongsData.setCoverUrl(a.select("figure").select("img").attr("abs:data-src"));
                    for(Element figcaption : a.select("figure").select("figcaption")){
                        if(figcaption.hasClass("row-title")){
                            recSongsData.setName(figcaption.text());
                        }else if(figcaption.hasClass("row-singer")){
                            recSongsData.setArtistName(figcaption.text());
                        }
                    }
                    recArray.add(recSongsData);
                }
            }

        }

        songData.setRecommendedSongs(recArray);
        RunTimeData.currentSongData = songData;
        RunTimeData.isSong_loaded = true;

    }
}
