package com.navi.imbd.controller;

import com.navi.imbd.dto.Platform;
import com.navi.imbd.service.PlatformService;

import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@Tag(name="Platform Service Api",description="Admins can add Platforms like (Amazon, hotstart,disney,Netflex etc..) to release the movies on their platform.")
public class PlatformController {

    private final PlatformService platformService;

    public PlatformController(PlatformService platformService) {
        this.platformService = platformService;
    }

    @GetMapping("/platforms")
    public Mono<Iterable<Platform>> getPlatforms() {
        return Mono.just(platformService.findAll());
    }

    @GetMapping("/platforms/{id}")
    public Mono<Platform> getPlatform(@PathVariable int id) {
        return Mono.just(platformService.find(id).get());
    }

    @PostMapping("/platforms")
    public Mono<Platform> createPlatform(@RequestBody Platform platform) {
        return Mono.just(platformService.save(platform));
    }

    @PutMapping("/platforms/{id}")
    public Mono<Platform> updatePlatform(@PathVariable int id,@RequestBody Platform platformForUpdate) {
        Platform platform = platformService.find(id).get();
        platform.setName(platformForUpdate.getName());
        platform.setLink(platformForUpdate.getLink());
        return Mono.just(platformService.save(platform));
    }

    @DeleteMapping("/platforms/{id}")
    public void getPlatforms(@PathVariable int id) {
        Platform platform = platformService.find(id).get();
        platformService.delete(platform);
    }

}
