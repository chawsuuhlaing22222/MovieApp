package com.padc.csh.themovieapp.viewpods

import android.content.Context
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.padc.csh.themovieapp.adapters.BestActorAdapter
import com.padc.csh.themovieapp.data.vos.ActorVO
import kotlinx.android.synthetic.main.view_pod_actor_list.view.*

class ActorListViewPod @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : RelativeLayout(context, attrs) {

    lateinit var mBestActorAdapter: BestActorAdapter
    override fun onFinishInflate() {
        setUpActorRecycler()
        super.onFinishInflate()
    }

    public fun setUpActorViewPod(bgColorReferenceType:Int,title:String,seeMoreTitle:String){
        setBackgroundColor(ContextCompat.getColor(context,bgColorReferenceType))
        tvBestActorTitle.text=title
        tvMoreActorsTitle.text=seeMoreTitle
        tvMoreActorsTitle.paintFlags=Paint.UNDERLINE_TEXT_FLAG
    }

    private fun setUpActorRecycler() {
        mBestActorAdapter = BestActorAdapter()
        rvBestActor.adapter = mBestActorAdapter
        rvBestActor.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
    }

    fun setData(actors:List<ActorVO>){
        mBestActorAdapter.setNewData(actors)
    }

}