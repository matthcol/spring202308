package org.example.movieapi.controller.ut;

import org.example.movieapi.controller.MovieController;
import org.example.movieapi.controller.fixture.JsonProvider;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.dto.MovieSimple;
import org.example.movieapi.service.MovieService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.text.html.Option;
import java.text.MessageFormat;
import java.util.Objects;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(MovieController.class) // controller to test here
class MovieControllerTest {

    final static String BASE_URI = "/api/movies";
    final static String TEMPLATE_URI_WITH_ID = BASE_URI + "/{id}";

    // client to perform HTTP requests
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieService movieService;

    @Test
    void testGetById_found() throws Exception {
        // given/facts
        // id to find
        int id = 123;
        // movie sent by mock service
        String title = "The Batman";
        short year = 2022;
        var movie = MovieDetail.builder()
                .id(id)
                .title(title)
                .year(year)
                // TODO: with all details
                .build();
        given(movieService.getById(id))
                .willReturn(Optional.of(movie));
        // when
        mockMvc.perform(get(TEMPLATE_URI_WITH_ID, id)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // then/verify: HTTP response
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.year").value((int) year));
        // then/verify: check mock service has been called
        then(movieService)
                .should()
                .getById(id);
    }

    @Test
    void testGetById_notFound() throws Exception {
        // given/facts
        int id = 0;
        given(movieService.getById(id))
                .willReturn(Optional.empty());
        // when
        mockMvc.perform(get(TEMPLATE_URI_WITH_ID, id)
                    .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // then/verify: HTTP response
                .andExpect(status().isNotFound());
                // TODO Bonus: verify JSON Error 404 message
        // then/verify: check mock service has been called
        then(movieService)
                .should()
                .getById(id);
    }

    @ParameterizedTest
    @CsvSource(quoteCharacter = '|', value = {
            "title + year,The Batman,2022,,",
            "short title + year,Z,1969,,",
            "long title + year,|Night of the Day of the Dawn of the Son of the Bride of the Return of the Revenge of the Terror of the Attack of the Evil Mutant Hellbound Flesh Eating Crawling Alien Zombified Subhumanoid Living Dead, Part 5|,2011,,",
            "title + year + duration,The Batman,2022,176,",
            "title + year + synopsis,The Batman,2022,,|When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.|",
            "title + year + duration + synopsis,The Batman,2022,176,|When a sadistic serial killer begins murdering key political figures in Gotham, Batman is forced to investigate the city's hidden corruption and question his family's involvement.|",

    })
    // TODO:  source maximum length title
    // TODO:  source maximum length summary
    void testAdd_valid(String message, String title, Short year, Short duration, String synopsis) throws Exception {
        // given
        // JSON movie to add
        var movieJSON = JsonProvider.movieJSON(title,year,duration,synopsis);
        // DTO movie returned by mock service
        int id = 321;
        var movieDto = MovieSimple.builder()
                .id(id)
                .title(title)
                .year(year)
                .duration(duration)
                .synopsis(synopsis)
                .build();
        given(movieService.add(any()))
                .willReturn(movieDto);

        // 2 - when
        mockMvc.perform(post(BASE_URI)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJSON))
                .andDo(print())
                // 3a - then/verify
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.id").value(id))
                .andExpect(jsonPath("$.title").value(title))
                .andExpect(jsonPath("$.year").value((int) year))
                .andExpect(jsonPath("$.duration").value((int) duration))
                .andExpect(jsonPath("$.synopsis").value(synopsis));

        // 3b - verify: mock service has been called
        then(movieService)
                .should()
                .add(any());
    }

    @ParameterizedTest
    @CsvSource({
            "missing title,,2022,,",
            "empty title,'',2022,,",
            "title with one space, ,2022,,",
            "title with spaces,  ,2022,,",
            "title with tab,\t,2022,,",
            "missing year,The Batman,,,",
            "year less than minimum,The Batman,1887,,",
            "year less than minimum,The Batman,1780,,",
            "duration less than minimum,The Batman,2022,39,",
    })
        // TODO: source with title too long
        // TODO: source with synopsis too short or too long
    void testAdd_invalid(String message, String title, Short year, Short duration, String synopsis) throws Exception {
        // 1 - given
        // JSON movie to add
        var movieJSON = JsonProvider.movieJSON(title,year,duration,synopsis);

        // 2 - when
        mockMvc.perform(post(BASE_URI)
                        .accept(MediaType.APPLICATION_JSON)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(movieJSON))
                .andDo(print())
        // 3 - then/verify
                .andExpect(status().isBadRequest());
    }

}