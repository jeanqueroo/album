package com.Inditex.album.domain.port.in;


import reactor.core.publisher.Mono;

public interface SaveAlbumUseCase {

    Mono<Void> saveAlbum();

}
