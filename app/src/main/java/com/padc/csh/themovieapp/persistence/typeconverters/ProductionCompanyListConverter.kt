package com.padc.csh.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.themovieapp.data.vos.ProductionCompanyVO
import com.padc.csh.themovieapp.data.vos.ProductionCountryVO

class ProductionCompanyListConverter {

    @TypeConverter
    fun toString(productionCompanyList:List<ProductionCompanyVO>?):String{
        return Gson().toJson(productionCompanyList)
    }

    @TypeConverter
    fun toProductionCompanyList(productionCompanyList: String):List<ProductionCompanyVO>?{
        var toType=object : TypeToken<List<ProductionCompanyVO>?>(){}.type
        return Gson().fromJson(productionCompanyList,toType)
    }

}