package com.refactorizando.hexagonalarchitecture.application.repository;

import com.refactorizando.hexagonalarchitecture.domain.Genre;
import com.refactorizando.hexagonalarchitecture.domain.Movie;

import java.util.List;
import java.util.Set;

public interface MovieRepository {

    List<Movie> getAll();

    List<Movie> findAllOrderByCreationDate(String order);

    Movie findById(Long movieId);

    List<Movie> findByTitle(String title);

    void delete(Long id);

    Movie save(Movie movie);

    //List<Genre> findByGenreId(Long idGenre);

    Set<Genre> getGenres(Long id);

    void addGenres(Long movieId, List<Long> genresIds);

    void removeGenres(Long movieId, List<Long> genresIds);
}

