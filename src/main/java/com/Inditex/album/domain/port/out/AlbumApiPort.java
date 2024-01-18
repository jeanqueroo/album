package com.inditex.album.domain.port.out;

import com.inditex.album.domain.model.AlbumApiDTO;
import reactor.core.publisher.Flux;

public interface AlbumApiPort {

    Flux<AlbumApiDTO> getAlbums();

}
