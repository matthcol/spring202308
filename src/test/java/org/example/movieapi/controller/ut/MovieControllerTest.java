package org.example.movieapi.controller.ut;

import org.example.movieapi.controller.MovieController;
import org.example.movieapi.service.MovieService;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.text.MessageFormat;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MovieController.class) // controller to test here
class MovieControllerTest {

    final static String BASE_URI = "/api/movies";

    // client to perform HTTP requests
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieService movieService;

    @Test
    void testGetById_found(){

    }

    @Test
    void testGetById_notFound() throws Exception {
        // given/facts
        int id = 0;
        BDDMockito.given(movieService.getById(id))
                .willReturn(Optional.empty());
        // when
        String uri = MessageFormat.format(
                "{0}/{1}",
                BASE_URI,
                id
        );
        mockMvc.perform(MockMvcRequestBuilders.get(uri)
                .accept(MediaType.APPLICATION_JSON)
        );
    }

}