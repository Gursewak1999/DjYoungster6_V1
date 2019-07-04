package jattbrand.projectDj6.processes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;

import jattbrand.projectDj6.data.dataTypes.AlbumData;
import jattbrand.projectDj6.data.RunTimeData;


public class getAlbumData extends Thread{

    private static String _url;
    public static boolean gotData;

    public getAlbumData(String url) {
        _url = url;
    }

    @Override
    public void run() {
        super.run();
        get();
    }

    private void get() {

        RunTimeData.isAlbum_loaded = false;

        ArrayList<AlbumData> albumData_array = new ArrayList<>();
        AlbumData albumData = new AlbumData();
        ArrayList<String> songsName = new ArrayList<>(),
                songsUrl = new ArrayList<>(),
                songsArtist = new ArrayList<>(),
                songsCover= new ArrayList<>();

        Document doc = null;
        try {
            doc = Jsoup.connect(_url).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(Element main : doc.select("div")){

            if(main.hasClass("youngster-thumb")){
         //       RunTimeData.currentCoverUrl = main.select("img").attr("abs:data-src");
                albumData.setUrl(main.select("img").attr("abs:data-src"));
               // Log.e("coverUrl", main.select("img").attr("abs:data-src"));
            }

            if(main.hasClass("youngster-div")){
                for(Element sub : main.select("h1").select("span")){
                    if(sub.hasClass("youngster-singer")){
                        albumData.setArtist(sub.text());
                        //RunTimeData.currentArtistUrl = sub.select("a").attr("abs:href");
                      //  Log.e("singerName ", sub.text());
                      //  Log.e("singerUrl ", sub.select("a").attr("abs:href"));
                    }
                    if(sub.hasClass("youngster-title")){
                        //RunTimeData.currentSongName = sub.text();
                        albumData.setName(sub.text());
                       // Log.e("singerTitle ", sub.text());
                    }
                }

                for(Element sub : main.select("ul").select("li")){
                   // RunTimeData.currentDescDataKey.add(sub.select("strong").text());
                   // RunTimeData.currentDescDataValue.add(sub.text().substring(sub.select("strong").text().length()));
                    String key = sub.select("strong").text();
                    String value = sub.text().substring(sub.select("strong").text().length());

                    if(key.toLowerCase().contains("tracks")){
                        albumData.setTracks(Integer.parseInt(value.substring(1)));
                    }else if(key.toLowerCase().contains("added")){
                        albumData.setReleasedOn(value);
                    }else if(key.toLowerCase().contains("composer")){
                        albumData.setComposer(value);
                    }

                   // Log.e(sub.select("strong").text(), sub.text().substring(sub.select("strong").text().length()));
                }
            }


            if(main.hasClass("youngster-main-div")){

                for(Element sub : main.select("ul").select("li")){
                    for(Element sub_sub : sub.select("span")){
                        if(sub_sub.attr("itemprop").equals("name")){

                            songsName.add(sub_sub.text());
                            songsUrl.add(sub_sub.select("a").attr("abs:href"));
                          //  RunTimeData.albumSongName.add(sub_sub.text());
                          //  RunTimeData.albumSongUrl.add(sub_sub.select("a").attr("abs:href"));
                          //  Log.e(sub_sub.text(), sub_sub.select("a").attr("abs:href"));
                        }else if(sub_sub.attr("itemprop").equals("byArtist")){

                            songsArtist.add(sub_sub.text());
                           // RunTimeData.albumSongArtist.add(sub_sub.text());
                           // Log.e(sub_sub.text(), "  ");
                        }
                    }
                    if(sub.select("div").attr("itemprop").equals("image")){
                        songsCover.add(sub.select("div").select("img").attr("abs:data-src"));
                //        RunTimeData.albumSongCover.add(sub.select("div").select("img").attr("abs:data-src"));
                       // Log.e("imageUrl ", sub.select("div").select("img").attr("abs:data-src"));
                    }
                }

                albumData.setSongArtist(songsArtist);
                albumData.setSongCover(songsCover);
                albumData.setSongName(songsName);
                albumData.setSongUrl(songsUrl);

            }
        }

        RunTimeData.currentAlbumsData = albumData;
        RunTimeData.isAlbum_loaded = true;

//       songsActivity.loadImages();
    }
}
