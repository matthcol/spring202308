package org.example.movieapi.controller;

import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.error.HttpExceptions;
import org.example.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieCreate> getAllMovies(){
        // TODO
        return List.of(
                new MovieCreate(),
                new MovieCreate()
        );
    }

    @GetMapping("{id}")
    public MovieDetail getById(@PathVariable("id") int id){
        //        var optMovie = movieService.getById(id);
        //        if (optMovie.isPresent()) {
        //            return optMovie.get();
        //        } else {
        //            throw HttpExceptions.notFoundException("Movie", id);
        //        }
        return movieService.getById(id)
                .orElseThrow(() -> HttpExceptions.notFoundException("Movie", id));
    }

    @PostMapping
    public MovieCreate add(@RequestBody MovieCreate movie){
        // TODO
        return movie;
    }

    @PutMapping("{id}")
    public MovieCreate update(@RequestBody MovieCreate movie) {
        // TODO
        return movie;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        // TODO
    }
}
