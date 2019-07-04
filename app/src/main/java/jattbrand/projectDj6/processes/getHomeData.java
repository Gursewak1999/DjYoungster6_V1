package jattbrand.projectDj6.processes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.data.dataTypes.HomeSongsData;


public class getHomeData extends Thread {

    private static final ArrayList<HomeSongsData> arrayList = new ArrayList<>();


    @Override
    public void run() {
        get();
        super.run();
    }
private static int i =0;
    private static void get() {
        RunTimeData.isHome_loaded = false;
        String heading = "";
        Document doc;
        Elements main_class = null;
        try {
            doc = Jsoup.connect("https://djyoungster.com").get();
            main_class = doc.select("body").select("div");

        } catch (IOException e) {
            e.printStackTrace();
        }

        if (main_class != null) {
            for (Element main : main_class) {

                if (main.hasClass("youngster-main-div")) {

                    Elements sub_main_class = main.select("div");
                    for (Element sub_main : sub_main_class) {
                        HomeSongsData homeSongsData = new HomeSongsData();

                        if (sub_main.hasClass("youngster-sub-heading")) {

                            if(sub_main.text().toLowerCase().contains("punjabi") || sub_main.text().toLowerCase().contains("hindi")) {

                                heading = sub_main.text().substring(0,sub_main.text().length()-"View All".length());
                            }
                        }
                        if (sub_main.hasClass("row")) {
                            Elements row_links = sub_main.select("a");
                            ArrayList<String> temp1 = new ArrayList<>();
                            ArrayList<String> temp2 = new ArrayList<>();
                            ArrayList<String> temp3 = new ArrayList<>();
                            ArrayList<String> temp4 = new ArrayList<>();
                            for (Element row_link : row_links) {

                                if (row_link.attr("abs:href") != null)
                                    temp1.add(row_link.attr("abs:href"));
                                if (!row_link.select("figure").select("img").attr("abs:data-src").isEmpty())
                                    temp2.add(row_link.select("figure").select("img").attr("abs:data-src"));
                                if (!row_link.select("figure").select("figcaption").text().isEmpty())

                                i=0;
                                for(Element el :row_link.select("figure").select("figcaption")){
                                    if(i==0){
                                        temp3.add(el.text());
                                    }else{
                                        temp4.add(el.text());
                                    }
                                    i++;
                                }

                            }
                            homeSongsData.setImageArray(temp2);
                            homeSongsData.setNameArray(temp3);
                            homeSongsData.setUrlArray(temp1);
                            homeSongsData.setHeading(heading);
                            homeSongsData.setArtistArray(temp4);
                            arrayList.add(homeSongsData);

                        }

                    }
                }
            }
        }
        RunTimeData.homeData = arrayList;
        RunTimeData.isHome_loaded = true ;

    }
}
