package org.example.movieapi.service;

import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;

import java.util.List;
import java.util.Optional;

public interface MovieService {

    /**
     * get a movie by its id
     * @param id id of the movie to find
     * @return optional with movie if found else optional empty
     */
    Optional<MovieDetail> getById(int id);

    /**
     * get all movies
     * @return list of all movies
     */
    List<MovieSimple> getAll();

    // TODO: search

    /**
     * add a new Movie
     * @param movie movie to add
     * @return movie added with its id
     * @throws org.springframework.dao.DataIntegrityViolationException if movie couldn't be added
     */
    MovieSimple add(MovieCreate movie);

    /**
     * update a existing Movie with its id and a new content
     * @param movie new content
     * @param id id of the movie to update
     * @return optional with movie updated if found else optional empty
     * @throws org.springframework.dao.DataIntegrityViolationException if movie found and couldn't be updated
     */
    Optional<MovieDetail> update(MovieCreate movie, int id);

    /**
     * delete movie with its id
     * @param id id of the movie to delete
     * @return true if movie found and deleted, false if not found
     * @throws org.springframework.dao.DataIntegrityViolationException if movie found and couldn't be deleted
     */
    boolean delete(int id);
}
