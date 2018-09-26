package com.test.movies.data.repository

import com.test.movies.data.model.MovieResponse
import rx.Observable

interface Repository {
    fun getMovieList(page:Int) : Observable<MovieResponse>
}
