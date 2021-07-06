package com.minjib.test.domain;

import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;

@Builder
@Getter
public class Movie implements Serializable {

    private String title;
    private String link;
    private String image;
    private String subtitle;
    private int pubDate;
    private String director;
    private String actor;
    private float userRating;
}