//package com.padc.csh.themovieapp.network.dataagents
//
//import android.util.Log
//import com.padc.csh.themovieapp.data.vos.ActorVO
//import com.padc.csh.themovieapp.data.vos.GenreVO
//import com.padc.csh.themovieapp.data.vos.MovieVO
//import com.padc.csh.themovieapp.network.TheMovieApi
//import com.padc.csh.themovieapp.network.responses.ActorListResponse
//import com.padc.csh.themovieapp.network.responses.GenreListResponse
//import com.padc.csh.themovieapp.network.responses.MovieCreditResponse
//import com.padc.csh.themovieapp.network.responses.MovieListResponse
//import com.padc.csh.themovieapp.utils.BASE_URL
//import okhttp3.OkHttpClient
//import retrofit2.*
//import retrofit2.converter.gson.GsonConverterFactory
//import java.util.concurrent.TimeUnit
//
//object RetrofitDataAgentImpl:MovieDataAgent {
//
//  private var mTheMovieApi:TheMovieApi?=null
//    init {
//        val okHttpClient= OkHttpClient.Builder()
//            .connectTimeout(15,TimeUnit.SECONDS)
//            .readTimeout(15,TimeUnit.SECONDS)
//            .writeTimeout(15,TimeUnit.SECONDS)
//            .build()
//
//        val retrofit=Retrofit.Builder()
//            .baseUrl(BASE_URL)
//            .client(okHttpClient)
//            .addConverterFactory(GsonConverterFactory.create())
//            .build()
//
//        mTheMovieApi=retrofit.create(TheMovieApi::class.java)
//    }
//
//    override fun getNowPlayingMovies(
//        onSuccess:(List<MovieVO>)->Unit,
//        onFailure:(String)->Unit
//    ) {
//
//        mTheMovieApi?.getNowPlayingMovies()
//        ?.enqueue(object :Callback<MovieListResponse>{
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//
//                if(response.isSuccessful){
//                    val response=response.body()?.results ?: listOf()
//                    Log.i("res",response.toString())
//                    onSuccess(response)
//                }else{
//                    onFailure(response.message())
//                }
//
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                    onFailure(t.message ?: " ")
//            }
//
//        })
//
//    }
//
//    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {
//
//        mTheMovieApi?.getPopularMovies()?.enqueue(
//            object :Callback<MovieListResponse>{
//                override fun onResponse(
//                    call: Call<MovieListResponse>,
//                    response: Response<MovieListResponse>
//                ) {
//                    if(response.isSuccessful){
//
//                        val movieList=response.body()?.results ?: listOf()
//                        onSuccess(movieList)
//                    }else{
//                        onFailure(response.message())
//                    }
//                }
//
//                override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                    onFailure(t.message.toString())
//                }
//
//            }
//        )
//    }
//
//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getTopRatedMovies()?.enqueue(object :Callback<MovieListResponse>{
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//               if(response.isSuccessful){
//                   val movieList=response.body()?.results ?: listOf()
//                   onSuccess(movieList)
//               }else{
//                   onFailure(response.message())
//               }
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message.toString())
//            }
//
//        })
//    }
//
//    override fun getGenreList(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
//
//        mTheMovieApi?.getGenreList()?.enqueue(object : Callback<GenreListResponse>{
//
//            override fun onResponse(
//                call: Call<GenreListResponse>,
//                response: Response<GenreListResponse>
//            ) {
//                if(response.isSuccessful){
//                    val genreList=response.body()?.genres ?: listOf()
//                    onSuccess(genreList)
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<GenreListResponse>, t: Throwable) {
//                onFailure(t.message.toString())
//            }
//
//        })
//    }
//
//    override fun getMoviesByGenre(
//        genreId: String,
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getMoviesByGenre(with_genre = genreId)?.enqueue(object :Callback<MovieListResponse>{
//            override fun onResponse(
//                call: Call<MovieListResponse>,
//                response: Response<MovieListResponse>
//            ) {
//                if(response.isSuccessful){
//                    val movieList=response.body()?.results ?: listOf()
//                    onSuccess(movieList)
//                }else{
//                    onFailure(response.message())
//                }
//            }
//
//            override fun onFailure(call: Call<MovieListResponse>, t: Throwable) {
//                onFailure(t.message ?: "")
//            }
//
//        })
//    }
//
//    override fun getActorList(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
//       mTheMovieApi?.getActors()?.enqueue(
//           object :Callback<ActorListResponse>{
//               override fun onResponse(
//                   call: Call<ActorListResponse>,
//                   response: Response<ActorListResponse>
//               ) {
//                   if(response.isSuccessful){
//                       val actorsList=response.body()?.results ?: listOf()
//                       onSuccess(actorsList)
//                   }else{
//                       onFailure(response.message())
//                   }
//               }
//
//               override fun onFailure(call: Call<ActorListResponse>, t: Throwable) {
//                   onFailure(t.message ?: "")
//               }
//
//           }
//       )
//    }
//
//    override fun getMovieDetail(
//        movieId: String,
//        onSuccess: (MovieVO) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//
//        mTheMovieApi?.getMovieDetail(movieId = movieId)?.enqueue(
//            object :Callback<MovieVO>{
//                override fun onResponse(call: Call<MovieVO>, response: Response<MovieVO>) {
//
//                    if(response.isSuccessful){
//                       response.body()?.let {
//                           onSuccess(it)
//                       }
//
//                    }else{
//                        onFailure(response.message())
//                    }
//                }
//
//                override fun onFailure(call: Call<MovieVO>, t: Throwable) {
//                    onFailure(t.message ?: "")
//                }
//
//            }
//        )
//
//
//    }
//
//    override fun getCreditsByMovie(
//        movieId: String,
//        onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        mTheMovieApi?.getCreditByMovie(movieId = movieId)?.enqueue(
//            object :Callback<MovieCreditResponse>{
//                override fun onResponse(
//                    call: Call<MovieCreditResponse>,
//                    response: Response<MovieCreditResponse>
//                ) {
//                    if(response.isSuccessful){
//                        response.body()?.let {
//                            onSuccess(Pair(it.cast ?: listOf(),it.crew ?: listOf()))
//                        }
//
//                    }else{
//                        onFailure(response.message())
//                    }
//                }
//
//                override fun onFailure(call: Call<MovieCreditResponse>, t: Throwable) {
//                    onFailure(t.message ?: "")
//                }
//
//            }
//        )
//    }
//
//
//}