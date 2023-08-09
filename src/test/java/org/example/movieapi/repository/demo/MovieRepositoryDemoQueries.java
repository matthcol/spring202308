package org.example.movieapi.repository.demo;

import org.example.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = NONE)
@ActiveProfiles("queries")
public class MovieRepositoryDemoQueries {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    void findAll() {
        movieRepository.findAll()
                .stream()
                .limit(50)
                .forEach(System.out::println);
    }
}
