package com.padc.csh.themovieapp.mvp.views

import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapp.data.vos.GenreVO
import com.padc.csh.themovieapp.data.vos.MovieVO

//to send action to view from presenter
interface MainView :BaseView{

    fun showNowPlayingMovies(nowPlayingMovieList:List<MovieVO>)
    fun showPopularMovies(popularMovieList:List<MovieVO>)
    fun showTopRatedMovies(topRatedMovieList:List<MovieVO>)
    fun showGenres(genreList:List<GenreVO>)
    fun showMoviesByGenre(movieByGenre:List<MovieVO>)
    fun showActors(actorList: List<ActorVO>)
    fun navigateToMovieDetailScrn(movieId:Int)
}