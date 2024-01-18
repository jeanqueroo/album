package com.inditex.album.domain.port.out;

import com.inditex.album.domain.model.PhotoApiDTO;
import reactor.core.publisher.Flux;

public interface PhotoApiPort {

    Flux<PhotoApiDTO> getPhotosByAlbumId(Integer albumId);
}
