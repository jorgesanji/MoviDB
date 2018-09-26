package com.test.movies.ui.view.home

import android.os.Bundle

import com.test.movies.R
import com.test.movies.ui.view.base.BaseActivity

class HomeActivity : BaseActivity<HomeFragment>() {

    override val fragment: Class<HomeFragment>
        get() = HomeFragment::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        title = getString(R.string.title_home)
    }

    override fun toolbarColor(): Int {
        return R.color.colorPrimary
    }

    override fun centerTitle(): Boolean {
        return true
    }

}
