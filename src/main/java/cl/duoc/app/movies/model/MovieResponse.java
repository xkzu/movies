package cl.duoc.app.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Optional;

@AllArgsConstructor
@Data
public class MovieResponse {

    private String message;

    private Optional<Movie> movie;
}
