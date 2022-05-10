package com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.CharacterDTOs;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterSlimDto {
    private Long id;
    private String image;
    private String name;
}
