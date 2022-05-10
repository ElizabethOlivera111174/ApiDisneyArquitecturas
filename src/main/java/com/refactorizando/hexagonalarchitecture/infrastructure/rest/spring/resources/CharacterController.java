package com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.resources;

import com.refactorizando.hexagonalarchitecture.application.service.CharacterService;
import com.refactorizando.hexagonalarchitecture.infrastructure.exeption.ErrorDetails;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.CharacterDTOs.CharacterDto;

import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.CharacterDTOs.CharacterSlimDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.ListOfLongDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.MovieDTOs.MovieSlimDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.mapper.CharacterMapper;
import com.refactorizando.hexagonalarchitecture.domain.Character;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.mapper.MovieMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Tag(name = "Character")
@RequestMapping("/character")
//@SecurityRequirement(name = "bearerAuth")
@RestController
public class CharacterController {

    private final CharacterService characterService;
    private final CharacterMapper characterMapper;
    private final MovieMapper movieMapper;



    @Operation(description = "Gets all characters")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All characters are shown", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterSlimDto.class)) })
    })
    @GetMapping()
    public ResponseEntity<List<CharacterSlimDto>> getAllCharacters() {

        return new ResponseEntity<>(characterMapper.ToCharactersSlimDto(characterService.getAll()), HttpStatus.OK);

    }

    @Operation(description = "Finds a character by his ID and shows his details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Character found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDto.class)) }),
            @ApiResponse(responseCode = "404", description = "No character have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> getCharacterById(@PathVariable("id") Long id) {

        return new ResponseEntity<>(characterMapper.ToDto(characterService.findById(id)), HttpStatus.OK);

    }

    @GetMapping(params = "name")
    public ResponseEntity<List<CharacterDto>> findCharacterByName(@Parameter(description = "Filter by name") @RequestParam(value = "name", required = false) String name) {

        return new ResponseEntity<>(characterMapper.ToCharactersDtos(characterService.findByName(name)), HttpStatus.OK);

    }

    @GetMapping(params="age")
    public ResponseEntity<List<CharacterDto>> findCharacterByAge(@Parameter(description = "Filter by age") @RequestParam(value = "age", required = false) Integer age) {

        return new ResponseEntity<>(characterMapper.ToCharactersDtos(characterService.findByAge(age)), HttpStatus.OK);

    }

    @GetMapping(params="movie")
    public ResponseEntity<List<CharacterDto>> findCharacterByMovieId(@Parameter(description = "Filter by MovieID") @RequestParam(value = "movie", required = false) Long movieId) {

        return new ResponseEntity<>(characterMapper.ToCharactersDtos(characterService.findByMovieId(movieId)), HttpStatus.OK);

    }

    @Operation(description = "Deletes a character by his ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Character deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "No character with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCharacterById(@PathVariable("id") Long id) {

        characterService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Operation(description = "Saves a character")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Character created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDto.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PostMapping()
    public ResponseEntity<CharacterDto> saveCharter(@Valid @RequestBody CharacterDto character) {

        Character charterCreated = characterService.save(characterMapper.ToCharter(character));

        return new ResponseEntity<>(characterMapper.ToDto(charterCreated), HttpStatus.CREATED);

    }

    @Operation(description = "Updates a character's info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Charter updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterDto.class)) }),
            @ApiResponse(responseCode = "404", description = "No character with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PatchMapping("/{id}")
    public ResponseEntity<CharacterDto> updateCharacter(@Valid @RequestBody CharacterDto character, @PathVariable("id") Long id) {
        character.setId(id);
        Character charactersUpdated = characterService.save(characterMapper.ToCharter(character));

        return new ResponseEntity<>(characterMapper.ToDto(charactersUpdated), HttpStatus.OK);

    }

    @Operation(description = "Shows all the movies of the character with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All movies of the character are shown", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = CharacterSlimDto.class)) }),
            @ApiResponse(responseCode = "404", description = "No character with the given ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) } )
    })

    @GetMapping("{id}/movies")
    public ResponseEntity<List<MovieSlimDto>> getCharacterMovies(@PathVariable("id") Long characterId) {

        return new ResponseEntity<>(movieMapper.moviesToMovieSlimDtos(new ArrayList<>(characterService.findById(characterId).getMovies())), HttpStatus.OK);

    }

    @Operation(description = "Given a list of MovieID's, adds all the corresponding movies to the character's movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movies added", content = @Content),
            @ApiResponse(responseCode = "404", description = "No character with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PutMapping("{id}/movies")
    public ResponseEntity<?> addMoviesToCharacter(@Valid @RequestBody ListOfLongDto moviesIds, @PathVariable("id") Long characterId) {

        characterService.addMovies(characterId, moviesIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Operation(description = "Given a list of Movie ID's, removes all the corresponding movies from the character's movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movies removed", content = @Content),
            @ApiResponse(responseCode = "404", description = "No character with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @DeleteMapping("{id}/movies")
    public ResponseEntity<?> removeMoviesFromCharacter(@Valid @RequestBody ListOfLongDto moviesIds, @PathVariable("id") Long characterId) {

        characterService.removeMovies(characterId, moviesIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

