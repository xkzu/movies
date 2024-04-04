package cl.duoc.app.movies.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@AllArgsConstructor
@Data
public class MovieResponse {

    private String message;

    private List<Movie> movies;
}
