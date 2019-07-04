package jattbrand.projectDj6.Adapters;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;

import com.tmall.ultraviewpager.UltraViewPager;
import com.tmall.ultraviewpager.transformer.UltraDepthScaleTransformer;

import jattbrand.projectDj6.R;
import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.temp;

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.RecyclerViewHolder> {

    private Context context;

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_main, viewGroup, false);

        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerViewHolder viewHolder, final int position) {

        //  viewHolder.mViewPager.setVisibility(View.GONE);
        viewHolder.horizontalView.setVisibility(View.GONE);
        viewHolder.choicesLayout.setVisibility(View.GONE);

        if (position == 0) {
            // viewHolder.mViewPager.setVisibility(View.VISIBLE);
            viewHolder.ultraViewPager.setVisibility(View.VISIBLE);
            //setupViewPager(viewHolder.mViewPager);
            setupUltraViewPager(viewHolder.ultraViewPager);
           /* final Handler handler = new Handler();

            final Runnable update = new Runnable() {
                public void run() {
                    int currentPage = viewHolder.mViewPager.getCurrentItem()+1;
                    viewHolder.mViewPager.setCurrentItem(currentPage, true);
                }
            };

           new Timer().schedule(new TimerTask() {

                @Override
                public void run() {
                    handler.post(update);
                }
            }, 500, 3500);
*/
        }
       /* if (position > 0 && position < 2) {
            viewHolder.horizontalView.setVisibility(View.VISIBLE);
            Log.e("Headings ", String.valueOf(RunTimeData.homeData.size()));
            viewHolder.headings.setText(RunTimeData.homeData.get(position - 1).getHeading());
            viewHolder.horizontalRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            viewHolder.horizontalRecycler.setAdapter(new horizontalGridAdapter(position - 1));
}
*/
        else if (position == 1) {
            //Choices Graph

            viewHolder.choicesLayout.setVisibility(View.VISIBLE);

            viewHolder.trenImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    temp.trendingData = RunTimeData.trendingSongs;
                    temp.pos = 0;
                    context.startActivity(new Intent(context, temp.class)
                            .putExtra("name", "Trending Songs"));

                }
            });
            viewHolder.recImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    temp.recommendedSongs = RunTimeData.recommendedSongs;
                    temp.pos = 1;
                    context.startActivity(new Intent(context, temp.class)
                            .putExtra("name", "Recommended Songs"));
                }
            });
            viewHolder.latImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    temp.latestSongs = RunTimeData.latestSongs;
                    temp.pos = 2;
                    context.startActivity(new Intent(context, temp.class)
                            .putExtra("name", "Latest Songs"));
                }
            });
            viewHolder.topImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Toast.makeText(context, "Feature Soon", Toast.LENGTH_SHORT).show();
                }
            });


        } else if (position == 2) {

            //show top artists here
            if (RunTimeData.topArtists != null) {
                if (RunTimeData.topArtists.size()==0)
                viewHolder.horizontalView.setVisibility(View.GONE);
                else viewHolder.horizontalView.setVisibility(View.VISIBLE);
                viewHolder.headings.setText("Top Punjabi Artists");
                viewHolder.horizontalRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
                viewHolder.horizontalRecycler.setAdapter(new ArtistsAdapter());

            }
        } else if (position > 2 && position < getItemCount()) {
            viewHolder.horizontalView.setVisibility(View.VISIBLE);
            viewHolder.headings.setText(RunTimeData.homeData.get(position - 3).getHeading());

            viewHolder.horizontalRecycler.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
            viewHolder.horizontalRecycler.setAdapter(new horizontalGridAdapter(position - 3));

        }
    }

    @Override
    public int getItemCount() {
        return RunTimeData.homeData.size() + 3;
    }

    class RecyclerViewHolder extends RecyclerView.ViewHolder {

        // final ViewPager mViewPager;
        final View horizontalView;
        final CustomTextView headings;
        final RecyclerView horizontalRecycler;
        final ImageView cardImage;
        final CustomTextView cardTitle;
        final UltraViewPager ultraViewPager;
        final LinearLayout choicesLayout;

        final CardView trenImg, latImg, recImg, topImg;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);

            ultraViewPager = itemView.findViewById(R.id.ultraViewPager);
            //mViewPager = itemView.findViewById(R.id.pager);
            headings = itemView.findViewById(R.id.main_heading);
            horizontalRecycler = itemView.findViewById(R.id.horizontalRecycler);
            horizontalView = itemView.findViewById(R.id.card_RelativeView);
            cardImage = itemView.findViewById(R.id.card_image);
            cardTitle = itemView.findViewById(R.id.card_text);
            choicesLayout = itemView.findViewById(R.id.choices_linearLayout);

            trenImg = itemView.findViewById(R.id.trending_card);
            recImg = itemView.findViewById(R.id.recommended_card);
            latImg = itemView.findViewById(R.id.latest_card);
            topImg = itemView.findViewById(R.id.top20_card);

        }
    }

    private void setupUltraViewPager(UltraViewPager ultraViewPager) {

        ultraViewPager.setPageTransformer(false, new UltraDepthScaleTransformer());
        ultraViewPager.setPadding(0, 20, 0, 20);
        ultraViewPager.setClipToPadding(false);
        //ultraViewPager.setScrollMargin(20,20);
        ultraViewPager.setMultiScreen(0.8f);
        ultraViewPager.setItemRatio(1.0f);
        ultraViewPager.setRatio(2.0f);
        //ultraViewPager.setAutoMeasureHeight(true);

        ultraViewPager.setScrollMode(UltraViewPager.ScrollMode.HORIZONTAL);
//initialize UltraPagerAdapter，and add child view to UltraViewPager
        PagerAdapter adapter = new UltraPagerAdapter(false);
        ultraViewPager.setAdapter(adapter);
//initialize built-in indicator
        ultraViewPager.initIndicator();
//set style of indicators
        ultraViewPager.getIndicator()
                .setOrientation(UltraViewPager.Orientation.HORIZONTAL)
                .setFocusColor(Color.BLACK)
                .setStrokeColor(Color.WHITE)
                .setStrokeWidth(1)
                .setNormalColor(Color.WHITE)
                .setRadius((int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 3, context.getResources().getDisplayMetrics()));
//set the alignment
        ultraViewPager.getIndicator().setGravity(Gravity.CENTER_HORIZONTAL | Gravity.BOTTOM);
        ultraViewPager.getIndicator().setMargin(0, 0, 0, 10);
//construct built-in indicator, and add it to  UltraViewPager
        ultraViewPager.getIndicator().build();

//set an infinite loop
        ultraViewPager.setInfiniteLoop(true);
//enable auto-scroll mode
        ultraViewPager.setAutoScroll(3500);

    }
}

