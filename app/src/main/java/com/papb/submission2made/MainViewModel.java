package com.papb.submission2made;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;

public class MainViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

    void setMovie(final String movie){
        final ArrayList<Movie> listMovies = new ArrayList<>();

        String apiKey = "";
        String url = "";
    }

    LiveData<ArrayList<Movie>> getWeathers() {
        return listMovies;
    }
}
