package org.example.movieapi.service.jpa;

import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;
import org.example.movieapi.repository.MovieRepository;
import org.example.movieapi.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceJpa implements MovieService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private MovieRepository movieRepository;

    @Override
    public Optional<MovieDetail> getById(int id) {
        return movieRepository.findById(id)
                .map(movieEntity -> modelMapper.map(movieEntity, MovieDetail.class));
    }

    @Override
    public List<MovieSimple> getAll() {
        return null;
    }

    @Override
    public MovieSimple add(MovieCreate movie) {
        return null;
    }

    @Override
    public Optional<MovieDetail> update(MovieCreate movie, int id) {
        return Optional.empty();
    }

    @Override
    public boolean delete(int id) {
        return false;
    }
}
