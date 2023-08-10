package org.example.movieapi.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

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
@ToString // with @ToString.Exclude on fields to ignore
// @ToString(of = {"id", "title", "year"})
// @ToString(exclude = {"director", "actors"})
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


    @Builder.Default
    @ElementCollection(fetch = FetchType.EAGER)
    @JoinTable(
            name = "have_genre",
            joinColumns = @JoinColumn(name = "movie_id")
    )
    @Column(name = "genre")
    private Set<String> genres = new TreeSet<>();

    // TODO: enum Pg

    @ToString.Exclude
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "director_id") // nullable=true by default
    private Person director;

    @ToString.Exclude
    @ManyToMany // LAZY by default
    @JoinTable(
            name="play",
            joinColumns = @JoinColumn(name="movie_id"),
            inverseJoinColumns = @JoinColumn(name="actor_id")
    )
    private Set<Person> actors;
}
