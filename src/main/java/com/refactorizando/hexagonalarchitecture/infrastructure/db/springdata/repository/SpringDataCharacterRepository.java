package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.repository;

import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.CharacterEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataCharacterRepository extends JpaRepository<CharacterEntity, Long> {
    Optional<CharacterEntity> findById(Long id);

    List<CharacterEntity> findByName(String name);

    List<CharacterEntity> findByAge(Integer age);

    List<CharacterEntity> findByMoviesId(Long id);

}