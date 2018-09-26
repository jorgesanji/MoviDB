package com.test.movies.ui.view.detail

import android.os.Bundle
import com.test.movies.R
import com.test.movies.ui.view.base.BaseActivity

class DetailActivity : BaseActivity<DetailFragment>() {

    override val fragment: Class<DetailFragment>
        get() = DetailFragment::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun toolbarColor(): Int {
        return R.color.colorPrimary
    }

    override fun centerTitle(): Boolean {
        return false
    }

}
