package com.navi.imbd.service;

import com.navi.imbd.dto.Platform;
import com.navi.imbd.respository.MovieRepo;
import com.navi.imbd.respository.PlatformRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PlatformService {
    private final PlatformRepo platformRepo;

    public PlatformService(PlatformRepo platformRepo) {
        this.platformRepo = platformRepo;
    }

    public Platform save(Platform platform){
        return platformRepo.save(platform);
    }

    public Platform update(Platform platform){
        return platformRepo.save(platform);
    }
    public Optional<Platform> find(int movieId){
        return platformRepo.findById(movieId);
    }

    public Iterable<Platform> findAll(){
        return platformRepo.findAll();
    }

    public void delete(Platform platform){
        platformRepo.delete(platform);
    }
}
