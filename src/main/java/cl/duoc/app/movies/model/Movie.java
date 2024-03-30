package cl.duoc.app.movies.model;

/*
    Las películas deben tener, al menos, los siguientes atributos: id, titulo, año,director, género y sinopsis.
 */

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie")
@AllArgsConstructor //le indicamos a loombok que nos genere el constructor con parametros
@NoArgsConstructor
@Data //le decimos a loombok que se encargue de generar los get y set
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "title")
    private  String title;

    @Column(name = "year")
    private String year;

    @Column(name = "director")
    private String director;

    @Column(name = "genre")
    private String genre;

    @Column(name = "synopsis")
    private String synopsis;

}
