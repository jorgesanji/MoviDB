package com.test.movies.integration

import android.app.Application

import com.test.movies.data.repository.LocalRepository
import com.test.movies.data.repository.MockRepository
import com.test.movies.data.repository.RepositoryProxy
import com.test.movies.data.repository.RestRepository

class  RepositoryProxyTest(context: Application, restRepository: RestRepository,
                          localRepository: LocalRepository, mockRepository: MockRepository) : RepositoryProxy(context, restRepository, localRepository, mockRepository) {

    override val isNetworkAvailable: Boolean
        get() = true
}
