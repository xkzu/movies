package cl.duoc.app.movies.service;

import cl.duoc.app.movies.model.Movie;
import cl.duoc.app.movies.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service //se le indica a springboot que trate esta clase como un service
public class MovieServiceImpl implements MovieService {

    MovieRepository repository;

    @Autowired //se inyectan dependencias
    public MovieServiceImpl(MovieRepository repository) {
        this.repository = repository;
    }

    public Optional<Movie> getMovie(Long id) {
        return repository.findById(id);
    }

    @Override
    public List<Movie> getMovies() {
        return repository.findAll();
    }

    @Override
    public void addMovie(Movie movie) {
        repository.save(movie);
    }

    @Override
    public void updateMovie(Movie movie) {
        repository.saveAndFlush(movie);
    }
}
