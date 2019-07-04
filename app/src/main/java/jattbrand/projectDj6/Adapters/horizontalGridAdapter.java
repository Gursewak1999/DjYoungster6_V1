package jattbrand.projectDj6.Adapters;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import jattbrand.projectDj6.DetailsActivity;
import jattbrand.projectDj6.PlayerActivity;
import jattbrand.projectDj6.R;
import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.temp;


class horizontalGridAdapter extends RecyclerView.Adapter<horizontalGridAdapter.horizontalViewHolder> {

    private Context context;
    private final int pos;

    public horizontalGridAdapter(int pos) {
        this.pos = pos;
    }

    @NonNull
    @Override
    public horizontalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_cards, viewGroup, false);

        return new horizontalViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final horizontalViewHolder viewHolder, final int position) {

        if(position != (RunTimeData.homeData.get(pos).getNameArray().size())){
            viewHolder.textView.setText(RunTimeData.homeData.get(pos).getNameArray().get(viewHolder.getAdapterPosition()));
            viewHolder.textView.setSingleLine(true);
            viewHolder.textView.setEllipsize(TextUtils.TruncateAt.END);
            Glide.with(context)
                    .load(RunTimeData.homeData.get(pos).getImageArray().get(viewHolder.getAdapterPosition()))
                    .placeholder(R.drawable.songs_placeholder)
                    .into(viewHolder.imageCover);
        }else {
            viewHolder.textView.setText(RunTimeData.homeData.get(pos).getHeading());
            viewHolder.textView.setSingleLine(true);
            viewHolder.textView.setEllipsize(TextUtils.TruncateAt.END);
            viewHolder.imageCover.setBackgroundColor(context.getResources().getColor(R.color.colorBg));
            Glide.with(context)
                    .load(R.drawable.shuffle_28_512)
                    .into(viewHolder.imageCover);
        }

        viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO change it later
                if(position != (RunTimeData.homeData.get(pos).getNameArray().size())) {
                   // Log.e("current Song ", RunTimeData.homeData.get(pos).getUrlArray().get(viewHolder.getAdapterPosition()));
                   if(pos>=2){

                       context.startActivity(new Intent(context, DetailsActivity.class).putExtra("pageUrl",RunTimeData.homeData.get(pos).getUrlArray().get(viewHolder.getAdapterPosition()))
                               .putExtra("cover", RunTimeData.homeData.get(pos).getImageArray().get(viewHolder.getAdapterPosition()))
                               .putExtra("name", RunTimeData.homeData.get(pos).getNameArray().get(viewHolder.getAdapterPosition())));

                   }else {

                       context.startActivity(new Intent(context, PlayerActivity.class)
                               .putExtra("url",RunTimeData.homeData.get(pos).getUrlArray().get(viewHolder.getAdapterPosition()))
                               .putExtra("cover", RunTimeData.homeData.get(pos).getImageArray().get(viewHolder.getAdapterPosition()))
                               .putExtra("name", RunTimeData.homeData.get(pos).getNameArray().get(viewHolder.getAdapterPosition()))
                               .putExtra("artist", RunTimeData.homeData.get(pos).getArtistArray().get(viewHolder.getAdapterPosition())));
                   }
                }else{
                   //TODO
                    context.startActivity(new Intent(context, temp.class)
                            .putExtra("name", RunTimeData.homeData.get(pos).getHeading())
                            .putStringArrayListExtra("dataName", RunTimeData.homeData.get(pos).getNameArray())
                            .putStringArrayListExtra("dataArtist", RunTimeData.homeData.get(pos).getArtistArray())
                            .putStringArrayListExtra("dataImage", RunTimeData.homeData.get(pos).getImageArray())
                            .putStringArrayListExtra("dataUrl", RunTimeData.homeData.get(pos).getUrlArray()));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return RunTimeData.homeData.get(pos).getNameArray().size()+1 ;
    }

     class horizontalViewHolder extends RecyclerView.ViewHolder{

        final ImageView imageCover;
        final CustomTextView textView;
        final CardView cardView;

         horizontalViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.horizontal_card);
            textView = itemView.findViewById(R.id.card_text);
            imageCover = itemView.findViewById(R.id.card_image);
        }
    }
}
