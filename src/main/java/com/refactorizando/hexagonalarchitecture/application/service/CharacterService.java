package com.refactorizando.hexagonalarchitecture.application.service;

import com.refactorizando.hexagonalarchitecture.domain.Character;
import com.refactorizando.hexagonalarchitecture.application.repository.CharacterRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
public class CharacterService {

    private final CharacterRepository characterRepository;

    public List<Character> getAll() {
        return characterRepository.getAll();
    }

    public Character findById(Long characterId) {
        return characterRepository.findById(characterId);
    }

    public List<Character> findByName(String name) {
        return characterRepository.findByName(name);
    }

    public List<Character> findByAge(Integer age) {
        return characterRepository.findByAge(age);
    }

    public void delete(Long id) {
        characterRepository.delete(id);
    }

    public Character save(Character character) {
        return characterRepository.save(character);
    }

    public List<Character> findByMovieId(Long idMovie) {
        return characterRepository.findByMovieId(idMovie);
    }

    public void addMovies(Long CharactersId, List<Long> moviesIds) {
        characterRepository.addMovies(CharactersId,moviesIds);
    }

    public void removeMovies(Long CharactersId, List<Long> moviesIds) {
        characterRepository.removeMovies(CharactersId,moviesIds);
    }
}

