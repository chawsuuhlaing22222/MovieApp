package com.padc.csh.themovieapp.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.padc.csh.themovieapp.data.vos.GenreVO
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.data.vos.ProductionCountryVO
import com.padc.csh.themovieapp.persistence.daos.MovieDao

@Database(entities = [MovieVO::class,GenreVO::class,ProductionCountryVO::class], version = 3, exportSchema = false)
abstract class MovieDatabase:RoomDatabase() {

    companion object{
        const val DB_NAME="THE_MOVIE_DB"
        var movieDatabase: MovieDatabase?=null
        fun getDBInstance(context:Context):MovieDatabase?{

            when(movieDatabase){
                null->{
                    movieDatabase= Room.databaseBuilder(context,MovieDatabase::class.java, DB_NAME)
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                }
              }

            return movieDatabase
        }

    }

    abstract fun movieDao():MovieDao
}

