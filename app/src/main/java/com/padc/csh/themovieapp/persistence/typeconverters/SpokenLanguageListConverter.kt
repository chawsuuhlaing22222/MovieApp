package com.padc.csh.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.themovieapp.data.vos.LanguageVO
import com.padc.csh.themovieapp.data.vos.ProductionCountryVO

class SpokenLanguageListConverter {

    @TypeConverter
    fun toString(spokenLanguageList:List<LanguageVO>?):String{
        return Gson().toJson(spokenLanguageList)
    }

    @TypeConverter
    fun toSpokentLanguageList(spokenLanguageList: String):List<LanguageVO>?{
        var toType=object : TypeToken<List<LanguageVO>?>(){}.type
        return Gson().fromJson(spokenLanguageList,toType)
    }
}