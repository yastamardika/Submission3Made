package com.papb.submission2made;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ListViewHolder> {

    private ArrayList<Movie> listMovie;
    private OnItemClickCallback onItemClickCallback;
    private View.OnClickListener onItemClickListener;

//
//    public void setOnItemClickListener(View.OnClickListener clickListener){
//        onItemClickListener = clickListener;
//    }

    public void setOnItemClickCallback(OnItemClickCallback onItemClickCallback) {
        this.onItemClickCallback = onItemClickCallback;
    }

    public MovieAdapter(ArrayList<Movie> list){
        this.listMovie = list;
    }
    @NonNull
    @Override
    public ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
       return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final ListViewHolder holder, int position) {
        Movie movie = listMovie.get(position);
        holder.imgPhoto.setImageResource(movie.getPhoto());
        holder.tvName.setText(movie.getTitle());
        holder.tvDesc.setText(movie.getDescription());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClickCallback.onItemClicked(listMovie.get(holder.getAdapterPosition()));
            }
        });


    }

    @Override
    public int getItemCount() {
        return listMovie.size();
    }

    public void setMovies(ArrayList<Movie> movies) { this.listMovie = movies;
    }

    public class ListViewHolder extends RecyclerView.ViewHolder{
        ImageView imgPhoto;
        TextView tvName , tvDesc;

        public ListViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_item_movie);
            tvName = itemView.findViewById(R.id.tv_moviename);
            tvDesc = itemView.findViewById(R.id.tv_deskripsi);
            itemView.setTag(this);
            itemView.setOnClickListener(onItemClickListener);
        }

        public void onClick(View v){
            int pos = getAdapterPosition();
            String title = listMovie.get(pos).getTitle();


        }
    }

    public interface OnItemClickCallback{
        void onItemClicked(Movie data);
    }

}
