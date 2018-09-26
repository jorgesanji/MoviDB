package com.test.movies.di.component

import android.app.Application
import com.test.movies.data.local.SessionManager
import com.test.movies.data.repository.LocalRepository
import com.test.movies.data.repository.MockRepository
import com.test.movies.data.repository.RestRepository
import com.test.movies.data.rest.service.RestAdapter
import com.test.movies.di.module.ApplicationModule
import com.test.movies.ui.application.SampleApplication
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun provideApplication(): Application

    fun provideRestRepository(): RestRepository

    fun provideLocalRepository(): LocalRepository

    fun provideMockRepository(): MockRepository

    fun provideRestAdapter(): RestAdapter

    fun provideSessionManager(): SessionManager

    fun inject(application: SampleApplication)
}
