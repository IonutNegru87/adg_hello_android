package com.inegru.siit.myapplication.week8;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface MovieService {
    String PAGE_QUERY = "page";

    @GET("3/movie/top_rated")
    Call<MovieResult> topRatedMovies(@Query(PAGE_QUERY) int page);

    @GET("3/movie/upcoming")
    Call<MovieResult> upcomingMovies(@Query(PAGE_QUERY) int page);

    @GET("3/movie/now_playing")
    Call<MovieResult> nowPlayingMovies(@Query(PAGE_QUERY) int page);
}
