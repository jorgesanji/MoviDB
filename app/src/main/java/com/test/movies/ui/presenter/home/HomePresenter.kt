package com.test.movies.ui.presenter.home

import android.os.Bundle
import com.test.movies.business.usecase.base.BaseSubscriber
import com.test.movies.business.usecase.movies.GetMoviesUseCase
import com.test.movies.data.model.Movie
import com.test.movies.data.model.MovieResponse
import com.test.movies.data.rest.service.ApiError
import com.test.movies.ui.presenter.base.PrensenterImpl
import com.test.movies.ui.presenter.base.Presenter
import com.test.movies.ui.utils.BundleConstants.movieKey
import com.test.movies.ui.utils.IODataSource
import com.test.movies.ui.view.IONavigation
import java.util.*
import kotlin.collections.ArrayList

class HomePresenter(appNavigation: IONavigation, private var getMovies: GetMoviesUseCase) : PrensenterImpl<HomePresenter.View>(appNavigation), IODataSource<Movie> {

    private var allMovies: List<Movie> = ArrayList()
    private var movies: List<Movie> = ArrayList()
    private var page: Int = 0
    private var loading: Boolean = false
    private val sizePage = 20
    private var maxPages: Int = 0
    private var sortingByLatest = true
    private var fromDate:Date? = null

    interface View : Presenter.View {
        fun reloadData()
    }

    private fun retrieveMoreDataIfNeeded(at: Int) {
        if (page < maxPages && at >= (movies.size - sizePage / 2) && !loading) {
            getMovies()
        }
    }

    private fun filter(array: List<Movie>?) : List<Movie>{
        val list =  array!!.filter { s -> s.date!!.compareTo(fromDate) == 1  || s.date!!.compareTo(fromDate) == 0}
        Collections.sort(list, ComparatorMovies())
        return list
    }

    private fun sort(result:Array<Movie>?){
        val arrayList = result!!.toCollection(ArrayList())
        var addResult:List<Movie> = movies + arrayList
        this.allMovies = addResult
        if (fromDate != null){
            addResult = filter(addResult)
        }else if(sortingByLatest){
            Collections.sort(addResult, ComparatorMovies())
        }else{
            Collections.sort(addResult)
        }
        this.movies = addResult
    }

    fun getMovies() {
        if (movies.isEmpty()) {
            view.showLoading()
        }
        page++
        loading = true
        getMovies.page = page
        getMovies.subscribe(object : BaseSubscriber<MovieResponse>() {

            override fun onError(apiError: ApiError) {
                super.onError(apiError)
                this@HomePresenter.view.hideLoading()
                this@HomePresenter.view.showInternetConnectionError()
                this@HomePresenter.loading = false
            }

            override fun onNext(response: MovieResponse) {
                super.onNext(response)
                this@HomePresenter.sort(response.results)
                this@HomePresenter.maxPages = response.total_pages!!
                this@HomePresenter.view.reloadData()
                this@HomePresenter.view.hideLoading()
                this@HomePresenter.loading = false
            }
        })
    }

    fun filterFromDate(date:Date?){
        this.fromDate = date
        movies = filter(allMovies)
        view.reloadData()
    }

    fun sortByLatestMovies() {
        sortingByLatest = true
        Collections.sort(movies, ComparatorMovies())
        view.reloadData()
    }

    fun sortByOldestMovies() {
        sortingByLatest = false
        Collections.sort(movies)
        view.reloadData()
    }

    fun launchDetail(position: Int){
        val movie = movies[position]
        var bundle = Bundle()
        bundle.putParcelable(movieKey, movie)
        appNavigation.launchDetail(bundle)
    }

    fun clearFilters(){
        sortingByLatest = true
        fromDate = null
        Collections.sort(allMovies, ComparatorMovies())
        movies = allMovies
        view.reloadData()
    }

    /**
     * Datasource
     */

    override val count: Int
        get() = movies.size

    override fun getItemAtPosition(position: Int): Movie {
        retrieveMoreDataIfNeeded(position)
        return movies[position]
    }

    private inner class ComparatorMovies() : Comparator<Movie> {

        override fun compare(movie1: Movie, movie2: Movie): Int {
            return movie2.compareTo(movie1)
        }
    }
}
