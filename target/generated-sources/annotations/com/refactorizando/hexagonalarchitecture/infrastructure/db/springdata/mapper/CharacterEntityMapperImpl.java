package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.mapper;

import com.refactorizando.hexagonalarchitecture.domain.Character;
import com.refactorizando.hexagonalarchitecture.domain.Genre;
import com.refactorizando.hexagonalarchitecture.domain.Movie;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.CharacterEntity;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.GenreEntity;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.MovieEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2022-05-09T22:12:59-0300",
    comments = "version: 1.2.0.Final, compiler: javac, environment: Java 11.0.14 (Oracle Corporation)"
)
@Component
public class CharacterEntityMapperImpl implements CharacterEntityMapper {

    @Override
    public List<Character> LtoDomain(List<CharacterEntity> charactersEntity) {
        if ( charactersEntity == null ) {
            return null;
        }

        List<Character> list = new ArrayList<Character>( charactersEntity.size() );
        for ( CharacterEntity characterEntity : charactersEntity ) {
            list.add( toDomain( characterEntity ) );
        }

        return list;
    }

    @Override
    public Character toDomain(CharacterEntity characterEntity) {
        if ( characterEntity == null ) {
            return null;
        }

        Character character = new Character();

        character.setId( characterEntity.getId() );
        character.setName( characterEntity.getName() );
        character.setImage( characterEntity.getImage() );
        character.setAge( characterEntity.getAge() );
        character.setWeight( characterEntity.getWeight() );
        character.setHistory( characterEntity.getHistory() );
        character.setMovies( movieEntitySetToMovieList( characterEntity.getMovies() ) );

        return character;
    }

    @Override
    public CharacterEntity toDbo(Character character) {
        if ( character == null ) {
            return null;
        }

        CharacterEntity characterEntity = new CharacterEntity();

        characterEntity.setId( character.getId() );
        characterEntity.setName( character.getName() );
        characterEntity.setImage( character.getImage() );
        characterEntity.setAge( character.getAge() );
        characterEntity.setWeight( character.getWeight() );
        characterEntity.setHistory( character.getHistory() );
        characterEntity.setMovies( movieListToMovieEntitySet( character.getMovies() ) );

        return characterEntity;
    }

    protected Genre genreEntityToGenre(GenreEntity genreEntity) {
        if ( genreEntity == null ) {
            return null;
        }

        Genre genre = new Genre();

        genre.setId( genreEntity.getId() );
        genre.setName( genreEntity.getName() );
        genre.setImage( genreEntity.getImage() );

        return genre;
    }

    protected List<Genre> genreEntitySetToGenreList(Set<GenreEntity> set) {
        if ( set == null ) {
            return null;
        }

        List<Genre> list = new ArrayList<Genre>( set.size() );
        for ( GenreEntity genreEntity : set ) {
            list.add( genreEntityToGenre( genreEntity ) );
        }

        return list;
    }

    protected Movie movieEntityToMovie(MovieEntity movieEntity) {
        if ( movieEntity == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setId( movieEntity.getId() );
        movie.setTitle( movieEntity.getTitle() );
        movie.setImage( movieEntity.getImage() );
        movie.setCreationDate( movieEntity.getCreationDate() );
        movie.setRating( movieEntity.getRating() );
        movie.setGenres( genreEntitySetToGenreList( movieEntity.getGenres() ) );

        return movie;
    }

    protected List<Movie> movieEntitySetToMovieList(Set<MovieEntity> set) {
        if ( set == null ) {
            return null;
        }

        List<Movie> list = new ArrayList<Movie>( set.size() );
        for ( MovieEntity movieEntity : set ) {
            list.add( movieEntityToMovie( movieEntity ) );
        }

        return list;
    }

    protected GenreEntity genreToGenreEntity(Genre genre) {
        if ( genre == null ) {
            return null;
        }

        GenreEntity genreEntity = new GenreEntity();

        genreEntity.setId( genre.getId() );
        genreEntity.setName( genre.getName() );
        genreEntity.setImage( genre.getImage() );

        return genreEntity;
    }

    protected Set<GenreEntity> genreListToGenreEntitySet(List<Genre> list) {
        if ( list == null ) {
            return null;
        }

        Set<GenreEntity> set = new HashSet<GenreEntity>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( Genre genre : list ) {
            set.add( genreToGenreEntity( genre ) );
        }

        return set;
    }

    protected MovieEntity movieToMovieEntity(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setId( movie.getId() );
        movieEntity.setTitle( movie.getTitle() );
        movieEntity.setImage( movie.getImage() );
        movieEntity.setCreationDate( movie.getCreationDate() );
        movieEntity.setRating( movie.getRating() );
        movieEntity.setGenres( genreListToGenreEntitySet( movie.getGenres() ) );

        return movieEntity;
    }

    protected Set<MovieEntity> movieListToMovieEntitySet(List<Movie> list) {
        if ( list == null ) {
            return null;
        }

        Set<MovieEntity> set = new HashSet<MovieEntity>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( Movie movie : list ) {
            set.add( movieToMovieEntity( movie ) );
        }

        return set;
    }
}
