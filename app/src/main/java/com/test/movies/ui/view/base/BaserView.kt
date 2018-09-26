package com.test.movies.ui.view.base

import android.util.AttributeSet

interface BaserView {

    val layout: Int

    fun initUI(attributeSet: AttributeSet?)
}
