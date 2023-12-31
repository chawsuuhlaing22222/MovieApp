package com.padc.csh.themovieapp.network.dataagents

import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapp.data.vos.GenreVO
import com.padc.csh.themovieapp.data.vos.MovieVO

interface MovieDataAgent {

    fun getNowPlayingMovies(
        onSuccess:(List<MovieVO>)->Unit,
        onFailure:(String)->Unit
    )

    fun getPopularMovies(
        onSuccess:(List<MovieVO>)->Unit,
        onFailure:(String)->Unit
    )

    fun getTopRatedMovies(
        onSuccess:(List<MovieVO>)->Unit,
        onFailure:(String)->Unit
    )

    fun getGenreList(
        onSuccess: (List<GenreVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMoviesByGenre(
        genreId:String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getActorList(
        onSuccess: (List<ActorVO>) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getMovieDetail(
        movieId:String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    )

    fun getCreditsByMovie(
        movieId:String,
        onSuccess: (Pair<List<ActorVO>,List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    )
}