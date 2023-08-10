package org.example.movieapi.dto;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

/**
 * represents a movie with all details: director, actors
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString(callSuper = true)
@SuperBuilder
public class MovieDetail extends MovieSimple {
    private PersonSimple director;
    private Set<PersonSimple> actors;
}
