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
        var optMovieEntity = movieRepository.findById(id);
        var movieEntity = optMovieEntity.get();
        var movieDto = MovieDetail.builder()
                .title(movieEntity.getTitle())
                .year(movieEntity.getYear())
                .build();
        return Optional.of(movieDto);
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
