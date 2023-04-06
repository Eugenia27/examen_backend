package com.dh.apimovie.controller;

import com.dh.apimovie.event.NewMovieEventProducer;
import com.dh.apimovie.model.Movie;
import com.dh.apimovie.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieController {

    @Autowired
    public MovieService movieService;

    @Value("${server.port}")
    private String port;

    @GetMapping("/{genre}")
    public ResponseEntity<List<Movie>> getMovieByGenre(@PathVariable String genre, HttpServletResponse response){
        response.addHeader("port", port);
        System.out.println("LOAD BALANCER SELECTED INSTANCE. PORT: " + port);
        return ResponseEntity.ok().body(movieService.findByGenre(genre));
    }

    @PostMapping("/save")
    public ResponseEntity<Movie> saveMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok().body(movieService.save(movie));
    }
}
