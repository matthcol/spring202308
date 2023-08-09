package org.example.movieapi.repository;

import org.example.movieapi.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Integer> {

    List<Movie> findByTitle(String title);

    List<Movie> findByTitleContaining(String title);

    List<Movie> findByTitleContainingIgnoringCase(String title);

    List<Movie> findByYearBetweenOrderByYear(short year1, short year2);

}
