package com.minjib.test.domain;

import java.util.List;

public interface MovieRepository {
    List<Movie> findByQuery(String query);
}
