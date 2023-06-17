package com.padc.csh.themovieapp.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.padc.csh.themovieapp.R
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.padc.csh.themovieapp.viewholders.ShowCaseViewHolder

class ShowCaseAdapter(private val mDelegate: ShowCaseViewHolderDelegate):RecyclerView.Adapter<ShowCaseViewHolder>() {

    private var mMovieList:List<MovieVO>?=null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShowCaseViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.view_holder_show_case_item,parent,false)
        return ShowCaseViewHolder(view,mDelegate)
    }

    override fun onBindViewHolder(holder: ShowCaseViewHolder, position: Int) {


        mMovieList?.let {
            var movie=it[position]
           holder.bindData(movie)
       }
    }

    override fun getItemCount(): Int {
        return mMovieList?.count() ?: 0
    }

    @SuppressLint("")
    fun setNewData(movieList:List<MovieVO>){
        mMovieList = movieList
        notifyDataSetChanged()
    }
}