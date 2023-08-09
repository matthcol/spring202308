package org.example.movieapi.repository.demo;

import org.example.movieapi.entity.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

@DataJpaTest
// keep DB from config instead of replacing it with default H2
@AutoConfigureTestDatabase(replace = NONE)
// use application-dbh2.properties to replace properties from main application.properties
@ActiveProfiles("dbh2")
// do not rollback transaction after each test (default behaviour)
@Rollback(value = false)
@TestMethodOrder(OrderAnnotation.class)
class MovieRepositoryDemo {

    @Autowired
    MovieRepository movieRepository;

    @Test
    @Order(1)
    void saveMovie(){
        var movie = Movie.builder()
                .title("The Batman")
                .year((short) 2022)
                .build();
        movieRepository.saveAndFlush(movie);
    }

    @Test
    @Order(2)
    void saveAllMovies(){
        var movies = List.of(
                Movie.builder()
                    .title("Oppenheimer")
                    .year((short) 2023)
                    .build(),
                Movie.builder()
                        .title("Barbie")
                        .year((short) 2023)
                        .build()
                );
        movieRepository.saveAllAndFlush(movies);
    }

    @Test
    @Order(3)
    void findAll(){
        var movies = movieRepository.findAll();
        System.out.println(movies);
    }

    @ParameterizedTest
    @ValueSource(ints={1,2,3,4})
    @Order(4)
    void findById(int id) {
        var optMovie = movieRepository.findById(id);
        System.out.println(optMovie);
    }
}