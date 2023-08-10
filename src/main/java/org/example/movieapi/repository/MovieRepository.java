package org.example.movieapi.repository;

import org.example.movieapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.stream.Stream;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    // Spring JPA vocabulary for method name generating SQL automatically
    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation
    List<Movie> findByTitle(String title);

    List<Movie> findByTitleContaining(String title);

    List<Movie> findByTitleContainingIgnoreCase(String title);

    List<Movie> findByYearBetweenOrderByYearAscTitleAsc(short year1, short year2);

    // Exo: by partial title and minimum duration + param Sort

    @Query("SELECT m FROM Movie m JOIN m.director d LEFT JOIN FETCH m.genres WHERE d.name LIKE :namePattern")
    Stream<Movie> findByDirector(String namePattern);


    // TODO: filmography, stats

}
