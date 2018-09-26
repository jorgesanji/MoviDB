package com.test.movies.integration

import android.app.Application
import com.test.movies.business.usecase.base.BaseSubscriber
import com.test.movies.business.usecase.movies.GetMovies
import com.test.movies.data.model.MovieResponse
import com.test.movies.data.repository.LocalRepository
import com.test.movies.data.repository.MockRepository
import com.test.movies.data.repository.RestRepository
import com.test.movies.data.rest.service.ApiError
import com.test.movies.data.rest.service.RestAdapter
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.runners.MockitoJUnitRunner

@RunWith(MockitoJUnitRunner::class)
class IntegrationTest {

    val apikey = "707267441ce3a621da2f4f5222239fbb"
    val language = "en-US"
    @Mock
    lateinit var mMockContext: Application

    lateinit var repositoryProxy: RepositoryProxyTest
    lateinit var getMovies: GetMovies

    @Before
    fun initUseCases() {
        val restAdapter = RestAdapter()
        restAdapter.language = language
        restAdapter.apikey = apikey
        val restRepository = RestRepository(restAdapter)
        val localRepository = LocalRepository()
        val mockRepository = MockRepository()
        this.repositoryProxy = RepositoryProxyTest(mMockContext, restRepository,
                localRepository, mockRepository)
        this.getMovies = GetMovies(repositoryProxy)
    }

    @Test
    fun getMoviesOnePageTest() {
        getMovies.page = 1
        getMovies.subscribeTest(object : BaseSubscriber<MovieResponse>() {
            override fun onError(apiError: ApiError) {
                super.onError(apiError)
                assert(apiError != null)
            }

            override fun onNext(response: MovieResponse) {
                super.onNext(response)
                assert(!response.results!!.isEmpty()){"No movies retrieved"}
            }
        })
    }

    @Test
    fun getMoviesZeroPageTest() {
        getMovies.page = 0
        getMovies.subscribeTest(object : BaseSubscriber<MovieResponse>() {
            override fun onError(apiError: ApiError) {
                super.onError(apiError)
                assert(apiError != null){"Page should be major than 0"}
            }
        })
    }

    @Test
    fun getMoviesPaginationTest() {
        var pagination = true
        var maxPage = 0
        var page = 0
        while(pagination){
            page++
            getMovies.page = page
            getMovies.subscribeTest(object : BaseSubscriber<MovieResponse>() {
                override fun onError(apiError: ApiError) {
                    super.onError(apiError)
                    assert(apiError != null)
                }

                override fun onNext(response: MovieResponse) {
                    super.onNext(response)
                    maxPage = response.totalPages!!
                }
            })
            pagination = !(page >= maxPage)
        }
        assert(page == maxPage){"Bad pagination"}
    }
}

