package com.Inditex.album.domain.port.in;

import com.Inditex.album.domain.model.AlbumDTO;
import reactor.core.publisher.Flux;

public interface FindAlbumUseCase {

    Flux<AlbumDTO> findAlbums();


}
