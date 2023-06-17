package com.padc.csh.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.delegates.ShowCaseViewHolderDelegate
import com.padc.csh.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_show_case_item.view.*

class ShowCaseViewHolder(itemView: View,delegate: ShowCaseViewHolderDelegate) : RecyclerView.ViewHolder(itemView) {

  private var movieVo:MovieVO?=null
    init {
        itemView.setOnClickListener {
            movieVo?.let {
                delegate.onTapMovieFromShowCase(it.id ?: 0)
            }

        }
    }

    fun bindData(movie: MovieVO) {
        movieVo = movie
        movieVo?.let {
            Glide.with(itemView.context)
                .load("$IMAGE_BASE_URL${it.posterPath}")
                .into(itemView.ivShowCase)

            itemView.tvShowCaseMovieName.text=it.title
            itemView.tvShowCaseMovieDate.text=it.releaseDate


        }
    }
}