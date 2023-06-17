package com.padc.csh.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreIdIdListTypeConverter {

    @TypeConverter
    fun toString(genreIdList:List<Int>?):String{
        return Gson().toJson(genreIdList)
    }

    @TypeConverter
    fun toGenreIdList(genreIdList: String):List<Int>?{
        var toType=object :TypeToken<List<Int>?>(){}.type
        return Gson().fromJson(genreIdList,toType)
    }
}