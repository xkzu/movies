package cl.duoc.app.movies.model;

/*
    Las películas deben tener, al menos, los siguientes atributos: id, titulo, año,director, género y sinopsis.
 */

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor //le indicamos a loombok que nos genere el constructor con parametros
@Data //le decimos a loombok que se encargue de generar los get y set
public class Movie {

    private int id;

    private  String title;

    private String year;

    private String director;

    private String genre;

    private String Synopsis;

}
