package com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.mapper;

import com.refactorizando.hexagonalarchitecture.domain.Movie;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.MovieDTOs.MovieDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.MovieDTOs.MovieSlimDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieMapper {
    MovieSlimDto movieToMovieSlimDto(Movie movie);

    MovieDto movieToMovieDto(Movie movie);

    Movie movieDtoToMovie(MovieDto movie);

    Movie updateMovieFromDto(MovieDto movieDto, @MappingTarget Movie movie);

    List<MovieSlimDto> moviesToMovieSlimDtos(List<Movie> movies);

    List<MovieDto> moviesToMovieDtos(List<Movie> movies);
}
