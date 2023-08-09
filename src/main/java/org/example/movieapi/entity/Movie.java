package org.example.movieapi.entity;

import jakarta.persistence.*;

@Entity
@Table(
        name = "movies",
        uniqueConstraints = @UniqueConstraint(columnNames = {"title", "year"})
)
public class Movie {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 300)
    private String title;
    private short year;
    private Short duration;

    @Column(length = 4000)
    private String synopsis;
}
