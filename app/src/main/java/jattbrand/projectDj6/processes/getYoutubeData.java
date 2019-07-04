package jattbrand.projectDj6.processes;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.data.dataTypes.YoutubeData;
import jattbrand.projectDj6.data.strings;

public class getYoutubeData extends Thread {

    private final ArrayList<String> bannerUrl = new ArrayList<>();

    @Override
    public void run() {
        super.run();

        RunTimeData.isYoutube_loaded = false;

        ArrayList<YoutubeData> youtubeData_array = new ArrayList<>();
        YoutubeData youtubeData = null;

        Document doc1 = null;
        try {
            doc1 = Jsoup.connect(strings.youtube_videos).get();
        } catch (IOException e) {
            e.printStackTrace();
        }

        assert doc1 != null;
        Elements main = doc1.select("body").select("div");

        for (Element sub : main) {

            if (sub.select("img").attr("src").contains("https://i.ytimg.com/vi/")) {

                String cover = sub.select("img").attr("src").substring(0, "https://i.ytimg.com/vi/Mw4h86pxY7U/hqdefault.jpg".length());

                if (bannerUrl.size() == 0) {
                     bannerUrl.add(cover);
                } else {
                    int yes = 0;
                    for (int i = 0; i < bannerUrl.size(); i++) {

                        if (bannerUrl.get(i).contains(cover)) {
                            yes = 1;
                        }
                    }

                    if (yes == 0) {
                        bannerUrl.add(cover);

                    }
                }
            }
        }


        for(String _url : bannerUrl){

            youtubeData = new YoutubeData();
            String _id =_url.substring("https://i.ytimg.com/vi/".length(), _url.length()-"/hqdefault.jpg".length());
            youtubeData.setUrl("http://www.youtube.com/watch?v=" + _id);
            youtubeData.setCover(_url);
            youtubeData.setId(_id);
            youtubeData_array.add(youtubeData);
        }

        RunTimeData.youtubeData = youtubeData_array;
        RunTimeData.isYoutube_loaded = true;

    }
}
