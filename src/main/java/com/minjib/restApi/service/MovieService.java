package com.minjib.restApi.service;

import com.minjib.restApi.domain.Movie;
import com.minjib.restApi.domain.MovieGroup;
import com.minjib.restApi.domain.MovieRepository;
import com.minjib.restApi.service.exception.NotFoundMovieException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Cacheable(value = "cache::movie::query")
    public List<Movie> search(final String query) {
        MovieGroup movieGroup = new MovieGroup(movieRepository.findByQuery(query));

        return movieGroup.getListOrderRating();
    }

    public Movie recommendTodayMovie() {
        var query = "반지의 제왕";
        MovieGroup movieGroup = new MovieGroup(movieRepository.findByQuery(query));
        return movieGroup.getHighRatingMovie()
                .orElseThrow(NotFoundMovieException::new);
    }
}
