package com.refactorizando.hexagonalarchitecture.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.xml.stream.events.Characters;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Movie {

    private Long id;

    private String title;

    private String image;

    private LocalDateTime creationDate;

    private Integer rating;

    private List<Character> character;

    private List<Genre> genres;

}
