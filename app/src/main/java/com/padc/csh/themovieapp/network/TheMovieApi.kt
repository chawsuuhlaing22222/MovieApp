package com.padc.csh.themovieapp.network

import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.network.responses.ActorListResponse
import com.padc.csh.themovieapp.network.responses.GenreListResponse
import com.padc.csh.themovieapp.network.responses.MovieCreditResponse
import com.padc.csh.themovieapp.network.responses.MovieListResponse
import com.padc.csh.themovieapp.utils.*
import io.reactivex.rxjava3.core.Observable
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TheMovieApi {
//
//    @GET(API_GET_NOW_PLAYING)
//    fun getNowPlayingMovies(
//        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
//        @Query(PARAM_PAGE) page:Int =1
//    ): Call<MovieListResponse>

    @GET(API_GET_NOW_PLAYING)
    fun getNowPlayingMovies(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int =1
    ): Observable<MovieListResponse>

    @GET(API_GET_POPULAR_MOVIES)
    fun getPopularMovies(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int =1
    ): Observable<MovieListResponse>

    @GET(API_GET_TOP_RATED_MOVIES)
    fun getTopRatedMovies(
        @Query(PARAM_API_KEY) apiKey:String = MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int =1
    ): Observable<MovieListResponse>

    @GET(API_GET_GENRE_LIST)
    fun getGenreList(
        @Query(PARAM_API_KEY)  api_key:String= MOVIE_API_KEY
    ):Observable<GenreListResponse>

    @GET(API_GET_MOVIE_BY_GENRE)
    fun getMoviesByGenre(
        @Query(PARAM_API_KEY)  api_key:String= MOVIE_API_KEY,
        @Query(PARAM_WITH_GENRE) with_genre:String
    ):Observable<MovieListResponse>

    @GET(API_GET_POPULAR_ACTORS)
    fun getActors(
        @Query(PARAM_API_KEY)  api_key:String= MOVIE_API_KEY,
        @Query(PARAM_PAGE) page:Int=1
    ):Observable<ActorListResponse>

    @GET("${API_GET_MOVIE_DETAIL}{movie_id}")
    fun getMovieDetail(
        @Path(PATH_PARAM_MOVIE_ID) movieId:String,
        @Query(PARAM_API_KEY)  api_key:String= MOVIE_API_KEY

    ):Observable<MovieVO>

    @GET("${API_GET_CREDITS_BY_MOVIE}{movie_id}/credits")
    fun getCreditByMovie(
        @Path(PATH_PARAM_MOVIE_ID) movieId:String,
        @Query(PARAM_API_KEY)  api_key:String= MOVIE_API_KEY

    ):Observable<MovieCreditResponse>


    @GET("$API_MOVIE_SEARCH")
    fun searchMovie(
        @Query(PARAM_API_KEY)  api_key:String= MOVIE_API_KEY,
        @Query(PARAM_QUERY)  query:String

    ):Observable<MovieListResponse>




}