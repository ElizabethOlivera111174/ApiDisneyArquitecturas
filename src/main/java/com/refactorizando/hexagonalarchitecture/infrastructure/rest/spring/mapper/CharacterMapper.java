package com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.mapper;

import com.refactorizando.hexagonalarchitecture.domain.Character;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.CharacterDTOs.CharacterDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.CharacterDTOs.CharacterSlimDto;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CharacterMapper {


    CharacterDto ToDto(Character character);

    Character ToCharter(CharacterDto character);

    List<CharacterSlimDto> ToCharactersSlimDto(List<Character> characters);

    List<CharacterDto> ToCharactersDtos(List<Character> characters);


}