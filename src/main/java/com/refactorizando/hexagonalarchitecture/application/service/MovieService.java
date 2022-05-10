package com.refactorizando.hexagonalarchitecture.application.service;

import com.refactorizando.hexagonalarchitecture.application.repository.MovieRepository;
import com.refactorizando.hexagonalarchitecture.domain.Genre;
import com.refactorizando.hexagonalarchitecture.domain.Movie;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public class MovieService {

    private final MovieRepository movieRepository;
    //private final GenreRepository genreRepository;

    public List<Movie> getAll() {

        return movieRepository.getAll();

    }


    public List<Movie> findAllOrderByCreationDate(String order) {



        return  movieRepository.findAllOrderByCreationDate(order);

    }


    public Movie findById(Long movieId) {

        return movieRepository.findById(movieId);

    }

    public List<Movie> findByTitle(String title) {

        return movieRepository.findByTitle(title);

    }


    public void delete(Long id){

        movieRepository.delete(id);

    }

    public Movie save(Movie movie) {


        return movieRepository.save(movie);

    }

    /*public List<Movie> findByGenreId(Long idGenre) {

        return movieRepository.findByGenreId(idGenre);

    }*/


    public Set<Genre> getGenres(Long id) {

        return movieRepository.getGenres(id);

    }

    public void addGenres(Long movieId, List<Long> genresIds) {

        movieRepository.addGenres(movieId, genresIds);

    }

    public void removeGenres(Long movieId, List<Long> genresIds) {

        Movie movie = findById(movieId);

        movie.getGenres().removeIf(genre -> genresIds.contains(genre.getId()));

        movieRepository.save(movie);

    }

}

