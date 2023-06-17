package com.padc.csh.themovieapp.activities

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.google.android.material.snackbar.Snackbar
import com.padc.csh.themovieapp.R
import com.padc.csh.themovieapp.data.models.MovieModel
import com.padc.csh.themovieapp.data.models.MovieModelImpl
import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapp.data.vos.GenreVO
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.mvp.presenters.MovieDetailPresenter
import com.padc.csh.themovieapp.mvp.presenters.MovieDetailPresenterImpl
import com.padc.csh.themovieapp.mvp.views.MovieDetailView
import com.padc.csh.themovieapp.utils.IMAGE_BASE_URL
import com.padc.csh.themovieapp.viewpods.ActorListViewPod
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_movie_detail.*
import kotlinx.android.synthetic.main.activity_movie_detail.viewPodActors

class MovieDetailActivity : BaseActivity(),MovieDetailView {

    lateinit var actorViewPod:ActorListViewPod
    lateinit var creatorViewPod: ActorListViewPod

    //presenter
    lateinit var mPresenter:MovieDetailPresenter


    companion object{
        const val IEXTRA_MOVIE_PARAM="movie id"
        fun newIntent(context: Context,movieId:Int): Intent {
            val intent=Intent(context,MovieDetailActivity::class.java)
            intent.putExtra(IEXTRA_MOVIE_PARAM,movieId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_detail)

        setUpPresenter()
        setUpViewPods()
        setUpListener()

        val movieId=intent.getIntExtra(IEXTRA_MOVIE_PARAM,0)
        movieId?.let {
            mPresenter.onUiReadyInMovieDetail(this ,it)
        }

    }

    private fun setUpPresenter() {
        mPresenter= ViewModelProvider(this)[MovieDetailPresenterImpl::class.java]
        mPresenter.initView(this)
    }



    private fun bindData(movie: MovieVO) {
        var ivurl=movie.backDropPath
        Glide.with(this)
            .load("${ IMAGE_BASE_URL}${movie.backDropPath}")
            .into(ivMovieImg)

        tvReleasedYear.text=movie.releaseDate?.substring(0,4)
        tvMovieDetailName.text=movie.title
        tvRating.text=movie.voteAverage.toString()
        ratingBar.rating=movie.getRatingBasedOnFiveStars()

        val numVotes=movie.voteCount ?: 0
        tvNumberOfVotes.text="$numVotes VOTES"

        bindGenre(movie,movie.genres)

        tvMovieOverview.text=movie.overview ?: ""
        tvOriginalTitle.text=movie.originalTtitle
        tvType.text=movie.getGenresAsCommaSeparatedString()
        Log.i("listGenre",movie.getGenresAsCommaSeparatedString())
        tvProduction.text=movie.getProductionCountriesAsCommaSeparatedString()
        Log.i("listProduction",movie.getProductionCountriesAsCommaSeparatedString())
        tvPremiere.text=movie.releaseDate
        tvDescription.text=movie.overview

        toolBarMovieDetail.title=movie.title

    }

    private fun bindGenre(movie: MovieVO, genres: List<GenreVO>?) {

        movie.genres?.count()?.let {
            tvFirstGenre.text=genres?.firstOrNull()?.name ?: ""
            tvSecondGenre.text=genres?.getOrNull(1)?.name ?: ""
            tvThirdGenre.text=genres?.getOrNull(2)?.name ?: ""

            if(it <3){
                tvThirdGenre.visibility=View.GONE
            }else if(it< 2){
                tvSecondGenre.visibility=View.GONE
            }

        }


    }

    private fun setUpListener(){
        btnBack.setOnClickListener {
           mPresenter.onTapBack()
        }
    }

    private fun setUpViewPods() {
        actorViewPod= viewPodActors as ActorListViewPod
        actorViewPod.setUpActorViewPod(bgColorReferenceType = R.color.colorPrimary,
        title = getString(R.string.lbl_actors),
        seeMoreTitle = "")

        creatorViewPod = viewPodCreators as ActorListViewPod
        creatorViewPod.setUpActorViewPod(bgColorReferenceType = R.color.colorPrimary,
            title = getString(R.string.lbl_creators),
            seeMoreTitle = getString(R.string.lbl_more_creators))

    }

    override fun showMovieDetail(movie: MovieVO) {
        bindData(movie)
    }

    override fun showCreditsByMovie(casts: List<ActorVO>, crew: List<ActorVO>) {
        actorViewPod.setData(casts)
        creatorViewPod.setData(crew)
    }

    override fun navigateBack() {
      finish()
    }

     override fun showErrorMsg(error:String){
        //Snackbar.make(window.decorView,"$error",Snackbar.LENGTH_SHORT).show()
    }
}