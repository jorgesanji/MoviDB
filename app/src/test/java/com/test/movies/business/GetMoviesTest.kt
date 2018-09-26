package com.test.movies.business

import com.test.movies.business.usecase.base.BaseSubscriber
import com.test.movies.business.usecase.base.BaseUseCaseImpl
import com.test.movies.business.usecase.movies.GetMoviesUseCase
import com.test.movies.data.model.MovieResponse
import com.test.movies.data.repository.RepositoryProxy
import com.test.movies.integration.RepositoryProxyTest
import rx.Observable

interface GetMoviesTestUseCase :GetMoviesUseCase

class GetMoviesTest(repositoryFactory: RepositoryProxyTest) : BaseUseCaseImpl<MovieResponse>(repositoryFactory), GetMoviesUseCase{

    override var page: Int = 0

    override fun subscribe(subscriber: BaseSubscriber<MovieResponse>?) {
        super.subscribeTest(subscriber)
    }

    override fun buildUseCaseObservable(): Observable<MovieResponse> {
        return repositoryFactory.getRepository(RepositoryProxy.RepositoryType.MOCK).getMovieList(page)
    }
}