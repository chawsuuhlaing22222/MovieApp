package com.padc.csh.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.padc.csh.themovieapp.data.vos.CollectionVO

class CollectionTypeConverter {

    @TypeConverter
    fun toString(collectionVO: CollectionVO?):String{
        return Gson().toJson(collectionVO)
    }

    @TypeConverter
    fun toCollectionVo(collectionList:String):CollectionVO?{
        var collectionType=object :TypeToken<CollectionVO?>(){}.type
        return Gson().fromJson(collectionList,collectionType)
    }
}