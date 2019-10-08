package hr.fer.nox.movies.ui.movies

import hr.fer.nox.movies.R
import hr.fer.nox.movies.model.MovieItemViewModel

data class MoviesViewState(
    val moviesTemplateItemViewModel: List<MovieItemViewModel>
)

fun DEFAULT(): List<MovieItemViewModel> =
    listOf(
        MovieItemViewModel(R.drawable.battle),
        MovieItemViewModel(R.drawable.boruto),
        MovieItemViewModel(R.drawable.dedpul),
        MovieItemViewModel(R.drawable.dk),
        MovieItemViewModel(R.drawable.joker),
        MovieItemViewModel(R.drawable.lotr1),
        MovieItemViewModel(R.drawable.lotr2),
        MovieItemViewModel(R.drawable.pikachu),
        MovieItemViewModel(R.drawable.spoderman),
        MovieItemViewModel(R.drawable.your_name),
        MovieItemViewModel(R.drawable.battle),
        MovieItemViewModel(R.drawable.boruto),
        MovieItemViewModel(R.drawable.dedpul),
        MovieItemViewModel(R.drawable.dk),
        MovieItemViewModel(R.drawable.joker),
        MovieItemViewModel(R.drawable.lotr1),
        MovieItemViewModel(R.drawable.lotr2),
        MovieItemViewModel(R.drawable.pikachu),
        MovieItemViewModel(R.drawable.spoderman),
        MovieItemViewModel(R.drawable.your_name),
        MovieItemViewModel(R.drawable.battle),
        MovieItemViewModel(R.drawable.boruto),
        MovieItemViewModel(R.drawable.dedpul),
        MovieItemViewModel(R.drawable.dk),
        MovieItemViewModel(R.drawable.joker),
        MovieItemViewModel(R.drawable.lotr1),
        MovieItemViewModel(R.drawable.lotr2),
        MovieItemViewModel(R.drawable.pikachu),
        MovieItemViewModel(R.drawable.spoderman),
        MovieItemViewModel(R.drawable.your_name)
    ).shuffled()