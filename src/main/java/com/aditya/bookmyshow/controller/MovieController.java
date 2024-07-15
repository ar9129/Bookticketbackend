package com.aditya.bookmyshow.controller;

import com.aditya.bookmyshow.model.Movie;
import com.aditya.bookmyshow.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class MovieController {

    @Autowired
    private MovieRepository movieRepository ;

    @PostMapping("/create-movie")
    public Movie createMovie(@RequestBody Movie movie){
            return  movieRepository.save(movie) ;

    }
}
