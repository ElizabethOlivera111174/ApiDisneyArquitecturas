package com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.mapper;

import com.refactorizando.hexagonalarchitecture.domain.Genre;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.GenreSlimDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface GenreMapper {
    List<GenreSlimDto> genresToGenreSlimDtos(List<Genre> genres);
}
