package com.test.movies.data.repository

import com.test.movies.data.model.MovieResponse
import com.test.movies.data.utils.RetrofitException
import rx.Observable
import javax.inject.Inject

class LocalRepository @Inject
constructor() : Repository {

    override fun getMovieList(page: Int): Observable<MovieResponse> {
        return Observable.fromCallable { throw RetrofitException.unexpectedError(null) }
    }
}
