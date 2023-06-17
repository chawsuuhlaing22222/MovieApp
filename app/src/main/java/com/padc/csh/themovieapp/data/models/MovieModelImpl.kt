package com.padc.csh.themovieapp.data.models

import android.content.Context
import androidx.lifecycle.LiveData
import com.padc.csh.themovieapp.data.vos.*
import com.padc.csh.themovieapp.network.dataagents.MovieDataAgent
import com.padc.csh.themovieapp.persistence.MovieDatabase
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.schedulers.Schedulers

object MovieModelImpl : MovieModel, BaseModel() {

    //dataagent
   // private val mMovieDataAgent: MovieDataAgent = RetrofitDataAgentImpl


/*
    override fun getPopularMovies(onSuccess: (List<MovieVO>) -> Unit, onFailure: (String) -> Unit) {

        onSuccess(movieDb?.movieDao()?.getMovieListByType(POPULAR) ?: listOf())
        mTheMovieApi.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {

                it.results?.forEach { movie -> movie.type = POPULAR }
                movieDb?.movieDao()?.insertMovies(it.results ?: listOf())
                onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })
    }
*/


    override fun getPopularMovies( onFailure: (String) -> Unit):LiveData<List<MovieVO>>? {


        mTheMovieApi.getPopularMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {

                it.results?.forEach { movie -> movie.type = POPULAR }
                movieDb?.movieDao()?.insertMovies(it.results ?: listOf())
              //  onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })

       return movieDb?.movieDao()?.getMovieListByType(POPULAR)
    }

//    override fun getTopRatedMovies(
//        onSuccess: (List<MovieVO>) -> Unit,
//        onFailure: (String) -> Unit
//    ) {
//        onSuccess(movieDb?.movieDao()?.getMovieListByType(TOP_RATED) ?: listOf())
//        mTheMovieApi.getTopRatedMovies()
//            .subscribeOn(Schedulers.io())
//            .observeOn(AndroidSchedulers.mainThread())
//            .subscribe( {
//
//                it.results?.forEach { movie -> movie.type = TOP_RATED }
//                movieDb?.movieDao()?.insertMovies(it.results ?: listOf())
//                onSuccess(it.results ?: listOf())
//            }, {
//                onFailure(it.localizedMessage ?: "")
//
//            })
//    }

    override fun getTopRatedMovies(
        onFailure: (String) -> Unit
    ):LiveData<List<MovieVO>>? {

        mTheMovieApi.getTopRatedMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {

                it.results?.forEach { movie -> movie.type = TOP_RATED }
                movieDb?.movieDao()?.insertMovies(it.results ?: listOf())

            }, {
                onFailure(it.localizedMessage ?: "")

            })

        return movieDb?.movieDao()?.getMovieListByType(TOP_RATED)
    }


    override fun getNowPlayingMovies(onFailure: (String) -> Unit): LiveData<List<MovieVO>>? {


        mTheMovieApi.getNowPlayingMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {

                it.results?.forEach { movie -> movie.type = NOW_PLAYING }
                movieDb?.movieDao()?.insertMovies(it.results ?: listOf())
                //  onSuccess(it.results ?: listOf())
            }, {
                onFailure(it.localizedMessage ?: "")
            })

        return movieDb?.movieDao()?.getMovieListByType(NOW_PLAYING)
    }

    override fun getGenreList(onSuccess: (List<GenreVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieApi.getGenreList()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                onSuccess(it.genres ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })
        //(onSuccess, onFailure)
    }

    override fun getMoviesByGenre(
        genreId: String,
        onSuccess: (List<MovieVO>) -> Unit,
        onFailure: (String) -> Unit
    ) {
        mTheMovieApi.getMoviesByGenre(with_genre = genreId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                onSuccess(it.results ?: listOf())
            },{
                onFailure(it.localizedMessage ?: "")
            })

    }

    override fun getActors(onSuccess: (List<ActorVO>) -> Unit, onFailure: (String) -> Unit) {
        mTheMovieApi.getActors()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe( {
                onSuccess(it.results ?: listOf())
            },
                {
                    onFailure(it.localizedMessage ?: "")
                })

    }

  /*  override fun getMovieDetail(
        movieId: String,
        onSuccess: (MovieVO) -> Unit,
        onFailure: (String) -> Unit
    ) {
        //database
        val movie = movieDb?.movieDao()?.getSingleMovie(movieId = movieId.toInt())
        movie?.let {
            onSuccess(it)
        }

        //network
        mTheMovieApi.getMovieDetail(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                val movie = movieDb?.movieDao()?.getSingleMovie(movieId = movieId.toInt())
                it.type = movie?.type
                movieDb?.movieDao()?.insertSingleMovie(it)
                onSuccess(it)
            }, {
                onFailure(it.localizedMessage ?: "")
            })

    }*/

    override fun getMovieDetail(
        movieId: String,
        onFailure: (String) -> Unit
    ) :LiveData<MovieVO?>?{

        //network
        mTheMovieApi.getMovieDetail(movieId)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                val movie = movieDb?.movieDao()?.getSingleMovieOneTime(movieId = movieId.toInt())
                it.type = movie?.type
                movieDb?.movieDao()?.insertSingleMovie(it)

            }, {
                onFailure(it.localizedMessage ?: "")
            })
        return movieDb?.movieDao()?.getSingleMovie(movieId = movieId.toInt())
    }


        override fun getMovieCredits(
            movieId: String,
            onSuccess: (Pair<List<ActorVO>, List<ActorVO>>) -> Unit,
            onFailure: (String) -> Unit
        ) {
            mTheMovieApi.getCreditByMovie(movieId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({

                    onSuccess(Pair(it.cast ?: listOf(), it.crew ?: listOf()))

                }, {

                    onFailure(it.localizedMessage ?: "")

                })

        }

    override fun searchMovie(name: String): Observable<List<MovieVO>> {

      return  mTheMovieApi.searchMovie(query = name)
            .map { it.results ?: listOf()    }
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }

    fun change(name: String){
          mTheMovieApi.searchMovie(query = name)
            .map { it.results ?: listOf()    }
              .flatMapIterable {
                  it->it
              }.map {
                  mvo->TestNameVo(mvo.type)
              }.toList()
              .toObservable()
            .onErrorResumeNext { Observable.just(listOf()) }
            .subscribeOn(Schedulers.io())
    }

}