package org.example.movieapi.repository.demo;

import org.example.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

@DataJpaTest
// keep DB from config instead of replacing it with default H2
@AutoConfigureTestDatabase(replace = NONE)
// use application-dbh2.properties to replace properties from main application.properties
@ActiveProfiles("dbh2")
class MovieRepositoryDemo {

    @Autowired
    MovieRepository movieRepository;

    @Test
    void findAll(){
        var movies = movieRepository.findAll();
        System.out.println(movies);
    }
}