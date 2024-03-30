package cl.duoc.app.movies.controller;

import cl.duoc.app.movies.model.Movie;
import cl.duoc.app.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController //le indicamos a springboot que maneje esta clase como RestController
public class MovieController {

    private final MovieService movieService;

    //inyectamos la dependencia a traves del constructor por que se considera una buena practica
    @Autowired //le damos la responsabilidad a springboot para que genere la isntancia
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/peliculas/{id}")
    public ResponseEntity<Optional<Movie>> getMovie(@PathVariable Long id) {
        return ResponseEntity.ok(movieService.getMovie(id));
    }

    @GetMapping("/peliculas")
    public ResponseEntity<List<Movie>> getMovies() {
        return ResponseEntity.ok(movieService.getMovies());
    }

}
