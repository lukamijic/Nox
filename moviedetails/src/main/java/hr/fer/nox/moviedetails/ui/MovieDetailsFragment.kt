package hr.fer.nox.moviedetails.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.annotation.LayoutRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import hr.fer.nox.coreui.base.BaseFragment
import hr.fer.nox.coreui.base.BaseView
import hr.fer.nox.coreui.base.ViewPresenter
import hr.fer.nox.coreui.util.ImageUtils
import hr.fer.nox.moviedetails.R
import hr.fer.nox.moviedetails.di.MOVIES_DETAILS_VIEW_SCOPE
import kotlinx.android.synthetic.main.fragment_movie_details.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.YouTubePlayerCallback
import hr.fer.nox.moviedetails.ui.adapter.ActorsAdapter
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper

class MovieDetailsFragment: BaseFragment<MovieDetailsViewState>(), MovieDetailsContract.View {

    companion object {

        const val TAG = "MovieDetailsFragment"

        @LayoutRes
        private val LAYOUT_RESOURCE: Int = R.layout.fragment_movie_details

        private const val MOVIE_ID_KEY = "movie_id"

        fun newInstance(movieId: Int): Fragment =
            MovieDetailsFragment().apply {
                arguments = Bundle().apply {
                    putInt(MOVIE_ID_KEY, movieId)
                }
            }
    }

    private val imageUtils: ImageUtils by inject()
    private val presenter: MovieDetailsContract.Presenter by scopedInject(
        parameters = { parametersOf(getMovieIdFromBundle()) }
    )
    private val actorsAdapter: ActorsAdapter by lazy {
        ActorsAdapter(LayoutInflater.from(context), imageUtils)
    }

    override fun initialiseView(view: View, savedInstanceState: Bundle?) {
        lifecycle.addObserver(moviedetails_youtubePlayer)

        moviedetails_actorsRecyclerView.apply {
            adapter = actorsAdapter
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            OverScrollDecoratorHelper.setUpOverScroll(this, OverScrollDecoratorHelper.ORIENTATION_HORIZONTAL)
        }

        moviedetails_backButton.setOnClickListener { goBack() }
        moviedetails_smallPoster.setOnClickListener { showMoviePoster() }
        moviedetails_smallSynopsis.setOnClickListener { showFullSynopsis() }
        moviedetails_likeIcon.setOnClickListener { presenter.likeAction() }
    }

    override fun render(viewState: MovieDetailsViewState) {
        with(viewState) {
            if(isLoading) {
                moviedetails_progressView.isVisible = true
                moviedetails_detailsContainer.isVisible = false
                return
            }

            moviedetails_progressView.isVisible = false
            moviedetails_detailsContainer.isVisible = true

            if(videoId.isEmpty()) {
                moviedetails_youtubePlayer.isVisible = false
            } else {
                moviedetails_youtubePlayer.isVisible= true
                moviedetails_youtubePlayer.getYouTubePlayerWhenReady(object: YouTubePlayerCallback {
                    override fun onYouTubePlayer(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.loadVideo(videoId, 0.0f)
                        youTubePlayer.pause()
                    }
                })
            }

            moviedetails_movieTitle.text = movieTitle
            moviedetails_releaseYear.text = movieYearRelease.toString()
            moviedetails_movieDuration.text = movieDuration
            moviedetails_movieGenres.text = movieGenres
            moviedetails_likeIcon.setImageResource(if (isLiked) R.drawable.ic_liked else R.drawable.ic_like)
            moviedetails_addToWatchListIcon.setImageResource(if (isOnWatchList) R.drawable.ic_added_to_watchlist else R.drawable.ic_add_to_watchlist)
            moviedetails_watchedIcon.setImageResource(if (isWatched) R.drawable.ic_watched else R.drawable.ic_watch)

            moviePosterUrl?.run {
                imageUtils.loadInto(this, moviedetails_smallPoster)
                imageUtils.loadInto(this, moviedetails_bigMoviePoster)
            }

            moviedetails_smallSynopsis.text = movieSynopsis
            moviedetails_fullSynopsis.text = movieSynopsis

            moviedetails_imdbScore.text = imdbScore
            moviedetails_tomatoScore.text = tomatoScore
            moviedetails_metacriticScore.text = metacriticScore

            moviedetails_directorName.text = directorName

            actorsAdapter.submitList(actors)
        }
    }

    private fun showMoviePoster() {
        moviedetails_moviePosterContainer.isVisible = true
    }

    private fun showFullSynopsis() {
        moviedetails_synopsisContainer.isVisible = true
    }

    private fun goBack() {
        if (moviedetails_moviePosterContainer.isVisible)  {
            moviedetails_moviePosterContainer.isVisible = false
        } else if (moviedetails_synopsisContainer.isVisible) {
            moviedetails_synopsisContainer.isVisible = false
        } else {
            presenter.back()
        }
    }

    private fun getMovieIdFromBundle(): Int = arguments?.getInt(MOVIE_ID_KEY) ?: throw IllegalArgumentException("Movie id not found")

    override fun getLayoutResource(): Int = LAYOUT_RESOURCE
    override fun getScopeName(): String = MOVIES_DETAILS_VIEW_SCOPE
    override fun getViewPresenter(): ViewPresenter<BaseView, MovieDetailsViewState> = presenter as ViewPresenter<BaseView, MovieDetailsViewState>
}