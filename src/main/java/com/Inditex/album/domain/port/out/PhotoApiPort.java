package com.Inditex.album.domain.port.out;

import com.Inditex.album.domain.model.PhotoApiDTO;
import reactor.core.publisher.Flux;

public interface PhotoApiPort {

    Flux<PhotoApiDTO> getPhotosByAlbumId(Integer albumId);
}
