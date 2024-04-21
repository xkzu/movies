package cl.duoc.app.movies.repository;

import cl.duoc.app.movies.model.Movie;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class MovieRepositoryTest {

    private final MovieRepository movieRepository;

    @Autowired
    MovieRepositoryTest(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Test
    public void findAll() {
        List<Movie> movies = movieRepository.findAll();
        assertNotNull(movies);
        assertFalse(movies.isEmpty());
    }

    @Test
    public void findById() {
        Movie movie = movieRepository.findById(1L).get();
        assertNotNull(movie);
        assertEquals(1L, movie.getId());
    }

    @Test
    public void save() {
        Movie movie = new Movie();
        movie.setTitle("Title");
        movie.setDirector("Director");
        movie.setYear("2024");
        movie.setGenre("Genre");
        movie.setSynopsis("Synopsis");
        Movie result = movieRepository.save(movie);
        assertNotNull(result);
        assertEquals("Title", result.getTitle());
        assertEquals("Director", result.getDirector());
        assertEquals("2024", result.getYear());
        assertEquals("Genre", result.getGenre());
        assertEquals("Synopsis", result.getSynopsis());
    }

    @Test
    public void deleteById() {
        movieRepository.deleteById(1L);
        List<Movie> movies = movieRepository.findAll();
        assertNotNull(movies);
        assertFalse(movies.isEmpty());
    }
}