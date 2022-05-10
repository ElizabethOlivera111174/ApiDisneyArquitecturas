package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.mapper;

import com.refactorizando.hexagonalarchitecture.domain.Movie;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.MovieEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovieEntityMapper {

    List<Movie> LtoDomain(List<MovieEntity> MovieEntity);

    Movie toDomain(MovieEntity MovieEntity);

    MovieEntity toDbo(Movie Movie);

}
