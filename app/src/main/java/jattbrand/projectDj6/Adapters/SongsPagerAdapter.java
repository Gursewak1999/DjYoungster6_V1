package jattbrand.projectDj6.Adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import jattbrand.projectDj6.Helper.Helper;
import jattbrand.projectDj6.R;
import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.data.dataTypes.RecSongsData;
import jattbrand.projectDj6.data.dataTypes.TrendingSongsData;
import jattbrand.projectDj6.processes.getArtistData;

/**
 * Created by florentchampigny on 24/04/15.
 */
public class SongsPagerAdapter extends Fragment {

    // --Commented out by Inspection (23/06/2019 12:43 PM):private static final boolean GRID_LAYOUT = true;
    private static int ITEM_COUNT = 0;
    private static int type = 0, pod;
    private static Context context;
private static String nameStr;
    private static ArrayList<String> name, artist, cover, url;
    private static ArrayList<TrendingSongsData> trendingSongsData;
    private static ArrayList<RecSongsData> recommendedSongs;
    private static ArrayList<RecSongsData> latestSongs;

    // --Commented out by Inspection START (23/06/2019 12:43 PM):
    private static RecyclerView mRecyclerView;

    public SongsPagerAdapter() {

    }

    public SongsPagerAdapter(Context applicationContext, ArrayList<String> dataName, ArrayList<String> dataArtist, ArrayList<String> dataCover, ArrayList<String> dataUrl) {

        context = applicationContext;
        name = dataName;
        artist = dataArtist;
//// --Commented out by Inspection START (23/06/2019 12:43 PM):
// --Commented out by Inspection STOP (23/06/2019 12:43 PM)
        cover = dataCover;
        url = dataUrl;

    }

//    public static SongsPagerAdapter newInstance(Context applicationContext, ArrayList<String> dataName, ArrayList<String> dataArtist, ArrayList<String> dataCover, ArrayList<String> dataUrl) {
//
//        type = 0;
//        context = applicationContext;
//        name = dataName;
//        artist = dataArtist;
//        cover = dataCover;
//        url = dataUrl;
// --Commented out by Inspection STOP (23/06/2019 12:43 PM)
    // return new SongsPagerAdapter();
    // }

    public static SongsPagerAdapter newInstance() {

        return new SongsPagerAdapter();
    }

    public static Fragment newInstance(Context applicationContext, ArrayList<TrendingSongsData> _trendingSongsData) {

        type = 0;
        pod = 0;
        context = applicationContext;
        trendingSongsData = _trendingSongsData;
        return new SongsPagerAdapter();
    }

    public static Fragment newInstance(Context applicationContext, ArrayList<RecSongsData> _recommendedSongs, int x, int y) {

        type = 0;
        pod = 1;
        context = applicationContext;
        recommendedSongs = _recommendedSongs;
        return new SongsPagerAdapter();
    }

    public static Fragment newInstance(Context applicationContext, ArrayList<RecSongsData> _latestSongs, int x) {

        type = 0;
        pod = 2;
        context = applicationContext;
        latestSongs = _latestSongs;
        return new SongsPagerAdapter();
    }

    public static Fragment newInstance(Context applicationContext, String msg) {
        context = applicationContext;

        if (msg.equals("localMusic")) {
            type = 0;
            pod = 4;
        }

        return new SongsPagerAdapter();
    }

    public static Fragment newInstance(Context applicationContext, String msg, int x) {
        context = applicationContext;

        type = 0;
        pod = 5;
        nameStr = msg;

        return new SongsPagerAdapter();
    }


    public static Fragment newInstance(Context applicationContext, ArrayList<String> dataName, ArrayList<String> dataArtist, ArrayList<String> dataUrl) {

        type = 0;
        pod = 3;
        context = applicationContext;
        name = dataName;
        artist = dataArtist;
        url = dataUrl;
        Log.e("data Size SongsPager ", String.valueOf(dataName.size()) + dataArtist.size() + dataUrl.size());
        return new SongsPagerAdapter();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mRecyclerView = view.findViewById(R.id.homeRecycler);

        final List<Object> items = new ArrayList<>();

   /*     switch (type) {
            case 0:
                ITEM_COUNT = RunTimeData.trendingSongs.size();
                break;
            case 1:
                ITEM_COUNT = RunTimeData.latestSongs.size();
                break;
            case 2:
                ITEM_COUNT = RunTimeData.trendingSongs.size();
                break;
            case 3:
                ITEM_COUNT = RunTimeData.trendingSongs.size();
                break;
        }
        for (int i = 0; i < ITEM_COUNT; ++i) {
            items.add(new Object());
        }
        //*/

        ArrayList<ArrayList<String>> data = new ArrayList<>();
        //setup materialviewpager

        name = new ArrayList<>();
        artist = new ArrayList<>();
        cover = new ArrayList<>();
        url = new ArrayList<>();
        switch (pod) {
            case 3:
                data = new ArrayList<>();
                data.add(name);
                data.add(artist);
                data.add(cover);
                data.add(url);
                if (type == 0) {
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                    mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                  //  mRecyclerView.setAdapter(new TestRecyclerViewAdapter(context, data, type, 0, 0));
                    mRecyclerView.setAdapter(new CustomListsAdapter(context, data));

                } else {
                    mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 3));
                    mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                    mRecyclerView.setAdapter(new CustomGridAdapter(context, data));
                }
                break;

            case 0: //fromTrendingSongs

                mRecyclerView.setLayoutManager(new LinearLayoutManager(context));
                mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
               // mRecyclerView.setAdapter(new TestRecyclerViewAdapter(context, trendingSongsData, type));
               if (data!=null)
                data.clear();
               name.clear();
               cover.clear();
               artist.clear();
               url.clear();
               for (TrendingSongsData songsData : trendingSongsData){
                   name.add(songsData.getName());
                   cover.add(songsData.getCoverUrl());
                   artist.add(songsData.getArtist());
                   url.add(songsData.getUrl());
               }
                data.add(name);
                data.add(cover);
                data.add(artist);
                data.add(url);
                mRecyclerView.setAdapter(new CustomListsAdapter(context, data));
                break;

            case 1: //fromRecommendedSongs
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
               // mRecyclerView.setAdapter(new TestRecyclerViewAdapter(context, recommendedSongs, type, 0));
                data.clear();
                name.clear();
                cover.clear();
                artist.clear();
                url.clear();
                for (RecSongsData recSongsData : recommendedSongs){
                    name.add(recSongsData.getName());
                    cover.add(recSongsData.getCoverUrl());
                    artist.add(recSongsData.getArtistName());
                    url.add(recSongsData.getPageUrl());
                }
                data.add(name);
                data.add(cover);
                data.add(artist);
                data.add(url);
                mRecyclerView.setAdapter(new CustomListsAdapter(context, data));
                break;

            case 2: //fromLatestSongs
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
               // mRecyclerView.setAdapter(new TestRecyclerViewAdapter(context, latestSongs, type, 0));
                data.clear();
                name.clear();
                cover.clear();
                artist.clear();
                url.clear();
                for (RecSongsData recSongsData : latestSongs){
                    name.add(recSongsData.getName());
                    cover.add(recSongsData.getCoverUrl());
                    artist.add(recSongsData.getArtistName());
                    url.add(recSongsData.getPageUrl());
                }
                data.add(name);
                data.add(cover);
                data.add(artist);
                data.add(url);
                mRecyclerView.setAdapter(new CustomListsAdapter(context, data));
                break;

            case 4:
                //from Local Library
                mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));
                mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());

                ArrayList<File> fileArrayList = Helper.getLocalSongs(new File(String.valueOf(Environment.getExternalStorageDirectory())));
                mRecyclerView.setAdapter(new LocalSongsAdapter(fileArrayList));
                break;

            case 5:
                mRecyclerView.setLayoutManager(new GridLayoutManager(getContext(), 3));
                mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
                new loadAsyncData().execute();

                break;
        }

        //Use this now
        //  */
    }

    private static class loadAsyncData extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            RunTimeData.isArtist_loaded = false;
            Log.e("Recycler :: ", "Loading");
            new getArtistData(nameStr).start();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (true) {
                if (RunTimeData.isArtist_loaded) {
                    Log.e("Recycler :: ", "Loaded");
                    break;
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
            //  artistRecycler.setAdapter(new TestRecyclerViewAdapter(context, RunTimeData.artistData));
            mRecyclerView.setAdapter(new CustomGridAdapter(RunTimeData.artistData));

            //mRecyclerView.setBackgroundColor(Color.TRANSPARENT);
        }
    }

}