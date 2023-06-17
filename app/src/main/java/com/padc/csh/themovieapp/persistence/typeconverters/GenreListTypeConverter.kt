package com.padc.csh.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.themovieapp.data.vos.GenreVO

class GenreListTypeConverter {

    @TypeConverter
    fun toString(genreList:List<GenreVO>?):String{
        return Gson().toJson(genreList)
    }

    @TypeConverter
    fun toGenreList(genreList: String):List<GenreVO>?{
        var toType=object : TypeToken<List<GenreVO>?>(){}.type
        return Gson().fromJson(genreList,toType)
    }
}