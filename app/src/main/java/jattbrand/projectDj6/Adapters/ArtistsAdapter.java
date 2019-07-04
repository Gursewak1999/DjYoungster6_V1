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

import jattbrand.projectDj6.R;
import jattbrand.projectDj6.data.RunTimeData;
import jattbrand.projectDj6.temp;


class ArtistsAdapter extends RecyclerView.Adapter<ArtistsAdapter.artistViewHolder> {

    private Context context;

    @NonNull
    @Override
    public artistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_cards_round, viewGroup, false);

        return new artistViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final artistViewHolder viewHolder, final int position) {

            viewHolder.textView.setText(RunTimeData.topArtists.get(position).getName());

            viewHolder.imageCover.setBackgroundColor(context.getResources().getColor(R.color.colorBg));
            Glide.with(context)
                    .load(RunTimeData.topArtists.get(position).getCoverUrl())
                    .centerCrop()
                    .into(viewHolder.imageCover);
            viewHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                   /* context.startActivity(new Intent(context, ArtistActivity.class)
                                                    .putExtra("name", RunTimeData.topArtists.get(position).getName())
                                                    .putExtra("cover", RunTimeData.topArtists.get(position).getCoverUrl()));
               //*/
                    temp.pos=5;
                    context.startActivity(new Intent(context, temp.class)
                            .putExtra("name",  RunTimeData.topArtists.get(position).getName())
                            .putExtra("cover", RunTimeData.topArtists.get(position).getCoverUrl()));

                }
            });

        //Log.e(RunTimeData.topArtists.get(position).getName(),"  "+RunTimeData.topArtists.get(position).getCoverUrl());
    }

    @Override
    public int getItemCount() {
        return RunTimeData.topArtists.size() ;
    }

    class artistViewHolder extends RecyclerView.ViewHolder{

        final ImageView imageCover;
        final CustomTextView textView;
        final CardView cardView;

        artistViewHolder(@NonNull View itemView) {
            super(itemView);

            cardView = itemView.findViewById(R.id.horizontal_card_round);
            textView = itemView.findViewById(R.id.card_text_round);
            imageCover = itemView.findViewById(R.id.card_image_round);

            textView.setSingleLine(true);
            textView.setEllipsize(TextUtils.TruncateAt.END);
        }
    }
}
