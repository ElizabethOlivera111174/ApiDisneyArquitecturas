package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.repository;

import com.refactorizando.hexagonalarchitecture.domain.Genre;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.GenreEntity;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.MovieEntity;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.mapper.MovieEntityMapper;
import com.refactorizando.hexagonalarchitecture.application.repository.MovieRepository;
import com.refactorizando.hexagonalarchitecture.domain.Movie;
import com.refactorizando.hexagonalarchitecture.infrastructure.exeption.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class MovieDboRepository implements MovieRepository {

    private final SpringDataMovieRepository movieRepository;

    private final SpringDataGenreRepository genreRepository;

    private final MovieEntityMapper MovieEntityMapper;


    @Override
    public List<Movie> getAll() {

        return MovieEntityMapper.LtoDomain(movieRepository.findAll());

    }

    @Override
    public List<Movie> findAllOrderByCreationDate(String order) {

        if(order.equalsIgnoreCase("ASC")) {

            return MovieEntityMapper.LtoDomain(movieRepository.findAllByOrderByCreationDateAsc());

        } else if (order.equalsIgnoreCase("DESC")) {

            return MovieEntityMapper.LtoDomain(movieRepository.findAllByOrderByCreationDateDesc());

        }

        return null;

    }

    @Override
    public Movie findById(Long movieId) {

        return MovieEntityMapper.toDomain(movieRepository.findById(movieId).orElseThrow(() -> new ResourceNotFoundException("No Movie with ID : " + movieId)));

    }

    @Override
    public List<Movie> findByTitle(String title) {

        return MovieEntityMapper.LtoDomain(movieRepository.findByTitle(title));

    }

    @Override
    public void delete(Long id){

        movieRepository.delete(MovieEntityMapper.toDbo(findById(id)));

    }

    @Override
    public Movie save(Movie movie) {

        return MovieEntityMapper.toDomain(movieRepository.save(MovieEntityMapper.toDbo(movie)));

    }

    /*@Override
    public Optional<GenreEntity> findByGenreId(Long idGenre) {

        return genreRepository.findById(idGenre);

    }*/

    private boolean checkGenresExistence(List<Long> genresIds) {

        return genreRepository.findAll().stream().map(GenreEntity::getId).collect(Collectors.toList()).containsAll(genresIds);

    }

    @Override
    public Set<Genre> getGenres(Long id) {

        Set<Genre> toSet = new HashSet<>(findById(id).getGenres());
        return toSet;
    }

    @Override
    public void addGenres(Long movieId, List<Long> genresIds) {

        MovieEntity movie = MovieEntityMapper.toDbo(findById(movieId));

        if (checkGenresExistence(genresIds)) {

            genreRepository.findAllById(genresIds).forEach(genre -> movie.getGenres().add(genre));

        } else {

            throw new ResourceNotFoundException("Make sure all movies you want to add to the character already exist on the server");

        }

        movieRepository.save(movie);

    }

    @Override
    public void removeGenres(Long movieId, List<Long> genresIds) {

        MovieEntity movie = MovieEntityMapper.toDbo(findById(movieId));

        movie.getGenres().removeIf(genre -> genresIds.contains(genre.getId()));

        movieRepository.save(movie);

    }
}

