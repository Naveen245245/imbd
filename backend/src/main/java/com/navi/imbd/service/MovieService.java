package com.navi.imbd.service;

import com.navi.imbd.dto.Movie;
import com.navi.imbd.dto.Platform;
import com.navi.imbd.respository.MovieRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {
    private final MovieRepo movieRepo;

    public MovieService(MovieRepo movieRepo) {
        this.movieRepo = movieRepo;
    }

    public Movie save(Movie movie){
        return movieRepo.save(movie);
    }

    public Movie update(Movie movie){
        return movieRepo.save(movie);
    }
    public Optional<Movie> find(int movieId){
        return movieRepo.findById(movieId);
    }

    public Iterable<Movie> findAll(){
        return movieRepo.findAll();
    }

    public void delete(Movie movie){
        movieRepo.delete(movie);
    }

}
