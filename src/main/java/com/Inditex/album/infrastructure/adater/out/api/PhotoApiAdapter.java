package com.inditex.album.infrastructure.adater.out.api;

import com.inditex.album.domain.mapper.PhotoMapper;
import com.inditex.album.domain.model.PhotoApiDTO;
import com.inditex.album.domain.port.out.PhotoApiPort;
import com.inditex.album.infrastructure.api.PhotoApiRest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service
public class PhotoApiAdapter implements PhotoApiPort {

    private final PhotoApiRest photoApiRest;

    private final PhotoMapper photoMapper;

    @Override
    public Flux<PhotoApiDTO> getPhotosByAlbumId(Integer albumId) {
        return this.photoApiRest.getPhotosByAlbumId(albumId).map(photoMapper::apiTophotoApiDTO);
    }
}
