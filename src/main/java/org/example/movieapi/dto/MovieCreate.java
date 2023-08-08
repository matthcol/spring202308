package org.example.movieapi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

/**
 * movie to be created in the API
 */
// customize JSON conversion
@JsonInclude(value = JsonInclude.Include.NON_EMPTY)
// lombok
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class MovieCreate {
    @NotBlank
    @Size(max=300)
    private String title;

    @NotNull
    @Min(1888)
    private Short year;

    @Min(40)
    private Short duration;

    @Size(min=20, max=4000)
    private String synopsis;

    // TODO: pg
    // TODO: genres
}
