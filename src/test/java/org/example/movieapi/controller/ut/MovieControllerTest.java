package org.example.movieapi.controller.ut;

import org.example.movieapi.controller.MovieController;
import org.example.movieapi.service.MovieService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(MovieController.class) // controller to test here
class MovieControllerTest {

    // client to perform HTTP requests
    @Autowired
    MockMvc mockMvc;

    @MockBean
    MovieService movieService;

    @Test
    void testGetById_found(){

    }

    @Test
    void testGetById_notFound(){

    }

}