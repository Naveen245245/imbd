package com.navi.imbd.service;

import com.navi.imbd.dto.Movie;
import com.navi.imbd.dto.Review;
import com.navi.imbd.respository.MovieRepo;
import com.navi.imbd.respository.ReviewRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ReviewService {
    private final ReviewRepo reviewRepo;
    private final MovieRepo movieRepo;

    public ReviewService(ReviewRepo reviewRepo, MovieRepo movieRepo) {
        this.reviewRepo = reviewRepo;
        this.movieRepo = movieRepo;
    }

    public Review find(Long id) {
        return reviewRepo.findById(id).get();
    }

    public Iterable<Review> findAll() {
        return reviewRepo.findAll();
    }

    public Review create(Review review) {
        updateRating(review);
        return reviewRepo.save(review);
    }
    public Review update(Review reviewForUpdate) {
        Review review = reviewRepo.findById(reviewForUpdate.getId()).get();
        review.setDescription(reviewForUpdate.getDescription()).setRating(reviewForUpdate.getRating());
        updateRating(review);
        return reviewRepo.save(review);
    }

    private void updateRating(Review review) {
        Movie movie = movieRepo.findById(review.getMovie().getId()).get();
        if(movie.getReviews().size()>0){
            movie.setRating((movie.getRating()+review.getRating())/2);
        }else{
            movie.setRating(review.getRating());
        }
        movieRepo.save(movie);
    }

    public void delete(Long id) {
        reviewRepo.deleteById(id);
    }
}
