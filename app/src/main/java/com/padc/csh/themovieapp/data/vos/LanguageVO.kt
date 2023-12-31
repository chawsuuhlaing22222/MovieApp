package com.padc.csh.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class LanguageVO (

    @SerializedName("english_name")
    val englishName: String?,

    @SerializedName("iso_639_1")
    val iso: String?,

    @SerializedName("name")
    val name: String?

        )