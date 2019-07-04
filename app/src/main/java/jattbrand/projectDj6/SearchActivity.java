package jattbrand.projectDj6;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.MenuItemCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import jattbrand.projectDj6.Adapters.CustomListsAdapter;
import jattbrand.projectDj6.data.dataTypes.RecSongsData;
import jattbrand.projectDj6.data.strings;

public class SearchActivity extends AppCompatActivity {

    private String query;
    private static String url;
    private static Context context;
    private static RecyclerView searchRecycler;
    private RecSongsData recSongsData;
    private static ArrayList<RecSongsData> recSongsDataArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        context = this;
        searchRecycler = findViewById(R.id.searchRecycler);
        searchRecycler.setLayoutManager(new LinearLayoutManager(context));
        Intent intent = getIntent();
        recSongsData = new RecSongsData();

        if(intent!=null){
            query = intent.getStringExtra("query");
        }else query = "";

        url = strings.song_search_prefix + query;
        getResults(url);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    private void getResults(final String url) {

        recSongsDataArrayList = new ArrayList<>();
        new Thread(new Runnable() {
            @Override
            public void run() {
                Document doc = null;
                try {
                    doc = Jsoup.connect(url).get();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                assert doc != null;
                Elements main = doc.select("li");

                for (Element sub : main) {
                    if (sub.hasClass("youngster_list")) {
                        RecSongsData recSongsData = new RecSongsData();
                        recSongsData.setName(sub.select("a").text());
                        recSongsData.setPageUrl(sub.select("a").attr("abs:href"));
                        recSongsData.setArtistName(sub.select("span").text().substring(sub.select("a").text().length()));
                        recSongsDataArrayList.add(recSongsData);
                    }
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        findViewById(R.id.searchProgress).setVisibility(View.GONE);
                        searchRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                        searchRecycler.setAdapter(new CustomListsAdapter(recSongsDataArrayList));
                    }
                });

            }

        }).start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        if(!query.isEmpty()){
            searchView.setIconifiedByDefault(false);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                searchView.setFocusedByDefault(true);
            }
            searchView.setQueryHint(query);
        }
        ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
        Glide
                .with(context)
                .load(R.drawable.search_solid_white)
                .apply(new RequestOptions().override(30, 30))
                .into(searchIcon);

        LinearLayout linearLayout = searchView.findViewById(androidx.appcompat.R.id.search_edit_frame);
        linearLayout.setBackgroundResource(R.drawable.search_bar_bg);

        ImageView imageView = searchView.findViewById(androidx.appcompat.R.id.search_close_btn);
        Glide
                .with(context)
                .load(R.drawable.baseline_close_24px)
                .apply(new RequestOptions().override(20, 20))
                .into(imageView);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                searchView.setIconified(true);
                searchView.setSelected(false);
                startActivity(new Intent(context, SearchActivity.class).putExtra("query", query));
                finish();
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //  adapter.getFilter().filter(newText);
                return false;
            }
        });
// 
       return super.onCreateOptionsMenu(menu);
    }
//
    //static void sortQueries(ArrayList<RecSongsData> recSongsDataArrayList){
//
//       // albumArray = songsArray = artistArray = new ArrayList<>();
//
//        Log.e("default size ", String.valueOf(recSongsDataArrayList.size()));
//        for(RecSongsData recSongsData : SearchActivity.recSongsDataArrayList)
//        {
//            if(recSongsData.getPageUrl().contains("https://djyoungster.me/")){
//                if(recSongsData.getPageUrl().contains("https://djyoungster.me/music/")){
//                    if(recSongsData.getPageUrl().contains("https://djyoungster.me/music/-")){
//                        //is a album
//                       // albumArray.add(recSongsData);
//                    }else{
//                        //is a song
//                       // songsArray.add(recSongsData);
//                    }
//                }else if(recSongsData.getPageUrl().contains("https://djyoungster.me/artist/")){
//                    //is an artist
//                   // artistArray.add(recSongsData);
// 
  //              }
  //          }
  //      }


   // }

}
