package org.example.movieapi.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;
import org.example.movieapi.error.HttpExceptions;
import org.example.movieapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.text.MessageFormat;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    private MovieService movieService;

    @GetMapping
    public List<MovieSimple> getAllMovies(){
       return movieService.getAll();
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
    @ResponseStatus(HttpStatus.CREATED)
    public MovieSimple add(@Valid @RequestBody MovieCreate movie){
        // TODO: deal with exception DataAccessException
        return movieService.add(movie);
    }

    @PutMapping("{id}")
    public MovieDetail update(@PathVariable("id") int id, @Valid @RequestBody MovieCreate movie) {
        // TODO: deal with exception DataAccessException
        return movieService.update(movie, id)
                .orElseThrow(() -> HttpExceptions.notFoundException("Movie", id));
    }

    @PatchMapping("{mid}/director/{did}")
    public MovieDetail updateDirector(
            @PathVariable("mid") int movieId,
            @PathVariable("did") int directorId)
    {
        return movieService.updateDirector(movieId, directorId)
                .orElseThrow(() -> HttpExceptions.notFoundException("Movie", movieId, "Person", directorId));
    }

    @PatchMapping("{mid}/actors")
    public MovieDetail updateActors(
            @PathVariable("mid") int movieId,
            @RequestBody Set<@NotNull Integer> actorIds)
    {
        return movieService.updateActors(movieId, actorIds)
                .orElseThrow(() -> HttpExceptions.notFoundException("Movie", movieId, "Person", actorIds));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id){
        if (!movieService.delete(id)) throw HttpExceptions.notFoundException("Movie", id);
    }
}
