package com.inditex.album.domain.port.in;

import com.inditex.album.domain.model.AlbumApiDTO;
import reactor.core.publisher.Flux;

public interface FindAlbumApiUseCase {

    Flux<AlbumApiDTO> findAlbums();

}
