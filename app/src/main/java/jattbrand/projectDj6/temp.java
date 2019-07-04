package jattbrand.projectDj6;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.github.florent37.materialviewpager.MaterialViewPager;

import java.util.ArrayList;

import jattbrand.projectDj6.Adapters.SongsPagerAdapter;
import jattbrand.projectDj6.data.dataTypes.RecSongsData;
import jattbrand.projectDj6.data.dataTypes.TrendingSongsData;

public class temp extends AppCompatActivity {

    public static ArrayList<TrendingSongsData> trendingData;
    public static ArrayList<RecSongsData> recommendedSongs;
    public static ArrayList<RecSongsData> latestSongs;
    public static int pos = -1;
    private static MaterialViewPager mViewPager;
    public static ImageView cover;
    private String coverStr;


    // --Commented out by Inspection (23/06/2019 12:43 PM):public static int type = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_temp);

    /*    if(pos == 5){
            findViewById(R.id.materialTempViewPager).setVisibility(View.GONE);
            findViewById(R.id.materialTempViewPager2).setVisibility(View.VISIBLE);
            mViewPager = findViewById(R.id.materialTempViewPager2);
        }else
          mViewPager = findViewById(R.id.materialTempViewPager);

//*/
        mViewPager = findViewById(R.id.materialTempViewPager);

        final Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);

        String name = "";

        ArrayList<String> dataName, dataUrl, dataCover, dataArtist;
        dataName = dataUrl = dataCover = dataArtist = new ArrayList<>();

        if (getIntent() != null) {
            name = getIntent().getStringExtra("name");
            if (pos != -1) {
                dataName = getIntent().getStringArrayListExtra("dataName");
                dataUrl = getIntent().getStringArrayListExtra("dataUrl");
                dataCover = getIntent().getStringArrayListExtra("dataCover");
                dataArtist = getIntent().getStringArrayListExtra("dataArtist");
            }
        }
        TextView header = mViewPager.getRootView().findViewById(R.id.logo_white);
        header.setText(name);
        cover = mViewPager.getRootView().findViewById(R.id.logo_white_image);
        if (pos == 5) {
            coverStr = getIntent().getStringExtra("cover");
            Glide.with(getApplicationContext())
                    .asBitmap()
                    .load(coverStr)
                    .transform(new RoundedCorners(16))
                    .into(cover);
        }

        // mViewPager.getViewPager().getAdapter(new SongsPagerAdapter(getApplicationContext(), dataName, dataArtist, dataCover, dataUrl));

        final ArrayList<String> finalDataName1 = dataName;
        final ArrayList<String> finalDataArtist1 = dataArtist;
        final ArrayList<String> finalDataUrl1 = dataUrl;

        name = getIntent().getStringExtra("name");
        final String finalName = name;
        mViewPager.getViewPager().setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager()) {

            @Override
            public Fragment getItem(int position) {
                switch (pos) {
                    case 0:
                        return SongsPagerAdapter.newInstance(getApplicationContext(), trendingData);
                    case 1:
                        return SongsPagerAdapter.newInstance(getApplicationContext(), recommendedSongs, 0);
                    case 2:
                        return SongsPagerAdapter.newInstance(getApplicationContext(), latestSongs, 0);
                    case -1:
                        return SongsPagerAdapter.newInstance(getApplicationContext(), finalDataName1, finalDataArtist1, finalDataUrl1);
                    case 4:
                        return SongsPagerAdapter.newInstance(getApplicationContext(), "localMusic");
                    case 5:
                        return SongsPagerAdapter.newInstance(getApplicationContext(), finalName, 0);

                    default:
                        return SongsPagerAdapter.newInstance();
                }
//                notifyDataSetChanged();
            }

            @Override
            public int getCount() {
                return 1;
            }

        });

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //What to do on back clicked
                onBackPressed();
            }
        });

    }

}
