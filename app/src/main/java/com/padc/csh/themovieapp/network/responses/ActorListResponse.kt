package com.padc.csh.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapp.data.vos.ActorVO

data class ActorListResponse (
    @SerializedName("results")
    val results:List<ActorVO>?
        )