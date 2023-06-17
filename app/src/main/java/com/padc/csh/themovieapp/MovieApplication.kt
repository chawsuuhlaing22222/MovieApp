package com.padc.csh.themovieapp

import android.app.Application
import com.padc.csh.themovieapp.data.models.BaseModel
import com.padc.csh.themovieapp.data.models.MovieModelImpl


class MovieApplication:Application() {
    override fun onCreate() {
        super.onCreate()
        MovieModelImpl.initDB(applicationContext)


    }
}