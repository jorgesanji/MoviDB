package com.test.movies.di.module

import android.app.Activity

import com.test.movies.di.PerFragment
import com.test.movies.ui.view.AppNavigation
import com.test.movies.ui.view.IONavigation

import dagger.Module
import dagger.Provides

@Module
class NavigatorModule(private val activity: Activity) {

    @Provides
    @PerFragment
    internal fun provideNavigator(): IONavigation {
        return AppNavigation(activity)
    }
}
