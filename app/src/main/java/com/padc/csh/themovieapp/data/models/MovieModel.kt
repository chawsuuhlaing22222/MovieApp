package com.padc.csh.themovieapp.data.models

import androidx.lifecycle.LiveData
import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapp.data.vos.GenreVO
import com.padc.csh.themovieapp.data.vos.MovieVO
import io.reactivex.rxjava3.core.Observable

interface MovieModel {

//    fun getNowPlayingMovies(
//        onSuccess:(List<MovieVO>)->Unit,
//        onFailure:(String)->Unit
//    )

    fun getNowPlayingMovies(
        onFailure:(String)->Unit
    ):LiveData<List<MovieVO>>?

//    fun getPopularMovies(
//        onSuccess:(List<MovieVO>)->Unit,
//        onFailure:(String)->Unit
//    )

    fun getPopularMovies(
        onFailure:(String)->Unit
    ):LiveData<List<MovieVO>>?

//    fun getTopRatedMovies(
//        onSuccess:(List<MovieVO>)->Unit,
//        onFailure:(String)->Unit
//    )

    fun getTopRatedMovies(
        onFailure:(String)->Unit
    ):LiveData<List<MovieVO>>?

    fun getGenreList(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMoviesByGenre(
        genreId:String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getActors(
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

//    fun getMovieDetail(
//        movieId:String,
//        onSuccess: (MovieVO) -> Unit,
//        onFailure: (String) -> Unit
//    )

    fun getMovieDetail(
        movieId:String,
        onFailure: (String) -> Unit
    ):LiveData<MovieVO?>?

    fun getMovieCredits(
        movieId:String,
        onSuccess: (Pair<List<ActorVO>,List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun searchMovie(name:String):Observable<List<MovieVO>>
}