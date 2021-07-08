package com.minjib.restApi.controller;

import com.minjib.restApi.domain.Movie;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class SearchControllerTest {

    private Map<String, List<Movie>> movieCache;
}