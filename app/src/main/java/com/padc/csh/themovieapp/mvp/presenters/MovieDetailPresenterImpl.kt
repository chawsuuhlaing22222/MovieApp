package com.padc.csh.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import com.padc.csh.themovieapp.data.models.MovieModel
import com.padc.csh.themovieapp.data.models.MovieModelImpl
import com.padc.csh.themovieapp.mvp.views.MainView
import com.padc.csh.themovieapp.mvp.views.MovieDetailView

class MovieDetailPresenterImpl:ViewModel(),MovieDetailPresenter {

    //model
    private val mMovieModel: MovieModel = MovieModelImpl

    //view
    var mView: MovieDetailView?=null

    override fun initView(view: MovieDetailView) {
       mView = view
    }

    override fun onUiReadyInMovieDetail(owner: LifecycleOwner, movieId: Int) {
       //movie detail
        mMovieModel.getMovieDetail(movieId = movieId.toString()
        ) {
            mView?.showErrorMsg(it)

        }?.observe(owner) {
            if (it != null) {
                mView?.showMovieDetail(it)
            }
        }

        //actors
        mMovieModel.getMovieCredits(movieId = movieId.toString(),{

            mView?.showCreditsByMovie(it.first,it.second)

        },{
            mView?.showErrorMsg(it)
        })
    }

    override fun onTapBack() {
       mView?.navigateBack()
    }

    override fun onUiReady(owner: LifecycleOwner) {

    }
}