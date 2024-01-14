package com.Inditex.album.infrastructure.adater.out.api;

import com.Inditex.album.domain.mapper.PhotoMapper;
import com.Inditex.album.domain.model.PhotoApiDTO;
import com.Inditex.album.domain.port.out.PhotoApiPort;
import com.Inditex.album.infrastructure.api.PhotoApiRest;
import com.Inditex.album.infrastructure.model.PhotoApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
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
