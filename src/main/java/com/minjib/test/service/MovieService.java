package com.minjib.test.service;

import com.minjib.test.domain.Movie;
import com.minjib.test.domain.MovieGroup;
import com.minjib.test.domain.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public List<Movie> search(final String query) {
        MovieGroup movieGroup = new MovieGroup(movieRepository.findByQuery(query));

        return movieGroup.getListOrderRating();
    }
}
