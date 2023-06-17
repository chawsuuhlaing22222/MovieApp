package com.padc.csh.themovieapp.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding4.widget.textChanges
import com.padc.csh.themovieapp.R
import com.padc.csh.themovieapp.adapters.MovieAdapter
import com.padc.csh.themovieapp.data.models.MovieModel
import com.padc.csh.themovieapp.data.models.MovieModelImpl
import com.padc.csh.themovieapp.delegates.MovieViewPodDelegate
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_movie_search.*
import java.util.concurrent.TimeUnit

class MovieSearchActivity : AppCompatActivity(),MovieViewPodDelegate {

    lateinit var mMovieAdapter: MovieAdapter

    private var movieModel :MovieModel = MovieModelImpl
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_movie_search)

        setUpRecycler()
        setUpActionListenr()
    }

    private fun setUpActionListenr() {

        etMovieName.textChanges()
            .debounce(500L,TimeUnit.MILLISECONDS)
            .flatMap { movieModel.searchMovie(it.toString())
            }
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
              mMovieAdapter.setNewData(it)
            },{
             showErrorMsg(it.localizedMessage ?: "")
            })

    }

    private fun setUpRecycler() {
        mMovieAdapter = MovieAdapter(this)
        rvMovieSearchResults.adapter=mMovieAdapter
        rvMovieSearchResults.layoutManager = GridLayoutManager(this,2)

        rvMovieSearchResults.addItemDecoration(object : RecyclerView.ItemDecoration() {

        })
    }

    override fun onTapMovie(movieId: Int) {

    }

    fun showErrorMsg(error:String){
        Snackbar.make(window.decorView,"$error", Snackbar.LENGTH_SHORT).show()
    }
}