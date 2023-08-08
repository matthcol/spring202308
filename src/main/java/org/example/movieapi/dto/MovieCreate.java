package org.example.movieapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * movie to be created in the API
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MovieCreate {
    private String title;
    private short year;
    private Short duration;
    private String synopsis;
    // TODO: pg
    // TODO: genres
}
