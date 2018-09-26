package com.test.movies.ui.utils

import android.widget.ImageView
import com.squareup.picasso.Picasso
import com.test.movies.data.rest.service.RestConstants.imageBaseUrl

object ImageLoader {

    val backdrop_sizes = arrayOf("w300", "w780", "w1280", "original")
    val logo_sizes = arrayOf("w45","w92","w154","w185","w300","w500","original")
    val poster_sizes = arrayOf("w92", "w154", "w185", "w342", "w500", "w780", "original")

    fun loadThumb(imageView: ImageView, url:String?){
        val url = imageBaseUrl +logo_sizes[1]+url
        loadImage(imageView, url, android.R.drawable.gallery_thumb)
    }

    fun loadBackground(imageView: ImageView, url:String?){
        val url = imageBaseUrl +poster_sizes[5]+url
        loadImage(imageView, url, null, false, false)
    }

    private fun loadImage(imageView: ImageView, url: String?, placeHolder:Int?, roundCorners:Boolean = true, resize:Boolean = true) {
        val creator = Picasso.with(imageView.context).load(url)
        if (placeHolder != null){
            creator.placeholder(placeHolder)
        }
        if (roundCorners){
            creator.transform(RoundCornersTransform(false))
        }
        if(resize){
            creator.fit()
            creator.centerCrop()
        }
        creator.into(imageView)
    }
}
