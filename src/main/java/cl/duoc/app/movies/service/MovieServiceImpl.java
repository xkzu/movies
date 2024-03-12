package cl.duoc.app.movies.service;

import cl.duoc.app.movies.model.Movie;
import org.springframework.stereotype.Service;

import java.util.List;

@Service //se le indica a springboot que trate esta clase como un service
public class MovieServiceImpl implements MovieService {
    @Override
    public Movie getMovie() {
        return null;
    }

    @Override
    public List<Movie> getMovies() {
        return null;
    }
}
