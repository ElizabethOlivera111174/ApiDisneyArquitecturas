package com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.mapper;

import com.refactorizando.hexagonalarchitecture.domain.Genre;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.GenreSlimDto;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-09T22:12:59-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class GenreMapperImpl implements GenreMapper {

    @Override
    public List<GenreSlimDto> genresToGenreSlimDtos(List<Genre> genres) {
        if ( genres == null ) {
            return null;
        }

        List<GenreSlimDto> list = new ArrayList<GenreSlimDto>( genres.size() );
        for ( Genre genre : genres ) {
            list.add( genreToGenreSlimDto( genre ) );
        }

        return list;
    }

    protected GenreSlimDto genreToGenreSlimDto(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreSlimDto genreSlimDto = new GenreSlimDto();

        genreSlimDto.setId( genre.getId() );
        genreSlimDto.setName( genre.getName() );
        genreSlimDto.setImage( genre.getImage() );

        return genreSlimDto;
    }
}
