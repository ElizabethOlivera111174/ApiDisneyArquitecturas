package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.repository;

import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.MovieEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface SpringDataMovieRepository extends JpaRepository<MovieEntity, Long> {

    Optional<MovieEntity> findById(Long id);

    List<MovieEntity> findByTitle(String title);

    List<MovieEntity> findByGenresId(Long genreId);

    List<MovieEntity> findAllByOrderByCreationDateAsc();

    List<MovieEntity> findAllByOrderByCreationDateDesc();
}