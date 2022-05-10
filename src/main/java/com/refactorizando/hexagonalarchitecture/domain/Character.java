package com.refactorizando.hexagonalarchitecture.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Character {

    private Long id;

    private String name;

    private String image;

    private Integer age;

    private Float weight;

    private String history;

    private List<Movie> movies;
}
