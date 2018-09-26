package com.test.movies.di

import android.app.Activity

import com.test.movies.di.component.DaggerPresenterComponent
import com.test.movies.di.component.PresenterComponent
import com.test.movies.di.module.NavigatorModule
import com.test.movies.di.module.PresenterModule
import com.test.movies.di.module.UseCaseModule
import com.test.movies.ui.application.SampleApplication

object InjectorHelper {

    fun getPresenterComponent(activity: Activity): PresenterComponent {
        return DaggerPresenterComponent.builder()
                .applicationComponent(SampleApplication.instance!!
                        .applicationComponent)
                .useCaseModule(UseCaseModule(activity))
                .navigatorModule(NavigatorModule(activity))
                .presenterModule(PresenterModule())
                .build()
    }
}
