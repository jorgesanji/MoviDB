package com.test.movies.di.module

import android.app.Application
import com.test.movies.data.local.SessionManager
import com.test.movies.data.repository.LocalRepository
import com.test.movies.data.repository.MockRepository
import com.test.movies.data.repository.RestRepository
import com.test.movies.data.rest.service.RestAdapter
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val application: Application) {

    @Provides
    @Singleton
    fun provideApplicationContext(): Application {
        return this.application
    }

    @Provides
    @Singleton
    fun provideRestRepository(restAdapter: RestAdapter): RestRepository {
        return RestRepository(restAdapter)
    }

    @Provides
    @Singleton
    fun provideLocalRepository(): LocalRepository {
        return LocalRepository()
    }

    @Provides
    @Singleton
    fun provideMockRepository(): MockRepository {
        return MockRepository()
    }

    @Provides
    @Singleton
    fun provideRestAdapter(): RestAdapter {
        return RestAdapter()
    }

    @Provides
    @Singleton
    fun provideSessionManager(restAdapter: RestAdapter): SessionManager{
        return SessionManager(restAdapter, application)
    }

}
