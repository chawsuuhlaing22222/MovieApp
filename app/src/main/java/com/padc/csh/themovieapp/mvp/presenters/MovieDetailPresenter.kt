package com.padc.csh.themovieapp.mvp.presenters

import androidx.lifecycle.LifecycleOwner
import com.padc.csh.themovieapp.mvp.views.MovieDetailView

interface MovieDetailPresenter:IBasePresenter {

    fun initView(view: MovieDetailView)
    fun onUiReadyInMovieDetail(owner: LifecycleOwner, movieId:Int)
    fun onTapBack()
}