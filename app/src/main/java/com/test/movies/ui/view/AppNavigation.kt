package com.test.movies.ui.view

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import com.test.movies.ui.utils.Navigation
import com.test.movies.ui.view.detail.DetailActivity
import com.test.movies.ui.view.home.HomeActivity
import javax.inject.Inject


interface IONavigation{
    fun launchHome()
    fun launchDetail(bundle:Bundle)
}

class AppNavigation @Inject constructor(activity: Activity) : Navigation(activity), IONavigation{

    // ****************************
    //      INTENTS CREATION
    // ****************************

    private fun home(): Intent {
        return newTask(HomeActivity::class!!, null, true)
    }

    private fun detail(bundle:Bundle): Intent {
        return newTask(DetailActivity::class!!, bundle)
    }

    // ****************************
    //      ACTIONS DEFINITION
    // ****************************

    override fun launchHome() {
        startActivity(home())
    }

    override fun launchDetail(bundle:Bundle) {
        startActivity(detail(bundle), ActivityAnimation.SLIDE_LEFT)
    }
}
