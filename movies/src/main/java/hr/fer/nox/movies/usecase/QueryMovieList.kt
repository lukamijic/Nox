package hr.fer.nox.movies.usecase

import hr.fer.nox.core.usecase.QueryUseCase
import hr.fer.nox.movieslib.model.Movie

interface QueryMovieList: QueryUseCase<List<Movie>>