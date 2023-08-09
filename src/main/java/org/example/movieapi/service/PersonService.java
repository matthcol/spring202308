package org.example.movieapi.service;

import org.example.movieapi.dto.*;

import java.util.List;
import java.util.Optional;

public interface PersonService {
    /**
     * get a movie by its id
     * @param id id of the movie to find
     * @return optional with movie if found else optional empty
     */
    Optional<PersonSimple> getById(int id);

    /**
     * get all movies
     * @return list of all persons
     */
    List<PersonSimple> getAll();

    // TODO: search

    /**
     * add a new person
     * @param person movie to add
     * @return person added with its id
     * @throws org.springframework.dao.DataAccessException if person couldn't be added
     */
    PersonSimple add(PersonCreate person);

    /**
     * update a existing person with its id and a new content
     * @param person new content
     * @param id id of the person to update
     * @return optional with person updated if found else optional empty
     * @throws org.springframework.dao.DataAccessException if person found and couldn't be updated
     */
    Optional<PersonSimple> update(PersonCreate person, int id);

    /**
     * delete movie with its id
     * @param id id of the person to delete
     * @return true if person found and deleted, false if not found
     * @throws org.springframework.dao.DataAccessException if person found and couldn't be deleted
     */
    boolean delete(int id);
}
