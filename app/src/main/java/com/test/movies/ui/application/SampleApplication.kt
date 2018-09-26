package com.test.movies.ui.application

import android.content.Context
import android.support.multidex.MultiDex
import android.support.multidex.MultiDexApplication

import com.squareup.picasso.Picasso
import com.test.movies.data.local.SessionManager

import com.test.movies.di.component.ApplicationComponent
import com.test.movies.di.component.DaggerApplicationComponent
import com.test.movies.di.module.ApplicationModule
import javax.inject.Inject

class SampleApplication : MultiDexApplication() {

    @Inject
    lateinit var sessionManager:SessionManager

    var applicationComponent: ApplicationComponent? = null
         set

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()
        initGraph()
        applicationComponent!!.inject(this)
        initPicasso()
        initSession()
        instance = this
    }

    private fun initSession(){
        sessionManager.initSession()
    }

    private fun initPicasso() {
        Picasso.with(this).isLoggingEnabled = true
    }

    private fun initGraph() {
        applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this))
                .build()
    }

    companion object {

        var instance: SampleApplication? = null
            private set
    }
}
