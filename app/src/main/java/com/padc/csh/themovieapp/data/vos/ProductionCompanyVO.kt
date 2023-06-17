package com.padc.csh.themovieapp.data.vos

import com.google.gson.annotations.SerializedName

data class ProductionCompanyVO(

    @SerializedName("id")
    val id: Int?,

    @SerializedName("logo_path")
    val logo_path: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("origin_country")
    val originCountry: String?
)