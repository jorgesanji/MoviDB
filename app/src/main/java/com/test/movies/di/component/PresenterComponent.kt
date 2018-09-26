package com.test.movies.di.component

import com.test.movies.di.PerFragment
import com.test.movies.di.module.NavigatorModule
import com.test.movies.di.module.PresenterModule
import com.test.movies.di.module.UseCaseModule
import com.test.movies.ui.view.detail.DetailFragment
import com.test.movies.ui.view.home.HomeFragment
import dagger.Component

@PerFragment
@Component(modules = arrayOf(PresenterModule::class, NavigatorModule::class, UseCaseModule::class), dependencies = arrayOf(ApplicationComponent::class))
interface PresenterComponent {

    fun inject(homeFragment: HomeFragment)
    fun inject(detailFragment: DetailFragment)

}
