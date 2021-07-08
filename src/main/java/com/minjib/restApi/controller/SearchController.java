package com.minjib.restApi.controller;

import com.minjib.restApi.domain.Movie;
import com.minjib.restApi.service.MovieService;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/search")
public class SearchController {

    private final MovieService movieService;
    private Map<String, List<Movie>> movieCache;

    public SearchController(MovieService movieService) {
        this.movieService = movieService;
        movieCache = new HashMap<>();
    }

    @GetMapping("/movies")
    public List<Movie> getMoviesByQuery(@RequestParam(name = "q") String query) {
        if(!movieCache.containsKey(query))
            movieCache.put(query, movieService.search(query));
        return movieCache.get(query);
    }

    @PostMapping("/update")
    public void updateCache() {
        for(String key : movieCache.keySet()) {
            movieCache.put(key, movieService.search(key));
        }
    }
}