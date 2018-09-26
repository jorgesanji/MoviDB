package com.test.movies.business.usecase.movies

import com.test.movies.business.usecase.base.BaseUseCase
import com.test.movies.business.usecase.base.BaseUseCaseImpl
import com.test.movies.data.model.MovieResponse
import com.test.movies.data.repository.RepositoryProxy
import rx.Observable
import javax.inject.Inject

interface GetMoviesUseCase : BaseUseCase<MovieResponse> {
    var page: Int
}

class GetMovies @Inject constructor(repositoryFactory: RepositoryProxy) : BaseUseCaseImpl<MovieResponse>(repositoryFactory), GetMoviesUseCase {

    override var page: Int = 0

    override fun buildUseCaseObservable(): Observable<MovieResponse> {
        return repositoryFactory.repository.getMovieList(page)
    }
}
