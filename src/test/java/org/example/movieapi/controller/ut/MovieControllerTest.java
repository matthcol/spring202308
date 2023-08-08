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

import javax.swing.text.html.Option;
import java.text.MessageFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(MovieController.class) // controller to test here
class MovieControllerTest {

    final static String BASE_URI = "/api/movies";

    // client to perform HTTP requests
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieService movieService;

    @Test
    void testGetById_found() throws Exception {
        // given/facts
        int id = 123;
        String title = "The Batman";
        short year = 2022;
        var movie = MovieDetail.builder()
                .title(title)
                .year(year)
                // TODO: with all details
                .build();
        given(movieService.getById(id))
                .willReturn(Optional.of(movie));
        String uri = MessageFormat.format(
                "{0}/{1}",
                BASE_URI,
                id
        );
        // when
        mockMvc.perform(get(uri)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                // then/verify: HTTP response
                .andExpect(status().isOk());
        // TODO: check JSON content
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
        String uri = MessageFormat.format(
                "{0}/{1}",
                BASE_URI,
                id
        );
        // when
        mockMvc.perform(get(uri)
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