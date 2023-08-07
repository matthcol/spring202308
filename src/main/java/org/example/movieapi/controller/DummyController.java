package org.example.movieapi.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.text.MessageFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RestController
@RequestMapping("/api/dummy")
public class DummyController {

    /**
     * route: GET /api/dummy
     * @return welcome message
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String coucou(){
        return "Coucou, je découvre Spring ??????????";
    }

    /**
     * route: GET /api/dummy/hour
     * @return current date and time
     */
    @GetMapping("hour")
    public LocalDateTime hour(){
        return LocalDateTime.now();
    }

    /**
     * route: POST /api/dummy
     * @param message provided with query param 'm'
     * @return same message with prefix 'say:'
     */
    @PostMapping
    public String mirrorQueryParam(
            @RequestParam("m") String message,
            @RequestParam(name = "n", required = false, defaultValue = "1") int count,
            @RequestParam(name = "d", required = false) LocalDate date
    ){
        var messageRepeated = IntStream.range(0, count)
                .mapToObj(i -> message)
                .collect(Collectors.joining(", "));
        return MessageFormat.format("say: {0} ({1})",
                messageRepeated,
                date
        );
    }
}
