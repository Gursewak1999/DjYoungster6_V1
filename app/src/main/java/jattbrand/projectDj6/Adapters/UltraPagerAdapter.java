package jattbrand.projectDj6.Adapters;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;

import jattbrand.projectDj6.R;
import jattbrand.projectDj6.data.RunTimeData;

class UltraPagerAdapter extends PagerAdapter {
    private final boolean isMultiScr;
    private Context context;

    public UltraPagerAdapter(boolean isMultiScr) {
        this.isMultiScr = isMultiScr;
    }

    @Override
    public int getCount() {
        return 5;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup view, final int position) {
        context = view.getContext();
        CardView cardView = new CardView(view.getContext());

        ImageView imageView = new ImageView(view.getContext());
        //imageView.setImageResource(R.drawable.clock_solid);

        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        Glide.with(view.getContext())
                .load(RunTimeData.youtubeData.get(position%RunTimeData.youtubeData.size()).getCover())
                .centerCrop()
                .transform(new RoundedCorners(20))
                .placeholder(R.drawable.songs_placeholder)
                .into(imageView);

        RelativeLayout.LayoutParams rlp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);


        imageView.setLayoutParams(rlp);
        // cardView.setLayoutParams(new WindowManager.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        cardView.setBackgroundColor(view.getResources().getColor(R.color.colorPrimary));

        cardView.setPadding(8,8,8,8);
        cardView.setRadius(16);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            cardView.setElevation(4);
        }
        cardView.addView(imageView);
        view.addView(cardView, rlp);

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(RunTimeData.youtubeData.get(position%RunTimeData.youtubeData.size()).getUrl())));
            }
        });
        return cardView;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        CardView view = (CardView) object;
        container.removeView(view);
    }

}
