package com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.mapper;

import com.refactorizando.hexagonalarchitecture.domain.Genre;
import com.refactorizando.hexagonalarchitecture.domain.Movie;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.GenreSlimDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.MovieDTOs.MovieDto;
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
public class MovieMapperImpl implements MovieMapper {

    @Override
    public MovieSlimDto movieToMovieSlimDto(Movie movie) {
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

    @Override
    public MovieDto movieToMovieDto(Movie movie) {
        if ( movie == null ) {
            return null;
        }

        MovieDto movieDto = new MovieDto();

        movieDto.setId( movie.getId() );
        movieDto.setTitle( movie.getTitle() );
        movieDto.setImage( movie.getImage() );
        movieDto.setCreationDate( movie.getCreationDate() );
        movieDto.setRating( movie.getRating() );
        movieDto.setGenres( genreListToGenreSlimDtoList( movie.getGenres() ) );

        return movieDto;
    }

    @Override
    public Movie movieDtoToMovie(MovieDto movie) {
        if ( movie == null ) {
            return null;
        }

        Movie movie1 = new Movie();

        movie1.setId( movie.getId() );
        movie1.setTitle( movie.getTitle() );
        movie1.setImage( movie.getImage() );
        movie1.setCreationDate( movie.getCreationDate() );
        movie1.setRating( movie.getRating() );
        movie1.setGenres( genreSlimDtoListToGenreList( movie.getGenres() ) );

        return movie1;
    }

    @Override
    public Movie updateMovieFromDto(MovieDto movieDto, Movie movie) {
        if ( movieDto == null ) {
            return null;
        }

        movie.setId( movieDto.getId() );
        movie.setTitle( movieDto.getTitle() );
        movie.setImage( movieDto.getImage() );
        movie.setCreationDate( movieDto.getCreationDate() );
        movie.setRating( movieDto.getRating() );
        if ( movie.getGenres() != null ) {
            List<Genre> list = genreSlimDtoListToGenreList( movieDto.getGenres() );
            if ( list != null ) {
                movie.getGenres().clear();
                movie.getGenres().addAll( list );
            }
            else {
                movie.setGenres( null );
            }
        }
        else {
            List<Genre> list = genreSlimDtoListToGenreList( movieDto.getGenres() );
            if ( list != null ) {
                movie.setGenres( list );
            }
        }

        return movie;
    }

    @Override
    public List<MovieSlimDto> moviesToMovieSlimDtos(List<Movie> movies) {
        if ( movies == null ) {
            return null;
        }

        List<MovieSlimDto> list = new ArrayList<MovieSlimDto>( movies.size() );
        for ( Movie movie : movies ) {
            list.add( movieToMovieSlimDto( movie ) );
        }

        return list;
    }

    @Override
    public List<MovieDto> moviesToMovieDtos(List<Movie> movies) {
        if ( movies == null ) {
            return null;
        }

        List<MovieDto> list = new ArrayList<MovieDto>( movies.size() );
        for ( Movie movie : movies ) {
            list.add( movieToMovieDto( movie ) );
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

    protected List<GenreSlimDto> genreListToGenreSlimDtoList(List<Genre> list) {
        if ( list == null ) {
            return null;
        }

        List<GenreSlimDto> list1 = new ArrayList<GenreSlimDto>( list.size() );
        for ( Genre genre : list ) {
            list1.add( genreToGenreSlimDto( genre ) );
        }

        return list1;
    }

    protected Genre genreSlimDtoToGenre(GenreSlimDto genreSlimDto) {
        if ( genreSlimDto == null ) {
            return null;
        }

        Genre genre = new Genre();

        genre.setId( genreSlimDto.getId() );
        genre.setName( genreSlimDto.getName() );
        genre.setImage( genreSlimDto.getImage() );

        return genre;
    }

    protected List<Genre> genreSlimDtoListToGenreList(List<GenreSlimDto> list) {
        if ( list == null ) {
            return null;
        }

        List<Genre> list1 = new ArrayList<Genre>( list.size() );
        for ( GenreSlimDto genreSlimDto : list ) {
            list1.add( genreSlimDtoToGenre( genreSlimDto ) );
        }

        return list1;
    }
}
