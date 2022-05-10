package com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.resources;

import com.refactorizando.hexagonalarchitecture.application.service.MovieService;
import com.refactorizando.hexagonalarchitecture.domain.Movie;
import com.refactorizando.hexagonalarchitecture.infrastructure.exeption.ErrorDetails;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.GenreSlimDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.ListOfLongDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.MovieDTOs.MovieDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.dto.MovieDTOs.MovieSlimDto;
import com.refactorizando.hexagonalarchitecture.infrastructure.rest.spring.mapper.GenreMapper;
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
@Tag(name = "Movies")
@RequestMapping("/movies")
//@SecurityRequirement(name = "bearerAuth")
@RestController
public class MovieController {

    private final MovieMapper movieMapper;
    private final GenreMapper genreMapper;
    private final MovieService movieService;

    @Operation(description = "Get all movies")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All movies are shown", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MovieSlimDto.class)) })
    })
    @GetMapping()
    public ResponseEntity<List<MovieSlimDto>> getAllMovies() {

        return new ResponseEntity<>(movieMapper.moviesToMovieSlimDtos(movieService.getAll()), HttpStatus.OK);

    }

    @Operation(description = "Find a movie by its ID and shows its details")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class)) }),
            @ApiResponse(responseCode = "404", description = "No movie have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> findMovieById(@PathVariable("id") Long movieId) {

        return new ResponseEntity<>(movieMapper.movieToMovieDto(movieService.findById(movieId)), HttpStatus.OK);

    }

    @GetMapping(params="title")
    public ResponseEntity<List<MovieDto>> findMovieByTitle(
            @Parameter(description = "Filter movies by title") @RequestParam(value = "title", required = false) String title) {

        return new ResponseEntity<>(movieMapper.moviesToMovieDtos(movieService.findByTitle(title)), HttpStatus.OK);

    }


    @Operation(description = "Delete a movie by its ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Movie deleted", content = @Content),
            @ApiResponse(responseCode = "404", description = "No movie with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteMovieById(@PathVariable("id") Long id) {

        movieService.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Operation(description = "Save a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Movie created", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PostMapping()
    public ResponseEntity<MovieDto> saveMovie(@Valid @RequestBody MovieDto movie) {

        Movie movieCreated = movieService.save(movieMapper.movieDtoToMovie(movie));

        return new ResponseEntity<>(movieMapper.movieToMovieDto(movieCreated), HttpStatus.CREATED);

    }

    @Operation(description = "Update a movie's info")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Movie updated", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = MovieDto.class)) }),
            @ApiResponse(responseCode = "404", description = "No movie with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PatchMapping("/{id}")
    public ResponseEntity<MovieDto> updateMovie(@Valid @RequestBody MovieDto movie, @PathVariable("id") Long id){
        movie.setId(id);
        Movie movieUpdated = movieService.save(movieMapper.movieDtoToMovie(movie));

        return new ResponseEntity<>(movieMapper.movieToMovieDto(movieUpdated), HttpStatus.OK);

    }

    @Operation(description = "Shows all the genres of the movie with the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All genres of the movie are shown", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = GenreSlimDto.class)) }),
            @ApiResponse(responseCode = "404", description = "No movie with the given ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) } )
    })
    @GetMapping("{id}/genres")
    public ResponseEntity<List<GenreSlimDto>> getMovieGenres(@PathVariable("id") Long movieId) {

        return new ResponseEntity<>(genreMapper.genresToGenreSlimDtos(new ArrayList<>(movieService.getGenres(movieId))), HttpStatus.OK);

    }

    @Operation(description = "Given a list of GenreID's, add all the corresponding genres to the movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Genres added successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "No movie with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @PutMapping("{id}/genres")
    public ResponseEntity<?> addGenresToMovie(@Valid @RequestBody ListOfLongDto genresIds, @PathVariable("id") Long movieId) {

        movieService.addGenres(movieId, genresIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @Operation(description = "Given a list of GenreID's, remove all the corresponding genres from the movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Genres removed successfully", content = @Content),
            @ApiResponse(responseCode = "404", description = "No movie with that ID have been found", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) }),
            @ApiResponse(responseCode = "400", description = "There have been validation errors", content = {
                    @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorDetails.class)) })
    })
    @DeleteMapping("{id}/genres")
    public ResponseEntity<?> removeGenresFromMovie(@Valid @RequestBody ListOfLongDto genresIds, @PathVariable("id") Long movieId) {

        movieService.removeGenres(movieId, genresIds.getList());

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

}


