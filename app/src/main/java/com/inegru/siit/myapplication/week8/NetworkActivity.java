package com.inegru.siit.myapplication.week8;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.inegru.siit.myapplication.R;

import java.io.IOException;
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
                handleMovieResponse(response);
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
                handleMovieResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {
                Log.e(TAG, "Failed to get upcoming movies", t);
            }
        });
    }

    public void getNowPlayingMovies(View view) {
        movieService.nowPlayingMovies(1).enqueue(new Callback<MovieResult>() {
            @Override
            public void onResponse(@NonNull Call<MovieResult> call, @NonNull Response<MovieResult> response) {
                handleMovieResponse(response);
            }

            @Override
            public void onFailure(@NonNull Call<MovieResult> call, @NonNull Throwable t) {
                Log.e(TAG, "Failed to get now playing movies", t);
            }
        });
    }

    private void handleMovieResponse(@NonNull Response<MovieResult> response) {
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

    public void executeAllSync(View view) {
        // Create a new task and execute it
        new ExecuteAllMovieTasks(movieService).execute(1);
    }

    private static class ExecuteAllMovieTasks extends AsyncTask<Integer, Void, Boolean> {
        @NonNull
        private final MovieService movieService;

        ExecuteAllMovieTasks(@NonNull MovieService movieService) {
            this.movieService = movieService;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG, "onPreExecute: started executeAllSync");
        }

        @Override
        protected Boolean doInBackground(Integer... params) {
            Integer page = params[0];
            if (page == null) {
                page = 1; // default to 1 in case it is not provided
            }
            try {
                if (isCancelled()) return false; // check if task is canceled and stop execution if true
                movieService.topRatedMovies(page).execute();
                if (isCancelled()) return false; // check if task is canceled and stop execution if true
                movieService.upcomingMovies(page).execute();
                if (isCancelled()) return false; // check if task is canceled and stop execution if true
                movieService.nowPlayingMovies(page).execute();
            } catch (IOException e) {
                Log.e(TAG, "executeAllSync: Failure", e);
                return false;
            }

            // If we reach this than all requests were successful
            Log.d(TAG, "executeAllSync: Success");
            return true;
        }

        @Override
        protected void onPostExecute(Boolean result) {
            // Do something with the result
            Log.d(TAG, "onPostExecute: finished executeAllSync - " + (result ? "Success" : "Failure"));
        }
    }
}
