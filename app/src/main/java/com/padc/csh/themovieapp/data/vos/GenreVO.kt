package com.padc.csh.themovieapp.data.vos

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "genres")
data class GenreVO (

        @SerializedName("id")
        @PrimaryKey
        val id:Int?,

        @SerializedName("name")
        val name:String?
        )