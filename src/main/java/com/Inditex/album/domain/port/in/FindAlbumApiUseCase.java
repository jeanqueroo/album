package com.Inditex.album.domain.port.in;

import com.Inditex.album.domain.model.AlbumApiDTO;
import reactor.core.publisher.Flux;

public interface FindAlbumApiUseCase {

    Flux<AlbumApiDTO> findAlbums();

}
