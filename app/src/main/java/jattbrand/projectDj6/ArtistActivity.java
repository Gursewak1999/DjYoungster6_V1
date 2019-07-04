package jattbrand.projectDj6;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.jackandphantom.blurimage.BlurImage;

import jattbrand.projectDj6.Adapters.CustomGridAdapter;
import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.processes.getArtistData;

public class ArtistActivity extends AppCompatActivity {

    // --Commented out by Inspection (23/06/2019 12:43 PM):private sta// --Commented out by Inspection (23/06/2019 12:43 PM):tic ProgressBar progressBar;

    private String name, url, cover;
    private ImageView coverImg, cover_bgImg;
    private TextView titleTxt;
    private static RecyclerView artistRecycler;
    private static Context context;
    private static ProgressBar progressbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_artist);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RunTimeData.isArtistActivity = true;
        context = getApplicationContext();
        if (getIntent() == null) {
            Toast.makeText(this, "An Error Occured While Loading", Toast.LENGTH_SHORT).show();
            finish();
        }

        name = getIntent().getStringExtra("name");
        cover = getIntent().getStringExtra("cover");

        new getArtistData(name).start();

        progressbar = findViewById(R.id.artist_progressbar);
        progressbar.setVisibility(View.VISIBLE);
        coverImg = findViewById(R.id.artist_cover);
        cover_bgImg = findViewById(R.id.artist_bg_cover);
        titleTxt = findViewById(R.id.artist_name);
        artistRecycler = findViewById(R.id.artist_Recycler);

        titleTxt.setText(name);

        Glide.with(getApplicationContext())
                .asBitmap()
                .load(cover)
                .transform(new RoundedCorners(16))
                .addListener(new RequestListener<Bitmap>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Bitmap> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(final Bitmap resource, Object model, Target<Bitmap> target, DataSource dataSource, boolean isFirstResource) {

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                coverImg.setImageBitmap(resource);
                                BlurImage.with(getApplicationContext())
                                        .load(resource)
                                        .intensity(15)
                                        .Async(false)
                                        .into(cover_bgImg);
                            }
                        });
                        return false;
                    }
                }).submit();
        final int[] artistLen = {250};
        final int[] recyclerLen = {600};
        final int minWidth = 100;
        artistRecycler.addOnScrollListener(new RecyclerView.OnScrollListener() {
            float state = 0.0f;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, final int dy) {
                state += dy;

                ImageView img = findViewById(R.id.artist_cover);
                if (dy > 0) {
                    //going up

                    if(artistLen[0]>50) {
                        artistLen[0] -= 10;
                        recyclerLen[0] += 20;
                    }

                    //Log.e("Recycler :;", "Going Up " + recyclerLen);
                }
                if (dy < 0) {
                    //going down
                    if (artistLen[0]<250) {
                        artistLen[0] += 5;
                        recyclerLen[0] -= 10;
                    }
                    //Log.e("Recycler :;", "Going Down " + recyclerLen);
                }
                //   if (artistLen[0]<240 && artistLen[0]>0) {
                img.getLayoutParams().height = artistLen[0];
                img.getLayoutParams().width = artistLen[0];
                artistRecycler.getLayoutParams().height = recyclerLen[0];
                //  }
                img.requestLayout();

            }
        });


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        RunTimeData.isArtistActivity = false;

    }

    public static void loadData() {
        Log.e("Artist Data Size :: ", RunTimeData.artistData.getSongName().size() + "gh");

        new loadAsyncData().execute();

    }

    private static class loadAsyncData extends AsyncTask<Void, Void, Void> {


        @Override
        protected Void doInBackground(Void... voids) {

            while (true) {

                if (RunTimeData.isArtist_loaded) {
                    break;
                }
            }
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            artistRecycler.setLayoutManager(new GridLayoutManager(context, 3));
          //  artistRecycler.setAdapter(new TestRecyclerViewAdapter(context, RunTimeData.artistData));
            artistRecycler.setAdapter(new CustomGridAdapter( RunTimeData.artistData));
            progressbar.setVisibility(View.GONE);
            artistRecycler.setBackgroundColor(Color.TRANSPARENT);
        }
    }
}
