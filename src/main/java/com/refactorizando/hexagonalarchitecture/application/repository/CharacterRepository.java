package com.refactorizando.hexagonalarchitecture.application.repository;

import com.refactorizando.hexagonalarchitecture.domain.Character;

import java.util.List;

public interface CharacterRepository {

    List<Character> getAll();

    Character findById(Long characterId);

    List<Character> findByName(String name);

    List<Character> findByAge(Integer age);

    void delete(Long id);

    Character save(Character character);

    List<Character> findByMovieId(Long idMovie);

    void addMovies(Long CharactersId, List<Long> moviesIds);

    void removeMovies(Long CharactersId, List<Long> moviesIds);
}
