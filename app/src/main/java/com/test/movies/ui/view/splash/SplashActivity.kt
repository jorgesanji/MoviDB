package com.test.movies.ui.view.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.support.v7.app.AppCompatActivity

import com.test.movies.ui.view.AppNavigation

class SplashActivity : AppCompatActivity() {
    private val handler = Handler(Looper.myLooper())
    private val runnable = Runnable {
        val appNavigation = AppNavigation(this@SplashActivity)
        appNavigation.launchHome()
    }

    override fun onResume() {
        super.onResume()
        handler.postDelayed(runnable, splashTime)
    }

    override fun onPause() {
        super.onPause()
        handler.removeCallbacks(runnable)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(SplashScreen(this))
    }

    companion object {

        private val splashTime: Long = 2000
    }
}
