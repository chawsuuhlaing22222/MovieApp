package com.padc.csh.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.delegates.BannerViewHolderDelegate
import com.padc.csh.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_item_banner.view.*

class BannerViewHolder(itemView: View,delegate: BannerViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

    private var movieVo:MovieVO?=null
    init {
        itemView.setOnClickListener {
            movieVo?.let { movie->
                delegate.onTapMovieFromBanner(movie.id ?: 0)
            }

        }
    }

    fun bindData(movie:MovieVO){

        movieVo = movie
       Glide.with(itemView.context)
           .load("$IMAGE_BASE_URL${movie.backDropPath}")
           .into(itemView.ivBanner)

        itemView.tvBannerMovieName.text=movie.title
    }
}