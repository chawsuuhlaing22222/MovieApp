package com.padc.csh.themovieapp.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayout
import com.padc.csh.themovieapp.R
import com.padc.csh.themovieapp.adapters.BannerAdapter
import com.padc.csh.themovieapp.adapters.ShowCaseAdapter
import com.padc.csh.themovieapp.data.models.MovieModel
import com.padc.csh.themovieapp.data.models.MovieModelImpl
import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapp.data.vos.GenreVO
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.delegates.BannerViewHolderDelegate
import com.padc.csh.themovieapp.delegates.MovieViewPodDelegate
import com.padc.csh.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.padc.csh.themovieapp.mvp.presenters.MainPresenter
import com.padc.csh.themovieapp.mvp.presenters.MainPresenterImpl
import com.padc.csh.themovieapp.mvp.views.MainView
import com.padc.csh.themovieapp.network.dataagents.NewOKHTTP
import com.padc.csh.themovieapp.network.dataagents.OKHTTPDataAgentImpl
import com.padc.csh.themovieapp.viewpods.ActorListViewPod
import com.padc.csh.themovieapp.viewpods.MovieListViewPod
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : BaseActivity(),MainView{

    //adapter
    lateinit var mBannerAdapter: BannerAdapter
    lateinit var mShowCaseAdapter: ShowCaseAdapter

    //view pod
    lateinit var mBestPopularMovieViewPod:MovieListViewPod
    lateinit var mMovieByGenreViewPod: MovieListViewPod
    lateinit var mActorViewPod:ActorListViewPod

    //presenter
   lateinit var mPresenter : MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //setup presenter
        setUpPresenter()
        //set appbar leading icon
        setUpToolBar()
        //setup vp banner
        setUpViewPager()

        //setup showcase movie
        setUpShowCase()
        //setup viewpod
        setUpViewPod()
        //action listener
        setUpListener()

        //api call in bg thread
        //MovieDataAgentImpl.getNowPlayingMovies()
         //OKHTTPDataAgentImpl.getNowPlayingMovies({},{})
       // NewOKHTTP.getNowPlayingMovies({},{})
       // RetrofitDataAgentImpl.getNowPlayingMovies()

        //requestData()

        //mvp
        mPresenter.onUiReady(this)
    }

    private fun setUpPresenter() {
        mPresenter = ViewModelProvider(this)[MainPresenterImpl::class.java]
        mPresenter.initView(this)
    }

    private fun setUpViewPod(){
        mBestPopularMovieViewPod = viewPodMovieList as MovieListViewPod
        mBestPopularMovieViewPod.setUpMovieListViewPod(mPresenter)

        mMovieByGenreViewPod = viewPodMovieListByGenre as MovieListViewPod
        mMovieByGenreViewPod.setUpMovieListViewPod(mPresenter)

        mActorViewPod=viewPodActors as ActorListViewPod
    }

    private fun setUpListener() {

        //search
        //Genre Tablayout listener
        tabLayoutGenre.addOnTabSelectedListener(object :TabLayout.OnTabSelectedListener{
            override fun onTabSelected(tab: TabLayout.Tab?) {

                mPresenter.onTapGenre(tab?.position ?:0)
                Snackbar.make(window.decorView,tab?.text ?: "",Snackbar.LENGTH_SHORT).show()
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

        })
    }

    private fun setUpShowCase(){
        mShowCaseAdapter= ShowCaseAdapter(mPresenter)
        rvShowCases.adapter=mShowCaseAdapter
        rvShowCases.layoutManager=LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)
    }

    private fun setUpTabLayoutGenre(genreList:List<GenreVO>) {
        genreList.forEach {

            tabLayoutGenre.newTab().apply {
                this.text=it.name
                tabLayoutGenre.addTab(this)
            }
        }
    }

    private fun setUpViewPager(){
        mBannerAdapter=BannerAdapter(mPresenter)
        viewPagerBanner.adapter=mBannerAdapter

        //attach vp with dots
        dotsIndicatorBanner.attachTo(viewPagerBanner)
    }

    private fun setUpToolBar() {
        setSupportActionBar(toolBar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_menu)
    }

    //set appbar trailing icon
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_discover,menu)
        return true
    }

    //action of menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.nav_search->{
                startActivity(Intent(this,MovieSearchActivity::class.java))
                return true
            }
        }
        return false
    }


    override fun showNowPlayingMovies(nowPlayingMovieList: List<MovieVO>) {
      mBannerAdapter.setNewData(nowPlayingMovieList)
    }

    override fun showPopularMovies(popularMovieList: List<MovieVO>) {
        mBestPopularMovieViewPod.setData(popularMovieList)
    }

    override fun showTopRatedMovies(topRatedMovieList: List<MovieVO>) {
        mShowCaseAdapter.setNewData(topRatedMovieList)
    }

    override fun showGenres(genreList: List<GenreVO>) {
        setUpTabLayoutGenre(genreList)
    }

    override fun showMoviesByGenre(movieByGenre: List<MovieVO>) {
        mMovieByGenreViewPod.setData(movieByGenre)
    }

    override fun showActors(actorList: List<ActorVO>) {
        mActorViewPod.setData(actorList)
    }

    override fun navigateToMovieDetailScrn(movieId: Int) {
        startActivity(MovieDetailActivity.newIntent(this,movieId))
    }

    override fun showErrorMsg(error:String){
        //Snackbar.make(window.decorView,"$error",Snackbar.LENGTH_SHORT).show()
    }
}