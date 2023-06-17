package com.padc.csh.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapp.data.vos.ActorVO

data class MovieCreditResponse(

    @SerializedName("cast")
    val cast: List<ActorVO>?,

    @SerializedName("crew")
    val crew: List<ActorVO>?
)
