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
public class Genre {

    private Long id;

    private String name;

    private String image;

    private List<Genre> genres;
}
