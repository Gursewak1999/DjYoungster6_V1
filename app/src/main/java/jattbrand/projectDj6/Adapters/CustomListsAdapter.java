package jattbrand.projectDj6.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import jattbrand.projectDj6.Helper.Teleportor;
import jattbrand.projectDj6.R;
import jattbrand.projectDj6.data.dataTypes.AlbumData;
import jattbrand.projectDj6.data.dataTypes.RecSongsData;
import jattbrand.projectDj6.data.dataTypes.SongData;

public class CustomListsAdapter extends RecyclerView.Adapter<CustomListsAdapter.SearchViewHolder> {

    private Context context;
    private ArrayList<RecSongsData> searchData;
    private int type = -1;
    private AlbumData albumData;
    private ArrayList<ArrayList<String>> data;

    public CustomListsAdapter(ArrayList<RecSongsData> searchSongsData) {
        type = 0;
        this.searchData = searchSongsData;
    }

    public CustomListsAdapter(AlbumData albumData) {
        type = 1;
        this.albumData = albumData;
    }

    CustomListsAdapter(Context context, ArrayList<ArrayList<String>> data) {
        this.context = context;
        this.data = data;
        type = 2;
    }

    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        context = viewGroup.getContext();
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.custom_linear_song_list, viewGroup, false);

        return new SearchViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final SearchViewHolder viewHolder, final int position) {
        //working mechanism here

        viewHolder.heading.setVisibility(View.GONE);
        switch (type) {
            case 0:
                viewHolder.title.setText(searchData.get(position).getName());
                viewHolder.artist.setText(searchData.get(position).getArtistName());
                //  Glide.with(context).load(searchData.get(position).getCoverUrl()).into(viewHolder.cover);
                break;
            case 1:

                viewHolder.title.setText(albumData.getSongName().get(viewHolder.getAdapterPosition()));
                viewHolder.artist.setText(albumData.getSongArtist().get(viewHolder.getAdapterPosition()));
                break;
            case 2:

                viewHolder.title.setText(data.get(0).get(viewHolder.getAdapterPosition()));
                viewHolder.artist.setText(data.get(2).get(viewHolder.getAdapterPosition()));
                break;

        }
        viewHolder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SongData songData = new SongData();
                switch (type) {
                    case 0:
                        songData.setArtistName(searchData.get(position).getArtistName());
                        songData.setName(searchData.get(position).getName());
                        songData.setCoverUrl("");
                        songData.setPageUrl(searchData.get(position).getPageUrl());
                        break;
                    case 1:
                        songData.setArtistName(albumData.getSongArtist().get(viewHolder.getAdapterPosition()));
                        songData.setName(albumData.getSongName().get(viewHolder.getAdapterPosition()));
                        songData.setCoverUrl("");
                        songData.setPageUrl(albumData.getSongUrl().get(viewHolder.getAdapterPosition()));
                        break;
                    case 2:
                        songData.setArtistName(data.get(2).get(viewHolder.getAdapterPosition()));
                        songData.setName(data.get(0).get(viewHolder.getAdapterPosition()));
                        songData.setCoverUrl(data.get(1).get(viewHolder.getAdapterPosition()));
                        songData.setPageUrl(data.get(3).get(viewHolder.getAdapterPosition()));
                        break;
                }

                new Teleportor(context, songData);
            }
        });
        viewHolder.playBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewHolder.title.callOnClick();
            }
        });
    /*    viewHolder.optionBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Context wrapper = new ContextThemeWrapper(context, R.style.YOURSTYLE);
              //  PopupMenu popup = new PopupMenu(wrapper, view);
                PopupMenu popup = new PopupMenu(wrapper, viewHolder.optionBtn);
                popup.inflate(R.menu.menu_song_options);
                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.download:
                                //handle menu1 click
                                break;
                            case R.id.addFav:
                                //handle menu2 click
                                break;
                            case R.id.addToPlaylist:
                                //handle menu3 click
                                break;
                        }
                        return false;
                    }
                });
                //displaying the popup
                popup.show();
            }
        });
   */
    }

    @Override
    public int getItemCount() {

        int count = 0;
        switch (type) {
            case 0:
                count = searchData.size();
                break;
            case 1:
                count = albumData.getSongName().size();
                break;
            case 2:
                count = data.get(0).size();
                break;
        }
        return count;
    }

    class SearchViewHolder extends RecyclerView.ViewHolder {

        final TextView title;
        final TextView artist, heading;
        final ImageView cover;
        final ImageButton playBtn, optionBtn;

        SearchViewHolder(@NonNull View itemView) {
            super(itemView);

            heading = itemView.findViewById(R.id.lists_heading);
            title = itemView.findViewById(R.id.list_name);
            artist = itemView.findViewById(R.id.list_artist);
            cover = itemView.findViewById(R.id.list_cover);
            playBtn = itemView.findViewById(R.id.list_btn_play);
            optionBtn = itemView.findViewById(R.id.list_btn_options);
        }
    }


}

