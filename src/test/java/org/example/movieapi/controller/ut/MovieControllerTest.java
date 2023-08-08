package org.example.movieapi.controller.ut;

import org.example.movieapi.controller.MovieController;
import org.example.movieapi.dto.MovieDetail;
import org.example.movieapi.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import javax.swing.text.html.Option;
import java.text.MessageFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

}