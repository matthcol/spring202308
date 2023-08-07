package org.example.movieapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @GetMapping
    String coucou(){
        return "Coucou, je découvre Spring";
    }
}
