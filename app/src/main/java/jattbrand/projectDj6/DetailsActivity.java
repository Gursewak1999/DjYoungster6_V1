package jattbrand.projectDj6;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import jattbrand.projectDj6.Adapters.CustomListsAdapter;
import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.processes.getAlbumData;

import static jattbrand.projectDj6.data.RunTimeData.isAlbum_loaded;

public class DetailsActivity extends AppCompatActivity {


    private TextView titleTxt;
// 
    private TextView heading;
    private ImageButton backBtn;
// 
    private ImageButton more_optionsBtn;
    private RecyclerView recyclerView;
    private ImageView coverImage;
    private String pageUrl;
    private String name;
    private String cover;
    private final String TAG = "DeatilsActivity.class ===== ";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        isAlbum_loaded = false;
        RunTimeData.currentAlbumsData = null;
        Log.e(TAG, "onCreate");

        if(getIntent() == null){
           finish();
        }

        pageUrl = getIntent().getStringExtra("pageUrl");
        name = getIntent().getStringExtra("name");
        cover = getIntent().getStringExtra("cover");

        new getAlbumData(pageUrl).start();

        initIds();

        Glide
                .with(getApplicationContext())
                .load(cover)
                .into(coverImage);
        titleTxt.setText(name);

    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.e(TAG, "onPostCreate");

        new getAlbumData(pageUrl).start();

        while(true) {
            if (isAlbum_loaded) {
                Log.e("Album ", "Loaded");
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new CustomListsAdapter(RunTimeData.currentAlbumsData));
                break;
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "onResume");

        while(true) {
            if (isAlbum_loaded) {
                Log.e("Album ", "Loaded");
                recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                recyclerView.setAdapter(new CustomListsAdapter(RunTimeData.currentAlbumsData));
                break;
            }
        }
    }

    private void initIds() {

        titleTxt = findViewById(R.id.detail_title);
        heading = findViewById(R.id.detail_text_heading);
        backBtn = findViewById(R.id.detail_back_btn);
        more_optionsBtn = findViewById(R.id.detail_more_options);
        recyclerView = findViewById(R.id.detailRecycler);
        coverImage = findViewById(R.id.detail_cover);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RunTimeData.currentAlbumsData = null;
        isAlbum_loaded = false;
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        finish();
    }
}
