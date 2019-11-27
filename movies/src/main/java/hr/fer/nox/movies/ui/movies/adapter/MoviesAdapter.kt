package hr.fer.nox.movies.ui.movies.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import hr.fer.nox.coreui.util.DiffUtilCallback
import hr.fer.nox.coreui.util.ImageUtils
import hr.fer.nox.movies.R
import hr.fer.nox.movies.model.MovieItemViewModel
import kotlinx.android.synthetic.main.item_movies_movie.view.*

class MoviesAdapter(
    private val layoutInflater: LayoutInflater,
    private val imageUtils: ImageUtils,
    private val movieSelectedAction: (MovieItemViewModel) -> Unit
): ListAdapter<MovieItemViewModel, MoviesAdapter.MovieViewHolder>(DiffUtilCallback()) {

    companion object {

        @LayoutRes
        private val LAYOUT_RESOURCE_MOVIE = R.layout.item_movies_movie
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder =
        MovieViewHolder(layoutInflater.inflate(LAYOUT_RESOURCE_MOVIE, parent, false))


    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) =
        holder.fillView(getItem(position))

    inner class MovieViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun fillView(item: MovieItemViewModel) {
            item.moviePosterPath?.run { imageUtils.loadInto(this, itemView.movies_moviePoster) }
            itemView.setOnClickListener { movieSelectedAction(item) }
        }
    }
}