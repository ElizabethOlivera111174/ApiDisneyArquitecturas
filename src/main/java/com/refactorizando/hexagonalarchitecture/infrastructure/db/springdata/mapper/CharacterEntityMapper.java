package com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.mapper;


import com.refactorizando.hexagonalarchitecture.infrastructure.db.springdata.dbo.CharacterEntity;
import com.refactorizando.hexagonalarchitecture.domain.Character;

import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterEntityMapper {

    List<Character> LtoDomain(List<CharacterEntity> charactersEntity);

    Character toDomain(CharacterEntity characterEntity);

    CharacterEntity toDbo(Character character);

}
