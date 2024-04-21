package cl.duoc.app.movies.controller;

import cl.duoc.app.movies.model.Movie;
import cl.duoc.app.movies.model.MovieResponse;
import cl.duoc.app.movies.service.MovieService;
import cl.duoc.app.movies.util.MovieUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController //le indicamos a springboot que maneje esta clase como RestController
public class MovieController {

    private final MovieService movieService;

    //inyectamos la dependencia a traves del constructor por que se considera una buena practica
    @Autowired //le damos la responsabilidad a springboot para que genere la isntancia
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

/*
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
*/

    // No me gustó hateoas :c
    @GetMapping("/peliculas/{id}")
    public ResponseEntity<EntityModel<MovieResponse>> getMovie(@PathVariable Long id) {
        if (id < 1) {
            MovieResponse response = new MovieResponse("El id ingresado debe ser mayor a cero", Optional.empty());
            return ResponseEntity.badRequest().body(EntityModel.of(response));
        }
        try {
            Optional<Movie> movie = movieService.getMovie(id);
            MovieResponse response = new MovieResponse("Success", movie);
            if (movie.isPresent()) {
                EntityModel<MovieResponse> model = EntityModel.of(response,
                        WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(this.getClass()).getMovie(id)).withSelfRel());
                return ResponseEntity.ok(model);
            } else {
                return ResponseEntity.ok(EntityModel.of(response));
            }
        } catch (Exception e) {
            MovieResponse errorResponse = new MovieResponse("Error al obtener la película desde la bd: " + e.getMessage(), Optional.empty());
            return ResponseEntity.internalServerError().body(EntityModel.of(errorResponse));
        }
    }

/*
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
*/

    @GetMapping("/peliculas")
    public ResponseEntity<CollectionModel<EntityModel<Movie>>> getMovies() {
        try {
            List<Movie> movies = movieService.getMovies();
            if (movies.isEmpty()) {
                return ResponseEntity.ok(CollectionModel.empty());
            }

            List<EntityModel<Movie>> movieModels = movies.stream()
                    .map(movie -> EntityModel.of(movie,
                            linkTo(methodOn(this.getClass()).getMovie(movie.getId())).withSelfRel()))
                    .collect(Collectors.toList());

            CollectionModel<EntityModel<Movie>> collectionModel = CollectionModel.of(movieModels,
                    linkTo(methodOn(this.getClass()).getMovies()).withSelfRel());

            return ResponseEntity.ok(collectionModel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/peliculas/agregar")
    public ResponseEntity<EntityModel<Map<String, Object>>> addMovie(@RequestBody Movie movie) {
        try {
            if (MovieUtil.isEmptyOrNull(movie.getTitle()) ||
                    MovieUtil.isEmptyOrNull(movie.getYear()) ||
                    MovieUtil.isEmptyOrNull(movie.getDirector()) ||
                    MovieUtil.isEmptyOrNull(movie.getGenre()) ||
                    MovieUtil.isEmptyOrNull(movie.getSynopsis())) {
                return ResponseEntity.badRequest().body(EntityModel.of(Map.of(
                        "message", "title, year, director, genre y synopsis no pueden ser nulos ni vacios")));
            }

            Movie savedMovie = movieService.addMovie(movie);
            Map<String, Object> responseContent = new HashMap<>();
            responseContent.put("id", savedMovie.getId());
            responseContent.put("title", savedMovie.getTitle());
            responseContent.put("year", savedMovie.getYear());
            responseContent.put("director", savedMovie.getDirector());
            responseContent.put("genre", savedMovie.getGenre());
            responseContent.put("synopsis", savedMovie.getSynopsis());

            EntityModel<Map<String, Object>> entityModel = EntityModel.of(
                    Map.of("Pelicula insertada", responseContent),
                    linkTo(methodOn(this.getClass()).getMovie(savedMovie.getId())).withRel("get-this-movie"),
                    linkTo(methodOn(this.getClass()).getMovies()).withRel("all-movies"));

            return ResponseEntity.ok(entityModel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(EntityModel.of(Map.of(
                    "message", "Error al insertar pelicula: " + e.getMessage())));
        }
    }



/*
    @PostMapping("/peliculas/agregar")
    public ResponseEntity<Map<String, String>> addMovie(@RequestBody Movie movie) {
        try {
            //se valida que las propiedades del objeto no sean nulas ni vacias a traves de un metodo statico
            if (MovieUtil.isEmptyOrNull(movie.getTitle())
                    || MovieUtil.isEmptyOrNull(movie.getYear())
                    || MovieUtil.isEmptyOrNull(movie.getDirector())
                    || MovieUtil.isEmptyOrNull(movie.getGenre())
                    || MovieUtil.isEmptyOrNull(movie.getSynopsis())) {
                //en caso de que una propiedad venga null o vacia, se retorna como badrequest
                return ResponseEntity.badRequest().body(Map.of(
                        "title, year, director, genre y synopsis no pueden ser nulos ni vacios", movie.toString()));
            }
            //se inserta la pelicula en la bd
            movieService.addMovie(movie);
            //se retorna con respuesta correcta
            return ResponseEntity.ok().body(Map.of("Pelicula insertada", movie.toString()));
        } catch (Exception e) {
            //se retorna con mensaje de error y la excepcion generada
            return ResponseEntity.internalServerError().body(Map.of("Error al insertar pelicula", e.getMessage()));
        }
    }
*/

    @PutMapping("/peliculas/actualizar")
    public ResponseEntity<EntityModel<Map<String, String>>> updateMovie(@RequestBody Movie movie) {
        try {
            if (movie.getId() == null) {
                return ResponseEntity.badRequest().body(EntityModel.of(Map.of("El id ingresado no puede ser null", movie.toString())));
            }

            Optional<Movie> validateMovie = movieService.getMovie(movie.getId());
            if (validateMovie.isEmpty()) {
                return ResponseEntity.badRequest().body(EntityModel.of(Map.of("El id de la pelicula ingresada no existe en la bd", movie.toString())));
            }

            if (MovieUtil.isEmptyOrNull(movie.getTitle()) || MovieUtil.isEmptyOrNull(movie.getYear())
                    || MovieUtil.isEmptyOrNull(movie.getDirector()) || MovieUtil.isEmptyOrNull(movie.getGenre())
                    || MovieUtil.isEmptyOrNull(movie.getSynopsis())) {
                return ResponseEntity.badRequest().body(EntityModel.of(Map.of("title, year, director, genre y synopsis no pueden ser nulos ni vacios", movie.toString())));
            }

            movieService.updateMovie(movie);
            Map<String, String> response = new HashMap<>();
            response.put("Pelicula actualizada", movie.toString());
            EntityModel<Map<String, String>> entityModel = EntityModel.of(response,
                    linkTo(methodOn(this.getClass()).getMovie(movie.getId())).withRel("get-this-movie"),
                    linkTo(methodOn(this.getClass()).getMovies()).withRel("all-movies"));

            return ResponseEntity.ok(entityModel);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(EntityModel.of(Map.of("Error al actualizar la pelicula", e.getMessage())));
        }
    }

/*
    @PutMapping("/peliculas/actualizar")
    public ResponseEntity<Map<String, String>> updateMovie(@RequestBody Movie movie) {
        try {
            //se verifica si el id es null
            if (null == movie.getId()) {
                //en caso de ser null se lanza un badrequest
                return ResponseEntity.badRequest().body(Map.of(
                        "El id ingresado no puede ser null", movie.toString()));
            }
            //se consulta si el id ingresado existe en la bd
            Optional<Movie> validateMovie = movieService.getMovie(movie.getId());
            if (validateMovie.isEmpty()) {
                //en caso de nmo existir el id se lanza un badrequest
                return ResponseEntity.badRequest().body(Map.of(
                        "El id de la pelicula ingresada no existe en la bd", movie.toString()));
            }
            //se valida que las propiedades del objeto no sean nulas ni vacias a traves de un metodo statico
            if (MovieUtil.isEmptyOrNull(movie.getTitle())
                    || MovieUtil.isEmptyOrNull(movie.getYear())
                    || MovieUtil.isEmptyOrNull(movie.getDirector())
                    || MovieUtil.isEmptyOrNull(movie.getGenre())
                    || MovieUtil.isEmptyOrNull(movie.getSynopsis())) {
                //en caso de que una propiedad venga null o vacia, se retorna como badrequest
                return ResponseEntity.badRequest().body(Map.of(
                        "title, year, director, genre y synopsis no pueden ser nulos ni vacios", movie.toString()));
            }
            //se actualiza pelicula en la bd
            movieService.updateMovie(movie);
            //se retorna con respuesta correcta
            return ResponseEntity.ok().body(Map.of("Pelicula actualizada", movie.toString()));
        } catch (Exception e) {
            //se retorna con mensaje de error y la excepcion generada
            return ResponseEntity.internalServerError().body(Map.of("Error al actualizar la pelicula", e.getMessage()));
        }

    }
*/

    @DeleteMapping("/peliculas/eliminar/{id}")
    public ResponseEntity<EntityModel<Map<String, String>>> deleteMovie(@PathVariable Long id) {
        if (id == null) {
            return ResponseEntity.badRequest().body(EntityModel.of(Map.of("El id ingresado no puede ser null", "null")));
        }

        Optional<Movie> movie = movieService.getMovie(id);
        if (movie.isEmpty()) {
            return ResponseEntity.badRequest().body(EntityModel.of(Map.of("El id de la pelicula ingresada no existe en la bd", "ID: " + id)));
        }

        movieService.deleteMovie(id);
        Map<String, String> response = Map.of("Pelicula eliminada", "ID: " + id);
        EntityModel<Map<String, String>> entityModel = EntityModel.of(response,
                linkTo(methodOn(this.getClass()).getMovies()).withRel("all-movies"));

        return ResponseEntity.ok(entityModel);
    }

/*
    @DeleteMapping("peliculas/eliminar/{id}")
    public ResponseEntity<Map<String, String>> deleteMovie(@PathVariable Long id) {
        try {
            if (null == id) {
                return ResponseEntity.badRequest().body(Map.of(
                        "El id ingresado no puede ser null", "null"));
            }
            Optional<Movie> movie = movieService.getMovie(id);
            if (movie.isEmpty()) {
                //en caso de no existir el id se lanza un badrequest
                return ResponseEntity.badRequest().body(Map.of(
                        "El id de la pelicula ingresada no existe en la bd", movie.toString()));
            }
            movieService.deleteMovie(id);
            return ResponseEntity.ok().body(Map.of("Pelicula eliminada", movie.toString()));
        } catch (Exception e) {
            //se retorna con mensaje de error y la excepcion generada
            return ResponseEntity.internalServerError().body(Map.of("Error al actualizar la pelicula", e.getMessage()));
        }
    }
*/

}
