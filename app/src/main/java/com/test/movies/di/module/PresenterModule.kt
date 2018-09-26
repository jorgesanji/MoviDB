package com.test.movies.di.module

import com.test.movies.business.usecase.movies.GetMoviesUseCase
import com.test.movies.di.PerFragment
import com.test.movies.ui.presenter.detail.DetailPresenter
import com.test.movies.ui.presenter.home.HomePresenter
import com.test.movies.ui.view.IONavigation
import dagger.Module
import dagger.Provides

@Module
class PresenterModule {

    @Provides
    @PerFragment
    internal fun provideHomePresenter(appNavigation: IONavigation, getMovies: GetMoviesUseCase): HomePresenter {
        return HomePresenter(appNavigation, getMovies)
    }

    @Provides
    @PerFragment
    internal fun provideDetailPresenter(appNavigation: IONavigation): DetailPresenter {
        return DetailPresenter(appNavigation)
    }
}
