package com.test.movies.ui.presenter.detail

import com.test.movies.data.model.Movie
import com.test.movies.ui.presenter.base.PrensenterImpl
import com.test.movies.ui.presenter.base.Presenter
import com.test.movies.ui.utils.BundleConstants.movieKey
import com.test.movies.ui.view.IONavigation

class DetailPresenter(appNavigation: IONavigation) : PrensenterImpl<DetailPresenter.View>(appNavigation) {

    interface View : Presenter.View {
        fun setInfo(title:String?, overview:String?, image:String?)
    }

    fun getMovieDetail(){
        val movie = view.fragment.activity!!.intent.extras[movieKey] as? Movie
        view.activity.setTitle(movie!!.title)
        view.setInfo(movie.title, movie.overview, movie.posterImage)
    }
}
