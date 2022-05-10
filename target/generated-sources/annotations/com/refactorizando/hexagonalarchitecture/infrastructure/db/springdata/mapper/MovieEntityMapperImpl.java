package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.mapper;

import com.refactorizando.hexagonalarchitecture.domain.Genre;
import com.refactorizando.hexagonalarchitecture.domain.Movie;
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
public class MovieEntityMapperImpl implements MovieEntityMapper {

    @Override
    public List<Movie> LtoDomain(List<MovieEntity> MovieEntity) {
        if ( MovieEntity == null ) {
            return null;
        }

        List<Movie> list = new ArrayList<Movie>( MovieEntity.size() );
        for ( MovieEntity movieEntity : MovieEntity ) {
            list.add( toDomain( movieEntity ) );
        }

        return list;
    }

    @Override
    public Movie toDomain(MovieEntity MovieEntity) {
        if ( MovieEntity == null ) {
            return null;
        }

        Movie movie = new Movie();

        movie.setId( MovieEntity.getId() );
        movie.setTitle( MovieEntity.getTitle() );
        movie.setImage( MovieEntity.getImage() );
        movie.setCreationDate( MovieEntity.getCreationDate() );
        movie.setRating( MovieEntity.getRating() );
        movie.setGenres( genreEntitySetToGenreList( MovieEntity.getGenres() ) );

        return movie;
    }

    @Override
    public MovieEntity toDbo(Movie Movie) {
        if ( Movie == null ) {
            return null;
        }

        MovieEntity movieEntity = new MovieEntity();

        movieEntity.setId( Movie.getId() );
        movieEntity.setTitle( Movie.getTitle() );
        movieEntity.setImage( Movie.getImage() );
        movieEntity.setCreationDate( Movie.getCreationDate() );
        movieEntity.setRating( Movie.getRating() );
        movieEntity.setGenres( genreListToGenreEntitySet( Movie.getGenres() ) );

        return movieEntity;
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
}
