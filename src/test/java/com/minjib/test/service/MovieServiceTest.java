package com.minjib.test.service;

import com.minjib.restApi.domain.Movie;
import com.minjib.restApi.domain.MovieRepository;
import com.minjib.restApi.service.MovieService;
import com.minjib.restApi.service.exception.NotFoundMovieException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {
    @Mock
    MovieRepository movieRepository;

    @DisplayName("평점 순으로 정렬되는지")
    @Test
    void shouldSortedInOrderOfGrade() {
        var query = "테스트_쿼리";
        var expectedTopRankingMovieTitle = "영화3";
        given(movieRepository.findByQuery(anyString())).willReturn(getStubMovies());
        MovieService movieService = new MovieService(movieRepository);

        var actualMovies = movieService.search(query);

        assertEquals(expectedTopRankingMovieTitle, actualMovies.stream().findFirst().get().getTitle());
    }

    List<Movie> getStubMovies() {
        return Arrays.asList(
                Movie.builder().title("영화1").link("http://test").userRating(9.3f).build(),
                Movie.builder().title("영화2").link("http://test").userRating(8.7f).build(),
                Movie.builder().title("영화3").link("http://test").userRating(9.7f).build()

        );
    }

    @DisplayName("평점 0을 제외하는지")
    @Test
    void shouldExceptRatingZero() {
        var query = "테스트_쿼리";
        var ratingZeroMovieTitle = "영화2";
        given(movieRepository.findByQuery(anyString())).willReturn(getStubMovies2());
        MovieService movieService = new MovieService(movieRepository);

        var actualMovies = movieService.search(query);

        assertFalse(actualMovies.contains(ratingZeroMovieTitle));
    }

    List<Movie> getStubMovies2() {
        return Arrays.asList(
                Movie.builder().title("영화1").link("http://test").userRating(9.3f).build(),
                Movie.builder().title("영화2").link("http://test").userRating(0.0f).build(),
                Movie.builder().title("영화3").link("http://test").userRating(9.7f).build()

        );
    }

    @DisplayName("추천 영화 없을시 예외 발생 검증")
    @Test
    void shouldThrowExceptionWhenNoneRecommend() {

        given(movieRepository.findByQuery(anyString())).willReturn(Collections.emptyList());
        MovieService movieService = new MovieService(movieRepository);

        assertThrows(NotFoundMovieException.class, () -> {
            var movie = movieService.recommendTodayMovie();
        });
    }
}