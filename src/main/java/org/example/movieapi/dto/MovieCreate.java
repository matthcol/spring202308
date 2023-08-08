package org.example.movieapi.dto;

import lombok.Data;

/**
 * movie to be created in the API
 */
@Data
public class MovieCreate {
    private String title;
    private short year;
    private Short duration;
    private String synopsis;
    // TODO: pg
    // TODO: genres
}
