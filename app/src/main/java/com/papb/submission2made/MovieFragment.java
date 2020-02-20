package com.papb.submission2made;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MovieFragment extends Fragment {
    private RecyclerView rvMovie;
    private ArrayList<Movie> list = new ArrayList<>();
    private ProgressBar progressBar;

    public MovieFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);

        rvMovie = rootView.findViewById(R.id.rv_movie);
        rvMovie.setHasFixedSize(true);

        list.addAll(getListMovie());
        showRecyclerList();


        return rootView;

    }

     private ArrayList<Movie> getListMovie() {
        String[] dataName = getResources().getStringArray(R.array.data_name);
        String[] dataDesc = getResources().getStringArray(R.array.data_description);
        @SuppressLint("Recycle") TypedArray dataPhoto = getResources().obtainTypedArray(R.array.data_photo);

        ArrayList<Movie> listMovie = new ArrayList<>();
        for (int i = 0 ; i< dataName.length; i++){
            Movie movie = new Movie();
            movie.setTitle(dataName[i]);
            movie.setDescription(dataDesc[i]);
            movie.setPhoto(dataPhoto.getResourceId(i,-1));

            listMovie.add(movie);
        }
        return listMovie;
    }


    private void showRecyclerList() {
        rvMovie.setLayoutManager(new LinearLayoutManager(getActivity()));
        MovieAdapter movieAdapter = new MovieAdapter(list);
        rvMovie.setAdapter(movieAdapter);

        movieAdapter.setOnItemClickCallback(new MovieAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(Movie data) {
                Intent intent = new Intent(getActivity() , DetailActivity.class);
                intent.putExtra("EXTRA_MOVIE", data);
                showLoading(true);
                startActivity(intent);
            }
        });
    }

    private void showLoading(Boolean state) {
        if (state) {
            progressBar.setVisibility(View.VISIBLE);
        } else {
            progressBar.setVisibility(View.GONE);
        }
    }


}