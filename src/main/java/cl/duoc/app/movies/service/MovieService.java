package cl.duoc.app.movies.service;

import cl.duoc.app.movies.model.Movie;

import java.util.List;

public interface MovieService {

    Movie getMovie();

    List<Movie> getMovies();
}
