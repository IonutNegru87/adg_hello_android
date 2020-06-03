package com.inegru.siit.myapplication.week8;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.inegru.siit.myapplication.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkActivity extends AppCompatActivity {

    private static final String TAG = "NetworkActivity";
    private MovieService movieService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        movieService = NetworkHelper.getRetrofit().create(MovieService.class);
    }

    public void getTopRatedMovies(View view) {
        movieService.topRatedMovies(1).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {
                MovieResult movieResult = response.body();
                if (movieResult == null)
                    return;
                List<Result> resultList = movieResult.getResults();
                if (resultList == null)
                    return;

                for (Result result : resultList) {
                    Log.d(TAG, "onResponse: result=" + result);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {
                Log.e(TAG, "Failed to get top rated movies", t);
            }
        });
    }

    public void getUpcomingMovies(View view) {
        movieService.upcomingMovies(1).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {

            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {

            }
        });
    }

    public void getNowPlayingMovies(View view) {
        movieService.nowPlayingMovies(1).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {

            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {

            }
        });
    }
}
