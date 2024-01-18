package com.inditex.album.infrastructure.adater.in.rest;

import com.inditex.album.app.services.AlbumCommand;
import com.inditex.album.domain.model.AlbumApiDTO;
import com.inditex.album.domain.model.AlbumDTO;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Log4j2
@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/album")
public class AlbumController {

    private final AlbumCommand albumCommand;


    @PostMapping
    public Mono<Void> saveAlbum() {
        log.info("Fill Database");
        return albumCommand.saveAlbum();
    }

    @GetMapping(path = "/api")
    public Flux<AlbumApiDTO> findAlbumsFromApi() {
        log.info("Find Albums from apiRest");
        return albumCommand.findAlbumsFromApi();
    }

    @GetMapping
    public Flux<AlbumDTO> findAlbums() {
        log.info("Find Albums in internal database");
        return albumCommand.findAlbums();
    }

}
