package com.padc.csh.themovieapp.network.responses

import com.google.gson.annotations.SerializedName
import com.padc.csh.themovieapp.data.vos.GenreVO

data class GenreListResponse (
@SerializedName("genres")
val genres: List<GenreVO>?
)