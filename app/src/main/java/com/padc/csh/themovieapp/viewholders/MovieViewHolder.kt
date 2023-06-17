package com.padc.csh.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.delegates.MovieViewPodDelegate
import com.padc.csh.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_movie_item.view.*

class MovieViewHolder(itemView: View, mDelegate: MovieViewPodDelegate) :
    RecyclerView.ViewHolder(itemView) {

    private var movie: MovieVO? = null

    init {
        itemView.setOnClickListener {
            movie?.let {
                mDelegate.onTapMovie(it.id ?: 0)
            }

        }
    }

    fun bindData(movieVO: MovieVO) {

        movie = movieVO
        movie?.let {
            Glide.with(itemView.context)
                .load("$IMAGE_BASE_URL${it.posterPath}")
                .into(itemView.ivMovieImage)

            itemView.tvMovieName.text = it.title
            itemView.tvMovieRating.text = it.voteAverage.toString()
            itemView.rbMovieRating.rating = it.getRatingBasedOnFiveStars()
        }

    }
}
