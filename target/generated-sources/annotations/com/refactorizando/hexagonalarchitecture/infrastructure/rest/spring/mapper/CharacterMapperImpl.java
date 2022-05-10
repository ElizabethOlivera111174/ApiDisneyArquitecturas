package com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.mapper;

import com.refactorizando.hexagonalarchitecture.domain.Character;
import com.refactorizando.hexagonalarchitecture.domain.Movie;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.CharacterDTOs.CharacterDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.CharacterDTOs.CharacterSlimDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.MovieDTOs.MovieSlimDto;
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
public class CharacterMapperImpl implements CharacterMapper {

    @Override
    public CharacterDto ToDto(Character character) {
        if ( character == null ) {
            return null;
        }

        CharacterDto characterDto = new CharacterDto();

        characterDto.setId( character.getId() );
        characterDto.setName( character.getName() );
        characterDto.setImage( character.getImage() );
        characterDto.setAge( character.getAge() );
        characterDto.setWeight( character.getWeight() );
        characterDto.setHistory( character.getHistory() );
        characterDto.setMovies( movieListToMovieSlimDtoList( character.getMovies() ) );

        return characterDto;
    }

    @Override
    public Character ToCharter(CharacterDto character) {
        if ( character == null ) {
            return null;
        }

        Character character1 = new Character();

        character1.setId( character.getId() );
        character1.setName( character.getName() );
        character1.setImage( character.getImage() );
        character1.setAge( character.getAge() );
        character1.setWeight( character.getWeight() );
        character1.setHistory( character.getHistory() );
        character1.setMovies( movieSlimDtoListToMovieList( character.getMovies() ) );

        return character1;
    }

    @Override
    public List<CharacterSlimDto> ToCharactersSlimDto(List<Character> characters) {
        if ( characters == null ) {
            return null;
        }

        List<CharacterSlimDto> list = new ArrayList<CharacterSlimDto>( characters.size() );
        for ( Character character : characters ) {
            list.add( characterToCharacterSlimDto( character ) );
        }

        return list;
    }

    @Override
    public List<CharacterDto> ToCharactersDtos(List<Character> characters) {
        if ( characters == null ) {
            return null;
        }

        List<CharacterDto> list = new ArrayList<CharacterDto>( characters.size() );
        for ( Character character : characters ) {
            list.add( ToDto( character ) );
        }

        return list;
    }

    protected MovieSlimDto movieToMovieSlimDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieSlimDto movieSlimDto = new MovieSlimDto();

        movieSlimDto.setId( movie.getId() );
        movieSlimDto.setImage( movie.getImage() );
        movieSlimDto.setTitle( movie.getTitle() );
        movieSlimDto.setCreationDate( movie.getCreationDate() );

        return movieSlimDto;
    }

    protected List<MovieSlimDto> movieListToMovieSlimDtoList(List<Movie> list) {
        if ( list == null ) {
            return null;
        }

        List<MovieSlimDto> list1 = new ArrayList<MovieSlimDto>( list.size() );
        for ( Movie movie : list ) {
            list1.add( movieToMovieSlimDto( movie ) );
        }

        return list1;
    }

    protected Movie movieSlimDtoToMovie(MovieSlimDto movieSlimDto) {
        if ( movieSlimDto == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setId( movieSlimDto.getId() );
        movie.setTitle( movieSlimDto.getTitle() );
        movie.setImage( movieSlimDto.getImage() );
        movie.setCreationDate( movieSlimDto.getCreationDate() );

        return movie;
    }

    protected List<Movie> movieSlimDtoListToMovieList(List<MovieSlimDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Movie> list1 = new ArrayList<Movie>( list.size() );
        for ( MovieSlimDto movieSlimDto : list ) {
            list1.add( movieSlimDtoToMovie( movieSlimDto ) );
        }

        return list1;
    }

    protected CharacterSlimDto characterToCharacterSlimDto(Character character) {
        if ( character == null ) {
            return null;
        }

        CharacterSlimDto characterSlimDto = new CharacterSlimDto();

        characterSlimDto.setId( character.getId() );
        characterSlimDto.setImage( character.getImage() );
        characterSlimDto.setName( character.getName() );

        return characterSlimDto;
    }
}
