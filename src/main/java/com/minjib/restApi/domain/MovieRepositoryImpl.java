package com.minjib.restApi.domain;

import com.minjib.restApi.domain.Movie;
import com.minjib.restApi.domain.MovieRepository;
import com.minjib.restApi.service.dto.MovieResponseDto;
import com.minjib.restApi.service.dto.NaverProperties;
import com.minjib.restApi.service.exception.AuthenticationFailException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

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
            return restTemplate.exchange(url, HttpMethod.GET, new HttpEntity(httpHeaders), MovieResponseDto.class)
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
            throw new AuthenticationFailException();
        }
    }
}
