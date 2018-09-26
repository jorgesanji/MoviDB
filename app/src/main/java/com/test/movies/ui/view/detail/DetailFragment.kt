package com.test.movies.ui.view.detail

import android.app.Activity
import android.view.View
import com.test.movies.di.InjectorHelper
import com.test.movies.ui.presenter.detail.DetailPresenter
import com.test.movies.ui.view.base.MVPFragment

class DetailFragment : MVPFragment<DetailPresenter, DetailPresenter.View>(), DetailPresenter.View, DetailScreen.Listener {

    private var detailScreen: DetailScreen? = null

    override val rootView: View
        get() {
            detailScreen = DetailScreen(activity!!)
            detailScreen!!.setListener(this)
            return detailScreen as DetailScreen
        }

    override fun injectDependencies() {
        InjectorHelper.getPresenterComponent(activity!!).inject(this)
    }

    override fun onDidAppear() {
        presenter.getMovieDetail()
    }

    /*
    DetailPresenter View
    */
    override fun setInfo(title: String?, overview: String?, image: String?) {
        detailScreen!!.setInfo(title, overview, image)
    }

    /*
     DetailScreen Listener
     */
}