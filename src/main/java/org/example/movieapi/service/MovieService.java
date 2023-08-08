package org.example.movieapi.service;

import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;

import java.util.Optional;

public interface MovieService {

    Optional<MovieDetail> getById(int id);
}
