package com.padc.csh.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.themovieapp.data.vos.GenreVO
import com.padc.csh.themovieapp.data.vos.ProductionCountryVO

class ProductionCountryListConverter {
    @TypeConverter
    fun toString(productionCountryList:List<ProductionCountryVO>?):String{
        return Gson().toJson(productionCountryList)
    }

    @TypeConverter
    fun toProductionCountryList(productionCountryList: String):List<ProductionCountryVO>?{
        var toType=object : TypeToken<List<ProductionCountryVO>?>(){}.type
        return Gson().fromJson(productionCountryList,toType)
    }
}