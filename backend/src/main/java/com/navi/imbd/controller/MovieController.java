package com.navi.imbd.controller;

import com.navi.imbd.dto.Movie;
import com.navi.imbd.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Optional;

@RestController
@Tag(name="Movie Service Api",description="Movie Management API")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @Operation(summary = "Get all movies list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Got Movies List",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Movie.class))
            })}
    )
    @GetMapping("/movies")
    public Mono<Iterable<Movie>> getMovies(){
        return Mono.just(movieService.findAll());
    }
    @Operation(summary = "Get movie with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Movie Found",content = {
                    @Content(mediaType = "application/json",schema=@Schema(implementation = Movie.class))
            }),
            @ApiResponse(responseCode = "400",description = "Invalid Movie Id",content =@Content),
            @ApiResponse(responseCode = "404",description = "Movie Not Found",content =@Content)}
    )
    @GetMapping("/movies/{id}")
    public Mono<Optional<Movie>> getMovie(@PathVariable int id){
        return Mono.just(movieService.find(id));
    }

    @Operation(summary = "Register/ create a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Movie created succefully",content = {
                    @Content(mediaType = "application/json",schema=@Schema(implementation = Movie.class))
            }),@ApiResponse(responseCode = "400",description = "Invalid Request",content =@Content)}
    )
    @PostMapping("/movies")
    public Mono<Movie> createMovie(@RequestBody Movie movie){
        return Mono.just(movieService.save(movie));
    }

    @Operation(summary = "Update a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Movie Updated succefully",content = {
                    @Content(mediaType = "application/json",schema=@Schema(implementation = Movie.class))
            }),
            @ApiResponse(responseCode = "400",description = "Invalid Request",content =@Content),
            @ApiResponse(responseCode = "404",description = "Movie Not Found with the provided ID",content =@Content)}
    )
    @PutMapping("/movies/{id}")
    public Mono<Movie> updateMovie(@PathVariable int id,@RequestBody Movie movieForUpdate){
        Movie movie = movieService.find(id).get()
                .setPlatform(movieForUpdate.getPlatform())
                .setTitle(movieForUpdate.getTitle())
                .setActivate(movieForUpdate.isActivate())
                .setDescription(movieForUpdate.getDescription());
        return Mono.just(movieService.update(movie));
    }

    @Operation(summary = "Delete a movie")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Movie Deleted succefully",content = {
                    @Content(mediaType = "application/json",schema=@Schema(implementation = Movie.class))
            }),
            @ApiResponse(responseCode = "404",description = "Movie Not Found with the provided ID",content =@Content)}
    )
    @DeleteMapping("/movies/{id}")
    public void  deleteMovie(@PathVariable int id){
        Optional<Movie> movie = movieService.find(id);
        movieService.delete(movie.get());
    }

}
