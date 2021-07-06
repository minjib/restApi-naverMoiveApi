package com.minjib.test.service.dto;

import com.minjib.test.domain.Movie;
import com.minjib.test.domain.MovieRepository;
import com.minjib.test.service.dto.NaverProperties;
import com.minjib.test.service.dto.ResponseMovie;
import com.minjib.test.service.exception.authogonizeFail;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieRepositoryImpl implements MovieRepository {
    private final RestTemplate restTemplate;
    private final NaverProperties naverProperties;

    public MovieRepositoryImpl(RestTemplate restTemplate, NaverProperties naverProperties) {
        this.restTemplate = restTemplate;
        this.naverProperties = naverProperties;
    }

    @Override
    public List<Movie> findByQuery(String query) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("X-Naver-Client-Id", naverProperties.getClientId());
        httpHeaders.add("X-Naver-Client-Secret", naverProperties.getClientSecret());

        String url = naverProperties.getMovieUrl() + "?query=" + query;

        try {
            return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), ResponseMovie.class)
                    .getBody()
                    .getItems()
                    .stream()
                    .map(m -> Movie.builder()
                            .title(m.getTitle())
                            .link(m.getLink())
                            .image(m.getImage())
                            .pubDate(m.getPubDate())
                            .subtitle(m.getSubtitle())
                            .actor(m.getActor())
                            .director(m.getDirector())
                            .userRating(m.getUserRating())
                            .build())
                    .collect(Collectors.toList());
        } catch (RuntimeException e) {
            throw new authogonizeFail();
        }
    }
}
