package org.example.movieapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "movies",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"title", "year"}
                // columnNames = {"title", "release_year"}
        )
)
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@ToString(of = {"id", "title", "year"})
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

    @Transient // ignore by JPA
    @Builder.Default
    private Set<String> genres = new HashSet<>();

    // TODO: enum Pg
    // TODO: genres
    // TODO: director
    // TODO: actors
}
