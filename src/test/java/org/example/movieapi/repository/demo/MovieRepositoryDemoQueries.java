package org.example.movieapi.repository.demo;

import jakarta.validation.constraints.Min;
import org.checkerframework.framework.qual.PreconditionAnnotation;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
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

    @ParameterizedTest
    @ValueSource(strings={"star", "Star", "STAR"})
    void findByTitleContaing(String partialTitle){
        var movies = movieRepository.findByTitleContaining(partialTitle);
        System.out.println("Movie count: " + movies.size());
        movies.stream()
                .limit(50)
                .forEach(System.out::println);
    }

    @ParameterizedTest
    @ValueSource(strings={"star", "Star", "STAR"})
    void findByTitleContaingIgnoreCase(String partialTitle){
        var movies = movieRepository.findByTitleContainingIgnoreCase(partialTitle);
        System.out.println("Movie count: " + movies.size());
        movies.stream()
                .limit(50)
                .forEach(System.out::println);
    }

    @ParameterizedTest
    @CsvSource({
            "1980,1989",
            "2018,2019",
            "1989,1980"
    })
    void findByYearBetweenOrderByYear(short year1, short year2){
        var movies = movieRepository.findByYearBetweenOrderByYearAscTitleAsc(year1, year2);
        System.out.println("Movie count: " + movies.size());
        movies.stream()
                .limit(50)
                .forEach(System.out::println);
    }

    @Test
    void findMovieWithDirectorAndActors(){
        int id = 76759;
        movieRepository.findById(id)
                .ifPresent(movie -> {
                    System.out.println(movie);
                    System.out.println("\t- genres: " + movie.getGenres());
                    System.out.println("\t- director: " + movie.getDirector());
                    System.out.println("\t- actors: ");
                    movie.getActors()
                            .forEach(actor -> System.out.println("\t\t* " + actor));
                });
    }
}
