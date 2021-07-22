package com.minjib.restApi.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;

@Builder
@Getter
@Entity
public class Movie implements Serializable {
    @Id
    private Long id;
    private String title;
    private String link;
    private String image;
    private String subtitle;
    private Date pubDate;
    private String director;
    private String actor;
    private float userRating;
}