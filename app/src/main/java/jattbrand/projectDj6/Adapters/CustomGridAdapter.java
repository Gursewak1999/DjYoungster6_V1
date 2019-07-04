package jattbrand.projectDj6.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import jattbrand.projectDj6.Helper.Teleportor;
import jattbrand.projectDj6.R;
import jattbrand.projectDj6.data.dataTypes.AlbumData;
import jattbrand.projectDj6.data.dataTypes.ArtistData;
import jattbrand.projectDj6.data.dataTypes.SongData;

public class CustomGridAdapter extends RecyclerView.Adapter<CustomGridAdapter.artistViewHolder> {

    private ArtistData artistData = new ArtistData();
    private Context context;
    private int type = -1;
    private AlbumData albumData;

    public CustomGridAdapter(ArtistData artistData) {
        type =0;
        this.artistData = artistData;
    }
    public CustomGridAdapter(AlbumData albumData) {
       type = 1;
       this.albumData = albumData;
    }

    public CustomGridAdapter(Context context, ArrayList<ArrayList<String>> data) {

    }

    @NonNull
    @Override
    public artistViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_cards, viewGroup, false);

        return new artistViewHolder(view) ;
    }

    @Override
    public void onBindViewHolder(@NonNull final artistViewHolder viewHolder, final int position) {
        //working mechanism here

        switch (type) {
            case 0 : viewHolder.name.setText(artistData.getSongName().get(position));
                     Glide.with(context).load(artistData.getSongCover().get(position)).into(viewHolder.cover);
                     break;


        }
        viewHolder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongData songData = new SongData();
                songData.setArtistName(artistData.getSongArtist().get(position));
                songData.setName(artistData.getSongName().get(position));
                songData.setCoverUrl(artistData.getSongCover().get(position));
                songData.setPageUrl(artistData.getSongUrl().get(position));
                new Teleportor(context, songData);
            }
        });
        viewHolder.cover.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongData songData = new SongData();
                songData.setArtistName(artistData.getName());
                songData.setName(artistData.getSongName().get(position));
                songData.setCoverUrl(artistData.getSongCover().get(position));
                songData.setPageUrl(artistData.getSongUrl().get(position));
                new Teleportor(context, songData);
            }
        });
    }

    @Override
     public int getItemCount() {

        int count =0;
        switch(type){
            case 0 : count = artistData.getSongName().size(); break;
            case 1 : count = albumData.getSongName().size();
                break;
        }
        return count;
    }

    class  artistViewHolder extends RecyclerView.ViewHolder {

        private final TextView name;
        private final ImageView cover;

        artistViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.card_text);
            name.setSingleLine();
            cover = itemView.findViewById(R.id.card_image);
        }
    }


}

