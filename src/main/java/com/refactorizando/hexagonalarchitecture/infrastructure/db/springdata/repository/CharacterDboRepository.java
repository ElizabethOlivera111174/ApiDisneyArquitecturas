package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.repository;

import com.refactorizando.hexagonalarchitecture.domain.Character;
import com.refactorizando.hexagonalarchitecture.application.repository.CharacterRepository;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.CharacterEntity;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.MovieEntity;
import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.mapper.CharacterEntityMapper;
import com.refactorizando.hexagonalarchitecture.infrastructure.exeption.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CharacterDboRepository implements CharacterRepository {

    private final SpringDataCharacterRepository characterRepository;

    private final SpringDataMovieRepository movieRepository;

    private final CharacterEntityMapper characterEntityMapper;


    @Override
    public List<Character> getAll() {
        return characterEntityMapper.LtoDomain(characterRepository.findAll());
    }

    @Override
    public Character findById(Long characterId) {
        return characterEntityMapper.toDomain(characterRepository.findById(characterId).orElseThrow(() ->new ResourceNotFoundException("No Character with ID : " + characterId)));
    }

    @Override
    public List<Character> findByName(String name) {
        return characterEntityMapper.LtoDomain(characterRepository.findByName(name));
    }

    @Override
    public List<Character> findByAge(Integer age) {
        return characterEntityMapper.LtoDomain(characterRepository.findByAge(age));
    }

    @Override
    public void delete(Long id) {

        characterRepository.delete(characterEntityMapper.toDbo(findById(id)));
    }

    @Override
    public Character save(Character character) {
        return characterEntityMapper.toDomain(characterRepository.save(characterEntityMapper.toDbo(character)));
    }

    private boolean checkMoviesExistence(List<Long> moviesIds) {

        return movieRepository.findAll().stream().map(MovieEntity::getId).collect(Collectors.toList()).containsAll(moviesIds);

    }

    @Override
    public List<Character> findByMovieId(Long idMovie) {

        return characterEntityMapper.LtoDomain(characterRepository.findByMoviesId(idMovie));
    }

    @Override
    public void addMovies(Long CharacterId, List<Long> moviesIds) {
        CharacterEntity character = characterEntityMapper.toDbo(findById(CharacterId));

        if (checkMoviesExistence(moviesIds)) {

            movieRepository.findAllById(moviesIds).forEach(movie -> character.getMovies().add(movie));

        } else {

            throw new ResourceNotFoundException("Make sure all movies you want to add to the character already exist on the server");

        }

        characterRepository.save(character);

    }

    @Override
    public void removeMovies(Long CharacterId, List<Long> moviesIds) {
        CharacterEntity character = characterEntityMapper.toDbo(findById(CharacterId));

        character.getMovies().removeIf(movie -> moviesIds.contains(movie.getId()));

        characterRepository.save(character);

    }

}
