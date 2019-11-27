package hr.fer.nox.movieslib.model

data class MovieDetails(
    val movieId: Int,
    val movieTitle: String,
    val movieYearRelease: Int,
    val movieDurationInMinutes: Int,
    val movieGenres: List<String>,
    val videoId: String,
    val isLiked: Boolean,
    val moviePosterUrl: String?,
    val movieSynopsis: String,
    val imdbScore: String,
    val tomatoScore: String,
    val metacriticScore: String,
    val directorName: String,
    val actors: List<Actor>
)