package com.test.movies.ui.view.splash

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity

import com.test.movies.R
import com.test.movies.ui.view.base.BaseLinearLayout

class SplashScreen(context: Context) : BaseLinearLayout(context) {

    override val layout: Int
        get() = R.layout.splash_lay

    override fun initUI(attributeSet: AttributeSet?) {
        gravity = Gravity.CENTER
    }
}
