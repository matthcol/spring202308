package org.example.movieapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

    @GetMapping
    String coucou(){
        return "Coucou, je découvre Spring ??????????";
    }

    @GetMapping("hour")
    LocalDateTime hour(){
        return LocalDateTime.now();
    }
}
