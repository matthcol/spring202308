package org.example.movieapi.service.fake;

import org.example.movieapi.dto.MovieCreate;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;
import org.example.movieapi.service.MovieService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // for DI discovery
public class MovieServicePrototype implements MovieService {
    @Override
    public Optional<MovieDetail> getById(int id) {
        // return Optional.empty();
        return Optional.of(MovieDetail.builder()
                .id(id)
                .title("Fake Movie")
                .year((short) 2023)
                .build()
        );
    }

    @Override
    public List<MovieSimple> getAll() {
        return List.of();
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
