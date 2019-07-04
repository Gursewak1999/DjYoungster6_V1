package jattbrand.projectDj6;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.core.view.MenuItemCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.navigation.NavigationView;

import jattbrand.projectDj6.Adapters.HomeAdapter;
import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.data.onGoingAudio;

public class Main extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private static Context context;
    private static RecyclerView mainRecyclerView;
    private static HomeAdapter adapter;

    private ImageView onGoingImg;
    private ImageButton onGoingPlayBtn;
    private TextView onGoingName, onGoingArtist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RunTimeData.isMainActivity = true;

        context = getApplicationContext();
        Toolbar mTopToolbar = findViewById(R.id.my_toolbar);
        mTopToolbar.setTitle("");
        setSupportActionBar(mTopToolbar);
        //connectivityStatusReceiver = new ConnectivityStatusReceiver();

        configureNavigationDrawer();
        configureToolbar();

        onGoingArtist = findViewById(R.id.onGoingArtist);
        onGoingImg = findViewById(R.id.onGoingImage);
        onGoingName = findViewById(R.id.onGoingName);
        onGoingPlayBtn = findViewById(R.id.onGoingPlayBtn);

        onGoingName.setText(onGoingAudio.name);
        Glide.with(getApplicationContext())
                .load((onGoingAudio.cover != null) ? onGoingAudio.cover : onGoingAudio.coverbytes)
                .transform(new RoundedCorners(16))
                .into(onGoingImg);
        onGoingArtist.setText(onGoingAudio.artist);

        onGoingPlayBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                RunTimeData.isSong_loaded = true;

                if (onGoingAudio.mp.isPlaying()) {
                    onGoingPlayBtn.setImageResource(R.drawable.play_solid);
                } else
                    onGoingPlayBtn.setImageResource(R.drawable.pause_solid);

                PlayerActivity.playBtn.callOnClick();

            }
        });

        RelativeLayout relativeLayout = findViewById(R.id.onGoingMusicPlayerLayout);
    /*    if(onGoingAudio.name.equals("")){
            relativeLayout.setVisibility(View.GONE);
        }else relativeLayout.setVisibility(View.VISIBLE);
      */

        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!onGoingAudio.cover.equals(""))
                    context.startActivity(new Intent(context, PlayerActivity.class)
                            .putExtra("url", onGoingAudio.pageUrl)
                            .putExtra("cover", onGoingAudio.cover)
                            .putExtra("name", onGoingAudio.name)
                            .putExtra("artist", onGoingAudio.artist));
                else context.startActivity(new Intent(context, PlayerActivity.class)
                        .putExtra("url", onGoingAudio.pageUrl)
                        .putExtra("cover", "local")
                        .putExtra("coverBytes", onGoingAudio.coverbytes)
                        .putExtra("name", onGoingAudio.name)
                        .putExtra("artist", onGoingAudio.artist));
            }
        });

        mainRecyclerView = findViewById(R.id.homeRecycler);
        mainRecyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        adapter = new HomeAdapter();
        mainRecyclerView.setAdapter(adapter);
        // setupViewPager();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem searchViewItem = menu.findItem(R.id.app_bar_search);

        final SearchView searchView = (SearchView) MenuItemCompat.getActionView(searchViewItem);

        final ImageView searchIcon = searchView.findViewById(androidx.appcompat.R.id.search_button);
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
                searchIcon.setSelected(false);
                startActivity(new Intent(context, SearchActivity.class).putExtra("query", query));

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                //  adapter.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    static void updateRecycler() {
        Looper.prepare();
        new Handler().post(new Runnable() {
            @Override
            public void run() {
                adapter.notifyDataSetChanged();
            }
        });
    }

    private void configureToolbar() {

        ActionBar actionbar = getSupportActionBar();

        actionbar.setHomeAsUpIndicator(R.drawable.baseline_menu_white);
        actionbar.setDisplayHomeAsUpEnabled(true);

    }

    private void configureNavigationDrawer() {
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navView = findViewById(R.id.nav_view);
        navView.setItemIconTintList(null);
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                // drawerLayout.closeDrawer(GravityCompat.START);

                switch (menuItem.getItemId()) {
                    // Android home

                    case R.id.nav_localMusic:

                        Log.e("Local Music Menu :: ", "I am Clicked");
                        temp.trendingData = RunTimeData.trendingSongs;
                        temp.pos = 4;
                        context.startActivity(new Intent(context, temp.class)
                                .putExtra("name", "Local Music Files"));
                        drawerLayout.closeDrawer(GravityCompat.START);
                        return true;
                    // manage other entries if you have it ...
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch (itemId) {
            // Android home
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            // manage other entries if you have it ...
        }
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        RunTimeData.isMainActivity = true;

        if (onGoingAudio.mp.isPlaying())
            onGoingPlayBtn.setImageResource(R.drawable.pause_solid);
        else
            onGoingPlayBtn.setImageResource(R.drawable.play_solid);

        RelativeLayout relativeLayout = findViewById(R.id.onGoingMusicPlayerLayout);
        if (onGoingAudio.name.equals("")) {
            relativeLayout.setVisibility(View.GONE);
        } else {
            relativeLayout.setVisibility(View.VISIBLE);
        }
        onGoingName.setText(onGoingAudio.name);
        Glide.with(getApplicationContext())
                .load(!onGoingAudio.cover.equals("") ? onGoingAudio.cover : onGoingAudio.coverbytes)
                .transform(new RoundedCorners(4))
                .into(onGoingImg);
        onGoingArtist.setText(onGoingAudio.artist);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (!onGoingAudio.mp.isPlaying()){
            startService(new Intent(context, MusicService.class).setAction("STOP"));
        }
    }

}
