package org.example.movieapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * represents a movie with all details: director, actors
 */
@Data
@NoArgsConstructor
// @AllArgsConstructor
@SuperBuilder
public class MovieDetail extends MovieSimple {
    // TODO: director
    // TODO: actors
}
