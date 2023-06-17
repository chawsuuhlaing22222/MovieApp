package com.padc.csh.themovieapp.mvp.presenters

import com.padc.csh.themovieapp.delegates.BannerViewHolderDelegate
import com.padc.csh.themovieapp.delegates.MovieViewPodDelegate
import com.padc.csh.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.padc.csh.themovieapp.mvp.views.MainView

//inheritance delegate to solve view holder action
//inheritance basepresenter to show initial data
interface MainPresenter :IBasePresenter, BannerViewHolderDelegate, ShowCaseViewHolderDelegate,
    MovieViewPodDelegate {
    //set view reference
        fun initView(view:MainView)
        fun onTapGenre(genrePosition:Int)
}