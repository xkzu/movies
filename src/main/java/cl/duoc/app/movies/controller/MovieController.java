package cl.duoc.app.movies.controller;

import cl.duoc.app.movies.model.Movie;
import cl.duoc.app.movies.model.MovieResponse;
import cl.duoc.app.movies.model.MoviesResponse;
import cl.duoc.app.movies.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
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
    public ResponseEntity<Optional<MovieResponse>> getMovie(@PathVariable Long id) {
        //se evalua si el id ingresado es menor a 1
        if (id  < 1) {
            //en caso de ser menor a 1 retorna un badrequest indicando que el numero debe ser mayor a cero
            return ResponseEntity.badRequest().body(Optional.of(
                    new MovieResponse("El id ingresado debe ser mayor a cero", null)));
        }
        try {
            //se obtiene la pelicula desde la bd
            Optional<Movie> movie = movieService.getMovie(id);
            //se evalua si movie contiene algo
            if (movie.isPresent()) {
                //en caso de que movie tenga data retorna con respuesta 200 y la pelicula solicitada
                return ResponseEntity.ok(Optional.of(
                        new MovieResponse("Success", movie)));
            }
            //en caso de que no se encuentr data se retorna indicando que no hay datos en la bd
            return ResponseEntity.ok(Optional.of(
                    new MovieResponse("No se encontró la pelicula solicitada", movie)));
        } catch (Exception e) {
            //en caso de una excepcion se retorna indicando error 500 con un mensaje descriptivo
            return ResponseEntity.internalServerError().body(Optional.of(
                    new MovieResponse("Error al obntener la pelicula desde la bd: " + e.getMessage(), null)));
        }
    }

    @GetMapping("/peliculas")
    public ResponseEntity<List<MoviesResponse>> getMovies() {
        try {
            //se obtienen las peliculas desde la bd
            List<Movie> movies = movieService.getMovies();
            //se evalua si la lista trae informacion
            if (null != movies && !movies.isEmpty()) {
                //se retorna la informacion correcta
                return ResponseEntity.ok(Collections.singletonList(
                        new MoviesResponse("Success", movies)));
            }
            // se retorna indicando que no se encontraron peliculas
            return ResponseEntity.ofNullable(Collections.singletonList(
                    new MoviesResponse("No se encontraron peliculas en la bd", movies)));
        } catch (Exception e) {
            // en caso de excepcion se retorna indicando que no se pudo obtener las peliculas con el error asociado
            return (ResponseEntity.internalServerError().body(Collections.singletonList(
                    new MoviesResponse("Error al obntener pelicuas desde la bd: " + e.getMessage(), null))));
        }
    }

}
