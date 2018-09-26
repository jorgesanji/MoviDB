package com.test.movies.ui.view.detail

import android.content.Context
import android.util.AttributeSet
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import com.test.movies.R
import com.test.movies.ui.utils.ImageLoader
import com.test.movies.ui.view.base.BaseConstraintLayout

class DetailScreen(context: Context) : BaseConstraintLayout(context) {

    interface Listener {}

    @BindView(R.id.movieIv)
    protected lateinit var movieIv: ImageView

    @BindView(R.id.movieTitleTv)
    protected lateinit var movieTitleTv: TextView

    @BindView(R.id.movieDescritionTv)
    protected lateinit var movieDescritionTv: TextView

    private var listener: Listener? = null

    override val layout: Int
        get() = R.layout.lay_detail

    override fun initUI(attributeSet: AttributeSet?) {}

    fun setListener(listener: Listener) {
        this.listener = listener
    }

    fun setInfo(title: String?, overview: String?, image: String?) {
        ImageLoader.loadBackground(movieIv, image)
        movieTitleTv.text = title
        movieDescritionTv.text = overview
    }
}
