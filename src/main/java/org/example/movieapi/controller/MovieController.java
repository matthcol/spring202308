package org.example.movieapi.controller;

import org.example.movieapi.dto.MovieSimple;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @GetMapping
    public List<MovieSimple> getAllMovies(){
        // TODO
        return List.of(
                new MovieSimple(),
                new MovieSimple()
        );
    }

    @GetMapping("{id}")
    public MovieSimple getById(@PathVariable("id") int id){
        // TODO
        return new MovieSimple();
    }

    @PostMapping
    public MovieSimple add(@RequestBody MovieSimple movie){
        // TODO
        return movie;
    }

    @PutMapping("{id}")
    public MovieSimple update(@RequestBody MovieSimple movie) {
        // TODO
        return movie;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        // TODO
    }
}
