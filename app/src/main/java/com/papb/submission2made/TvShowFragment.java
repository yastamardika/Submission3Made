package com.papb.submission2made;


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
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class TvShowFragment extends Fragment {
    private RecyclerView rvTvShow;
    private ArrayList<Movie> list = new ArrayList<>();
    private ProgressBar progressBar;

    public TvShowFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView;
        rootView = inflater.inflate(R.layout.fragment_tv_show, container, false);

        rvTvShow = rootView.findViewById(R.id.rv_tvshow);
        rvTvShow.setHasFixedSize(true);

        list.addAll(getListTvShow());
        showRecyclerList();
        return rootView;

    }
    ArrayList<Movie> getListTvShow() {
        String[] dataName = getResources().getStringArray(R.array.tv_show);
        String[] dataDesc = getResources().getStringArray(R.array.tv_show_description);
        TypedArray dataPhoto = getResources().obtainTypedArray(R.array.tv_show_photo);

        ArrayList<Movie> listTvShow = new ArrayList<>();
        for (int i = 0 ; i< dataName.length; i++){
            Movie movie = new Movie();
            movie.setTitle(dataName[i]);
            movie.setDescription(dataDesc[i]);
            movie.setPhoto(dataPhoto.getResourceId(i,-1));

            listTvShow.add(movie);
        }
        return listTvShow;
    }


    private void showRecyclerList() {
        rvTvShow.setLayoutManager(new LinearLayoutManager(getActivity()));
        MovieAdapter movieAdapter = new MovieAdapter(list);
        rvTvShow.setAdapter(movieAdapter);

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
