package com.papb.submission2made;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class MovieViewModel extends ViewModel {
    private MutableLiveData<ArrayList<Movie>> listMovies = new MutableLiveData<>();

    void setMovie(final String movie){
        final ArrayList<Movie> listItem = new ArrayList<>();

        String apiKey = "5ce8f4065cb8106d3cbd5947796e6c2a";
        //Ambil data movie (bukan tv show)
        String url = "https://api.themoviedb.org/3/discover/movie?api_key="+apiKey+"&language=en-US";

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(url, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                try {
                    //parse json retrieve data
                    String result = new String(responseBody);
                    JSONObject responObject = new JSONObject(result);
                    JSONArray list =  responObject.getJSONArray("list");

                    for (int i = 0 ; i< list.length(); i++){
                        JSONObject movie = list.getJSONObject(i);
                        Movie movieItem = new Movie();
                        movieItem.setId(movie.getInt("movie_id"));
                        movieItem.setTitle(movie.getString("title"));
                        movieItem.setDescription(movie.getString("overview"));
                        listItem.add(movieItem);
                    }
                    listMovies.postValue(listItem);
                }
                catch (Exception e){
                    Log.d("Exception", e.getMessage());
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {
                Log.d("onFailure", error.getMessage());
            }
        });

    }

    LiveData<ArrayList<Movie>> getMovie() {
        return listMovies;
    }
}
