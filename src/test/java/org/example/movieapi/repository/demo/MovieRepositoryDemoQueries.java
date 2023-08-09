package org.example.movieapi.repository.demo;

import jakarta.validation.constraints.Min;
import org.checkerframework.framework.qual.PreconditionAnnotation;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.test.context.ActiveProfiles;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace.*;
import static org.springframework.data.domain.ExampleMatcher.StringMatcher.CONTAINING;
import static org.springframework.data.domain.Sort.*;

// Spring Data Jpa Doc:
// https://docs.spring.io/spring-data/jpa/docs/current/reference/html/

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

    static Stream<Sort> sortCriteriaSource(){
        return Stream.of(
                by("year", "title"),
                by("title", "year"),
                by(Order.desc("year"), Order.asc("title"))
        );
    }

    @ParameterizedTest
    @MethodSource("sortCriteriaSource")
    void findAllSort(Sort sortCriteria) {
        var movies = movieRepository.findAll(sortCriteria);
        System.out.println("Movie count: " + movies.size());
        movies.stream()
                .limit(50)
                .forEach(System.out::println);
    }

    static Stream<Example<Movie>> movieExampleSource(){
        var movieExTitleYear = Movie.builder()
                .title("The Man Who Knew Too Much")
                .year((short) 1956)
                .build();
        var movieExTitle = Movie.builder()
                .title("The Man Who Knew Too Much")
                .build();
        var movieExPartialTitle = Movie.builder()
                .title("Star")
                .build();
        return Stream.of(
                Example.of(movieExTitleYear),
                Example.of(movieExTitle), // !! default year value is 0
                Example.of(movieExTitle,
                        ExampleMatcher.matching()
                                .withIgnorePaths("year")
                ),
                Example.of(movieExPartialTitle,
                        ExampleMatcher.matching()
                                .withIgnorePaths("year")
                                .withStringMatcher(CONTAINING)
                )
        );
    }

    // https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#query-by-example
    @ParameterizedTest
    @MethodSource("movieExampleSource")
    void findAllExample(Example<Movie> movieExample){
        var movies = movieRepository.findAll(movieExample);
        System.out.println("Movie count: " + movies.size());
        movies.stream()
                .limit(50)
                .forEach(System.out::println);
    }
}
