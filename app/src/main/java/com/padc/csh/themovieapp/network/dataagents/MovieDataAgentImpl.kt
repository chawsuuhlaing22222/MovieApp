package com.padc.csh.themovieapp.network.dataagents

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
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

object MovieDataAgentImpl:MovieDataAgent {
    override fun getNowPlayingMovies(onSuccess:(List<MovieVO>)->Unit,
                                     onFailure:(String)->Unit) {
      GetNowPlayingMovie().execute()
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

class GetNowPlayingMovie(): AsyncTask<Void, Void, MovieListResponse>() {
    override fun doInBackground(vararg p0: Void?): MovieListResponse? {//work in bg thread

        val url:URL
        var reader:BufferedReader?=null //to read line from response and store it to stringbuilder
        val stringBuilder:StringBuilder

        try{
            url=URL("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1 """)
            //connection
            val connection=url.openConnection() as HttpURLConnection

            //set Http method
            connection.requestMethod="GET"

            //set 15 sec to respond
            connection.readTimeout=15 *1000

            //have response?
            connection.doInput=true
            //have request body? (now we put arguments with url)
            connection.doOutput=false

            connection.connect()

            //read the output from the server
            reader= BufferedReader(InputStreamReader(connection.inputStream))

            stringBuilder=StringBuilder()

            for(line in reader.readLines()){
                stringBuilder.appendLine(line +"\n")
            }

            val responseString=stringBuilder.toString()
            Log.d("NowPlayingMovie",responseString)

            val movieListResponse= Gson().fromJson(
                responseString,MovieListResponse::class.java
            )

            return movieListResponse
        }catch (e:Exception){
            e.printStackTrace()
            Log.e("NewErrors",e.message.toString())
        }finally {
            if(reader !=null){
                try {
                    reader.close()
                }catch (ioe:IOException){
                    ioe.printStackTrace()
                }
            }

        }
        return null
    }

    override fun onPostExecute(result: MovieListResponse?) {  //after finish doInbg , do in main thread (notify result)
        super.onPostExecute(result)
    }
}