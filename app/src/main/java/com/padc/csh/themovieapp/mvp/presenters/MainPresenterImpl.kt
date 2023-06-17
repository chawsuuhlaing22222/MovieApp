package com.padc.csh.themovieapp.mvp.presenters

import android.widget.Toast
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padc.csh.themovieapp.data.models.MovieModel
import com.padc.csh.themovieapp.data.models.MovieModelImpl
import com.padc.csh.themovieapp.data.vos.GenreVO
import com.padc.csh.themovieapp.mvp.views.MainView

class MainPresenterImpl:ViewModel(),MainPresenter {

    //view
    var mView:MainView?=null

    //model
    private var mMovieModel: MovieModel= MovieModelImpl

    private var genreList:List<GenreVO> = listOf()

    override fun initView(view: MainView) {
        mView = view
    }

    override fun onTapGenre(genrePosition: Int) {

        genreList?.getOrNull(genrePosition)?.id?.let { genreId->
            mMovieModel.getMoviesByGenre(genreId = genreId.toString(),
                {
                    //Toast.makeText(this,"genre id $genreId and movie size is ${it.size}", Toast.LENGTH_SHORT).show()
                    mView?.showMoviesByGenre(it)
                },{
                    mView?.showErrorMsg(it)
                }
            )
        }

    }

    override fun onUiReady(owner: LifecycleOwner) {

        //Now Playing Movies
        mMovieModel.getNowPlayingMovies {
            mView?.showErrorMsg(it)
        }?.observe(owner) {
            mView?.showNowPlayingMovies(it)
        }

        //Popular movies
        mMovieModel.getPopularMovies{
            mView?.showErrorMsg(it)
        }?.observe(owner) {
            mView?.showPopularMovies(it)
        }

        //Top Rated Movies(ShowCase)
        mMovieModel.getTopRatedMovies{
            mView?.showErrorMsg(it)
        }?.observe(owner) {
            mView?.showTopRatedMovies(it)
        }

        //get genres
        mMovieModel.getGenreList({
            genreList=it
            mView?.showGenres(it)
            it.firstOrNull()?.id?.let { genreId->
                onTapGenre(genreId)
            }
        },{
            mView?.showErrorMsg(it)
        })

        //get actors
        mMovieModel.getActors({
            mView?.showActors(it)
        },{
            mView?.showErrorMsg(it)
        })

    }

    override fun onTapMovieFromBanner(movieId: Int) {
       mView?.navigateToMovieDetailScrn(movieId)
    }

    override fun onTapMovieFromShowCase(movieId: Int) {
        mView?.navigateToMovieDetailScrn(movieId)
    }

    override fun onTapMovie(movieId: Int) {
        mView?.navigateToMovieDetailScrn(movieId)
    }
}