package com.minjib.restApi.domain;

import java.util.List;

public interface MovieRepository {
    List<Movie> findByQuery(String query);
}
