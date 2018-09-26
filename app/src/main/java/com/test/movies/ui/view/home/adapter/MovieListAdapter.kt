package com.test.movies.ui.view.home.adapter

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.test.movies.R
import com.test.movies.data.model.Movie
import com.test.movies.ui.utils.IODataSource
import com.test.movies.ui.utils.ImageLoader

class MovieListAdapter(dataSource: IODataSource<Movie>) : RecyclerView.Adapter<MovieListAdapter.MovieViewHolder>() {

    private val dataSource: IODataSource<Movie>?

    init {
        this.dataSource = dataSource
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.lay_movie_item, parent, false)
        return MovieViewHolder(view)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(dataSource!!.getItemAtPosition(position))
    }

    override fun getItemCount(): Int {
        return dataSource?.count ?: 0
    }

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @BindView(R.id.iconIv)
        protected lateinit var iconIv: ImageView

        @BindView(R.id.titleTv)
        protected lateinit var title_tv: TextView

        @BindView(R.id.descriptionTv)
        protected lateinit var description_tv: TextView

        @BindView(R.id.dateTv)
        protected lateinit var date_tv: TextView

        init {
            ButterKnife.bind(this, itemView)
        }

        fun bind(movie: Movie) {
            ImageLoader.loadThumb(iconIv, movie.posterImage)
            title_tv.text = movie.title
            description_tv.text = movie.overview
            date_tv.text = DateFormat.getLongDateFormat(itemView.context).format(movie.date)
        }
    }
}
