package com.inditex.album.domain.port.out;

import com.inditex.album.domain.model.AlbumDTO;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface AlbumRepositoryPort {

    Mono<AlbumDTO> saveAlbum(AlbumDTO albumDTO);

    Flux<AlbumDTO> findAlbums();

}
