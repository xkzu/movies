package cl.duoc.app.movies.service;

import cl.duoc.app.movies.model.Movie;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service //se le indica a springboot que trate esta clase como un service
public class MovieServiceImpl implements MovieService {

    List<Movie> movies = new ArrayList<>();

    public MovieServiceImpl() {

        //por temas de mantenibilidad no se debe repetir más de 5 veces un texto
        final String SCIENCE_FICTION = "Science fiction"; //se crea constante para no imcumplir regla de sonar

        //Se inicializan los datos de las peliculas
        movies.add(new Movie(
                1,
                "Interstellar",
                "2014",
                "Christopher Nolan",
                //se usa la constante creada para establecer el género de la pelicula
                SCIENCE_FICTION,
                "A group of scientists and explorers, led by Cooper, embark on a space journey to find a " +
                        "place with the necessary conditions to replace Earth and start a new life there. Earth is " +
                        "coming to an end and this group needs to find a planet beyond our galaxy that will guarantee" +
                        " the future of the human race."));

        movies.add(new Movie(
                2,
                "Inception",
                "2010",
                "Christopher Nolan",
                SCIENCE_FICTION,
                "Dom Cobb (Leonardo DiCaprio) is a thief with an uncanny ability to enter people's " +
                        "dreams and steal the secrets of their subconscious. His ability has made him a hot " +
                        "commodity in the world of corporate espionage, but it has come at a great cost to " +
                        "the people he loves. Cobb has a chance to be forgiven when he is given an impossible " +
                        "task: plant an idea in a person's mind. If he succeeds, it will be the perfect crime, " +
                        "but an enemy anticipates his moves."));

        movies.add(new Movie(
                3,
                "Arrival",
                "2016",
                "Denis Villeneuve",
                SCIENCE_FICTION,
                "Twelve alien spacecraft begin arriving on our planet. The military high command asks" +
                        " for the help of an expert linguist to try to find out if the aliens come in peace" +
                        " or pose a threat. Little by little the woman will try to learn to communicate with" +
                        " the strange invaders, who have their own language, to find out the true and mysterious" +
                        " reason for the extraterrestrial visit."));

        movies.add(new Movie(
                4,
                "The Martian",
                "2015",
                "Ridley Scott",
                SCIENCE_FICTION,
                "A space explorer is trapped on Mars after being abandoned by his crew members, " +
                        "who thought he had died in a storm. With almost no resources and only his wits, " +
                        "he will try to survive while NASA, on the one hand, and his crew members, " +
                        "on their own, try to rescue him."));

        movies.add(new Movie(
                5,
                "The Matrix",
                "1999",
                "Lana Wachowski, Lilly Wachowski",
                "Science fiction",
                "Computer programmer Thomas Anderson, better known in the hacker world as Neo, " +
                        "is targeted by the dreaded Agent Smith. Two other hackers, Trinity and Morpheus, " +
                        "contact Neo to help him escape. The Matrix possesses you. Follow the white rabbit."));
    }

    @Override
    public Movie getMovie(int id) {
        for (Movie movie: movies) {
            if (movie.getId() == id) {
                return movie;
            }
        }
        return null;
    }

    @Override
    public List<Movie> getMovies() {
        return movies;
    }
}
