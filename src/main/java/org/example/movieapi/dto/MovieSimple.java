package org.example.movieapi.dto;

import lombok.Data;

@Data
public class MovieSimple {
    private String title;
    private short year;
    private Short duration;
    private String synopsis;
}
