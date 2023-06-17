package com.padc.csh.themovieapp.viewpods

import android.content.Context
import android.util.AttributeSet
import android.widget.FrameLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapp.adapters.MovieAdapter
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.delegates.MovieViewPodDelegate
import kotlinx.android.synthetic.main.view_pod_movie_list.view.*

class MovieListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : FrameLayout(context, attrs) {

    lateinit var mMovieAdapter:MovieAdapter
    lateinit var mDelegate:MovieViewPodDelegate

    override fun onFinishInflate() {
        //setUpRecyclerAdapter()
        super.onFinishInflate()
    }

    public fun setUpMovieListViewPod(delegate: MovieViewPodDelegate){
        setUpDelegate(delegate)
        setUpRecyclerAdapter()
    }

    private fun setUpDelegate(delegate: MovieViewPodDelegate){
        mDelegate=delegate
    }

    private fun setUpRecyclerAdapter(){
        mMovieAdapter= MovieAdapter(mDelegate)
        rvMovieList.adapter=mMovieAdapter
        rvMovieList.layoutManager=LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false)
    }

     fun setData(movieList:List<MovieVO>){
        mMovieAdapter.setNewData(movieList)
    }

}