package com.padc.csh.themovieapp.mvp.views

import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapp.data.vos.MovieVO

interface MovieDetailView:BaseView {
    fun showMovieDetail(movie:MovieVO)
    fun showCreditsByMovie(casts:List<ActorVO>,crew:List<ActorVO>)
    fun navigateBack()
}