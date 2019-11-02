package hr.fer.nox.moviedetails.ui

import hr.fer.nox.moviedetails.model.ActorViewModel

data class MovieDetailsViewState(
    var movieId: String,
    var movieTitle: String,
    var movieYearRelease: Int,
    var movieDuration: String,
    var movieGenres: String,
    var videoId: String,
    var isLiked: Boolean,
    var isOnWatchList: Boolean,
    var isWatched: Boolean,
    var moviePosterUrl: String?,
    var movieSynopsis: String,
    var imdbScore: String,
    var tomatoScore: String,
    var metacriticScore: String,
    var directorName: String,
    var actors: List<ActorViewModel>,
    var isLoading: Boolean
) {

    companion object {

        val EMPTY = MovieDetailsViewState(
            "",
            "",
            0,
            "",
            "",
            "",
            false,
            false,
            false,
            null,
            "",
            "",
            "",
            "",
            "",
            emptyList(),
            true)

        val JOKER = MovieDetailsViewState(
            "1",
            "Joker",
            2019,
            "2h 2m",
            "Crime, Drama, Thriller",
            "zAGVQLHvwOY",
            true,
            false,
            true,
            "https://m.media-amazon.com/images/M/MV5BNGVjNWI4ZGUtNzE0MS00YTJmLWE0ZDctN2ZiYTk2YmI3NTYyXkEyXkFqcGdeQXVyMTkxNjUyNQ@@._V1_.jpg",
            "Arthur Fleck is a wannabe stand-up comic who suffers from many mental illnesses, including one which causes him to laugh uncontrollably when he is nervous, and often gets him into bad situations. Arthur's mental health causes almost all people in society to reject and look down upon him, even though all he wants is to be accepted by others. After being brutally beaten, having his medication cut off, Arthur's life begins to spiral downward out-of-control into delusions, violence, and anarchy until he eventually transforms into Gotham's infamous Clown-Prince of Crime.",
            "8.8/10",
            "69%",
            "59",
            "Todd Philips",
            listOf(
                ActorViewModel(
                    "Joaquin Phoenix",
                    "Arthur Fleck",
                    "https://m.media-amazon.com/images/M/MV5BNzAzNjg5MDE3N15BMl5BanBnXkFtZTcwMjIxNzU0OA@@._V1_.jpg"
                ),
                ActorViewModel(
                    "Robert De Niro",
                    "Murray Franklin",
                    "https://m.media-amazon.com/images/M/MV5BMjAwNDU3MzcyOV5BMl5BanBnXkFtZTcwMjc0MTIxMw@@._V1_UY1200_CR139,0,630,1200_AL_.jpg"
                ),
                ActorViewModel(
                    "Zazie Beetz",
                    "Sophie Dumond",
                    "https://vignette.wikia.nocookie.net/batman/images/6/64/Sophie_Dumond.png/revision/latest?cb=20191006204033"
                ),
                ActorViewModel(
                    "Frances Conroy",
                    "Penny Fleck",
                    "https://vignette.wikia.nocookie.net/americanhorrorstory/images/0/08/Frances2017.png/revision/latest?cb=20171028234034"
                ),
                ActorViewModel(
                    "Brett Cullen",
                    "Thomas Wayne",
                    "https://vignette.wikia.nocookie.net/westwing/images/c/cc/BrettCullen.jpg/revision/latest?cb=20120303050057"
                ),
                ActorViewModel(
                    "Glenn Fleshler",
                    "Randall",
                    "http://images.fandango.com/ImageRenderer/0/0/redesign/static/img/default_poster.png/0/images/masterrepository/performer%20images/p772912/GlennFleshler.jpg"
                )
            ),
            false
        )
    }
}