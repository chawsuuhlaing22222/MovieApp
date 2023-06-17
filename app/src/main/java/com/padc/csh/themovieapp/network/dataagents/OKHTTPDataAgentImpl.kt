package com.padc.csh.themovieapp.network.dataagents

import android.app.DownloadManager.Request
import android.os.AsyncTask
import android.util.Log
import com.google.gson.Gson
import com.padc.csh.themovieapp.data.vos.ActorVO
import com.padc.csh.themovieapp.data.vos.GenreVO
import com.padc.csh.themovieapp.data.vos.MovieVO
import com.padc.csh.themovieapp.network.responses.MovieListResponse
import com.padc.csh.themovieapp.utils.API_GET_NOW_PLAYING
import com.padc.csh.themovieapp.utils.BASE_URL
import com.padc.csh.themovieapp.utils.MOVIE_API_KEY
import okhttp3.OkHttpClient
import okhttp3.*
import java.io.IOException
import java.nio.channels.AsynchronousByteChannel
import java.util.concurrent.TimeUnit

object OKHTTPDataAgentImpl : MovieDataAgent {

    val mClient: OkHttpClient = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS) //connection duration
        .readTimeout(30, TimeUnit.SECONDS) //response reading duration
        .writeTimeout(30, TimeUnit.SECONDS)//request or input writing duration
        .build()

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetNowPlayingMovieOKHTTPTask(mClient).execute()
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getGenreList(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getActorList(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

}

class GetNowPlayingMovieOKHTTPTask(private val mOkHttpClient: OkHttpClient) :
    AsyncTask<Void, Void, MovieListResponse>() {

    var movieListResponse: MovieListResponse? = null

    override fun doInBackground(vararg p0: Void?): MovieListResponse? {

        val request = okhttp3.Request.Builder()
            .url("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")
            //.url("https://api.themoviedb.org/3/movie/now_playing?api_key=d862c9b2ab825cc933e612a576adad70&language=en-US&page=1")
            .build()
        Log.i("mrequest", request.toString())

        try {
            val response = mOkHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                val responsebody = response.body
                responsebody?.let {

                    val responseString = it.toString()
                    val movieListResponse = Gson().fromJson<MovieListResponse>(
                        responseString,
                        MovieListResponse::class.java
                    )
                    Log.i("mresponse", "${movieListResponse}")
                }



                return movieListResponse
            }


        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null

    }

    override fun onPostExecute(result: MovieListResponse?) {
        super.onPostExecute(result)
    }

}


