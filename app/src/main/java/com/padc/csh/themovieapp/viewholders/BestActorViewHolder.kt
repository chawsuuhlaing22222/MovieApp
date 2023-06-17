package com.padc.csh.themovieapp.viewholders

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapp.utils.IMAGE_BASE_URL
import kotlinx.android.synthetic.main.view_holder_best_actor_item.view.*
import kotlin.math.acos

class BestActorViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    fun bindData(actor: ActorVO) {

        Glide.with(itemView.context)
            .load("$IMAGE_BASE_URL${actor.profile_path}")
            .into(itemView.ivActorImage)

        itemView.tvActorName.text=actor.name
    }
}