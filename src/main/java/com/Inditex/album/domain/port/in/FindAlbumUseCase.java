package com.inditex.album.domain.port.in;

import com.inditex.album.domain.model.AlbumDTO;
import reactor.core.publisher.Flux;

public interface FindAlbumUseCase {

    Flux<AlbumDTO> findAlbums();


}
