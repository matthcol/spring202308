package org.example.movieapi.service.jpa;

import jakarta.persistence.criteria.Order;
import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;
import org.example.movieapi.entity.Movie;
import org.example.movieapi.repository.MovieRepository;
import org.example.movieapi.service.MovieService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import static org.springframework.data.domain.Sort.Order.asc;
import static org.springframework.data.domain.Sort.Order.desc;

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
        return movieRepository.findAll(Sort.by(desc("year"), asc("title")))
                .stream()
                .map(movieEntity -> modelMapper.map(movieEntity, MovieSimple.class))
                .toList();
    }

    @Override
    public MovieSimple add(MovieCreate movie) {
        var movieEntity = modelMapper.map(movie, Movie.class);
        movieRepository.saveAndFlush(movieEntity);
        return modelMapper.map(movieEntity, MovieSimple.class);
    }

    @Override
    public Optional<MovieDetail> update(MovieCreate movie, int id) {
        return movieRepository.findById(id)
                .map(movieEntity -> {
                    modelMapper.map(movie, movieEntity);
                    movieRepository.flush(); // update SQL
                    return modelMapper.map(movieEntity, MovieDetail.class);
                });
    }

    @Override
    public boolean delete(int id) {
        return movieRepository.findById(id)
                .map(movieEntity -> {
                    movieRepository.delete(movieEntity);
                    movieRepository.flush();
                    return true;
                })
                .orElse(false);
    }
}
