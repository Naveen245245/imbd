package com.navi.imbd.controller;

import com.navi.imbd.dto.Review;
import com.navi.imbd.service.ReviewService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Tag(name="Reviews Service Api",description="Users can add reviews to specific movies.")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @Operation(summary = "Get all review list")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Got review List",content = {
                    @Content(mediaType = "application/json",schema = @Schema(implementation = Review.class))
            })}
    )
    @GetMapping("/reviews")
    public Mono<Iterable<Review>> getMovies(){
        return Mono.just(reviewService.findAll());
    }
    @Operation(summary = "Get review with id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "review Found",content = {
                    @Content(mediaType = "application/json",schema=@Schema(implementation = Review.class))
            }),
            @ApiResponse(responseCode = "400",description = "Invalid review Id",content =@Content),
            @ApiResponse(responseCode = "404",description = "Review Not Found",content =@Content)}
    )
    @GetMapping("/reviews/{id}")
    public Mono<Review> getMovie(@PathVariable Long id){
        return Mono.just(reviewService.find(id));
    }

    @Operation(summary = "Register/ create a reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Reviews created succefully",content = {
                    @Content(mediaType = "application/json",schema=@Schema(implementation = Review.class))
            }),@ApiResponse(responseCode = "400",description = "Invalid Request",content =@Content)}
    )
    @PostMapping("/review")
    public Mono<Review> createMovie(@RequestBody Review review){
        return Mono.just(reviewService.create(review));
    }

    @Operation(summary = "Update a reviews")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201",description = "Review Updated succefully",content = {
                    @Content(mediaType = "application/json",schema=@Schema(implementation = Review.class))
            }),
            @ApiResponse(responseCode = "400",description = "Invalid Request",content =@Content),
            @ApiResponse(responseCode = "404",description = "Review Not Found with the provided ID",content =@Content)}
    )
    @PutMapping("/reviews/{id}")
    public Mono<Review> updateReview(@PathVariable Long id,@RequestBody Review reviewForUpdate){
        return Mono.just(reviewService.update(reviewForUpdate));
    }

    @Operation(summary = "Delete a review")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200",description = "Review Deleted succefully",content = {
                    @Content(mediaType = "application/json",schema=@Schema(implementation = Review.class))
            }),
            @ApiResponse(responseCode = "404",description = "Review Not Found with the provided ID",content =@Content)}
    )
    @DeleteMapping("/reviews/{id}")
    public void deleteMovie(@PathVariable Long id){
        reviewService.delete(id);
    }

}
