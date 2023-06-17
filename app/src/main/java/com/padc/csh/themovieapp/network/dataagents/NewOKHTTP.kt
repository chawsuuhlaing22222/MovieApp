package com.padc.csh.themovieapp.network.dataagents





    import android.os.AsyncTask

    import com.google.gson.Gson
    import com.padc.csh.themovieapp.data.vos.ActorVO
    import com.padc.csh.themovieapp.data.vos.GenreVO
    import com.padc.csh.themovieapp.data.vos.MovieVO
    import com.padc.csh.themovieapp.network.responses.MovieListResponse
    import com.padc.csh.themovieapp.utils.API_GET_NOW_PLAYING
    import com.padc.csh.themovieapp.utils.BASE_URL
    import com.padc.csh.themovieapp.utils.MOVIE_API_KEY
    import okhttp3.OkHttpClient
    import okhttp3.Request
    import java.util.concurrent.TimeUnit

object NewOKHTTP : MovieDataAgent {

    override fun getNowPlayingMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        GetNowPlayingMovieOkHttpTask(mClient).execute()
    }

    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

    }

    override fun getTopRatedMovies(
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {

    }

    override fun getGenreList(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getActorList(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun getCreditsByMovie(
        movieId: String,
        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        TODO("Not yet implemented")
    }







    private val mClient = OkHttpClient.Builder()
        .connectTimeout(15,TimeUnit.SECONDS)
        .readTimeout(15,TimeUnit.SECONDS)
        .writeTimeout(15,TimeUnit.SECONDS)
        .build()

    class GetNowPlayingMovieOkHttpTask(private val mOkHttpClient: OkHttpClient) : AsyncTask<Void,Void,MovieListResponse>() {
        override fun doInBackground(vararg p0: Void?): MovieListResponse? {
            val request = Request.Builder()
                .url("""$BASE_URL$API_GET_NOW_PLAYING?api_key=$MOVIE_API_KEY&language=en-US&page=1""")
                .build()

            try {
                val response = mOkHttpClient.newCall(request).execute()
                if(response.isSuccessful) {
                    response.body?.let {
                        val responseString = it.string()
                        return Gson().fromJson(responseString, MovieListResponse::class.java)
                    }
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
}
