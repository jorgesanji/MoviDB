package com.test.movies.data.rest.service

import com.test.movies.data.model.MovieResponse
import retrofit2.http.GET
import retrofit2.http.Query
import rx.Observable

interface RestApiService {

    @GET("movie/now_playing")
    fun getMovies(@Query("page")page:Int) : Observable<MovieResponse>
}
