package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.repository;

import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.GenreEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataGenreRepository extends JpaRepository<GenreEntity, Long> {
}
