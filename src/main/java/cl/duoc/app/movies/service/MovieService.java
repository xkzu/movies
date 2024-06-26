package cl.duoc.app.movies.service;

import cl.duoc.app.movies.model.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    Optional<Movie> getMovie(Long id);

    List<Movie> getMovies();

    Movie addMovie(Movie movie);

    void updateMovie(Movie movie);

    void deleteMovie(Long id);
}
