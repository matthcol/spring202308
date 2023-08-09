package org.example.movieapi.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "movies",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"title", "year"}
                // columnNames = {"title", "release_year"}
        )
)
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 300)
    private String title;

    //@Column(name = "release_year")
    private short year;
    private Short duration;

    @Column(length = 4000)
    private String synopsis;
}
