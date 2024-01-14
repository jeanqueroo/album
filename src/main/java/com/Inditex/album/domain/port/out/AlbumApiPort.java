package com.Inditex.album.domain.port.out;

import com.Inditex.album.domain.model.AlbumApiDTO;
import reactor.core.publisher.Flux;

public interface AlbumApiPort {

    Flux<AlbumApiDTO> getAlbums();

}
