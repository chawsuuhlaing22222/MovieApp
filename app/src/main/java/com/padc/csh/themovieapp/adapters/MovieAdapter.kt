package com.padc.csh.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapp.R
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.delegates.MovieViewPodDelegate
import com.padc.csh.themovieapp.viewholders.MovieViewHolder

class MovieAdapter(private val mDelegate:MovieViewPodDelegate): RecyclerView.Adapter<MovieViewHolder>() {
    private var mMovieList:List<MovieVO> = listOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_movie_item,parent,false)
        return MovieViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {

        if(mMovieList.isNotEmpty()){
            holder.bindData(mMovieList[position])
        }

    }

    override fun getItemCount(): Int {
    return mMovieList.size ?: 0
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setNewData(movieList:List<MovieVO>){
        mMovieList=movieList
        notifyDataSetChanged()
    }

}