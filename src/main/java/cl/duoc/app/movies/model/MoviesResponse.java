package cl.duoc.app.movies.model;

import lombok.Data;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.RepresentationModel;

import java.util.ArrayList;
import java.util.List;

@Data
public class MoviesResponse extends RepresentationModel<MoviesResponse> {

    private String message;

    private List<Movie> movies;

    private List<Link> movieDetailsLinks = new ArrayList<>();

    public MoviesResponse(String message, List<Movie> movies) {
        this.message = message;
        this.movies = movies;
    }

    public void addMovieDetailLink(Link link) {
        movieDetailsLinks.add(link);
    }
}
