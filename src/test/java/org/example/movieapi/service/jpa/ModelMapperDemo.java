package org.example.movieapi.service.jpa;

import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.entity.Movie;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import java.util.Set;

public class ModelMapperDemo {

    static ModelMapper modelMapper;

    @BeforeAll
    static void setup() {
        modelMapper = new ModelMapper();
    }

    @Test
    void mapMovieEntityToMovieDetailDto() {
        var movieEntity = Movie.builder()
                .id(123)
                .title("The Batman")
                .year((short) 2022)
                .duration((short) 156)
                .synopsis("A movie with Batman")
                .genres(Set.of("Action","Super Heroes"))
                .build();
        var movieDto = modelMapper.map(movieEntity, MovieDetail.class);
        System.out.println("Entity: " + movieEntity);
        System.out.println("Dto: " + movieDto);
    }
}
