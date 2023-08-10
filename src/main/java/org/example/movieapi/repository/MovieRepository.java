package org.example.movieapi.repository;

import org.example.movieapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    // Spring JPA vocabulary for method name generating SQL automatically
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<Movie> findByTitle(String title);

    List<Movie> findByTitleContaining(String title);

    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByYearBetweenOrderByYearAscTitleAsc(short year1, short year2);

    // Exo: by partial title and minimum duration + param Sort

    // TODO: methods with query JPQL

    // TODO: filmography, stats

}
