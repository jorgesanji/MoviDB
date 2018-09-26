package com.test.movies.data.repository

import com.test.movies.data.model.MovieResponse
import rx.Observable
import javax.inject.Inject

class MockRepository @Inject
constructor() : Repository {

    override fun getMovieList(page: Int): Observable<MovieResponse> {
        var response = MovieResponse()
        return Observable.just(MovieResponse())
    }
}
