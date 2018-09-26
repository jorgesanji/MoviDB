package com.test.movies.data.repository

import com.test.movies.data.model.MovieResponse
import com.test.movies.data.rest.service.RestAdapter
import rx.Observable

class RestRepository(internal var restAdapter: RestAdapter) : Repository {

    override fun getMovieList(page: Int): Observable<MovieResponse> {
        return restAdapter.service.getMovies(page)
    }
}

