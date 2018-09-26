package com.test.movies.di.module

import android.content.Context
import com.test.movies.business.usecase.movies.GetMovies
import com.test.movies.business.usecase.movies.GetMoviesUseCase
import com.test.movies.data.repository.RepositoryProxy
import com.test.movies.di.PerFragment
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule(private val activity: Context) {

    @Provides
    @PerFragment
    internal fun provideGetMovies(repositoryFactory: RepositoryProxy): GetMoviesUseCase {
        return GetMovies(repositoryFactory)
    }
}
