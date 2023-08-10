package org.example.movieapi.service;

import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;

import java.util.List;
import java.util.Optional;
import java.util.Set;

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
     * update an existing movie to set its director
     * @param movieId id of movie to update (must be in the database)
     * @param directorId id of movie director (must be in the database)
     * @return optional with movie updated if movie and director have been found else optional empty
     */
    Optional<MovieDetail> updateDirector(int movieId, int directorId);

    /***
     * update an existing movie to set its actors
     * @param movieId id of movie to update (must be in the database)
     * @param actorIds id of movie actors (all must be in the database)
     * @return optional with movie updated if movie and all actors have been found else optional empty
     */
    Optional<MovieDetail> updateActors(int movieId, Set<Integer> actorIds);


    /**
     * delete movie with its id
     * @param id id of the movie to delete
     * @return true if movie found and deleted, false if not found
     * @throws org.springframework.dao.DataIntegrityViolationException if movie found and couldn't be deleted
     */
    boolean delete(int id);
}
