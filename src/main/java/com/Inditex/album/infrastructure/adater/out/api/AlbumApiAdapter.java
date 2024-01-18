package com.inditex.album.infrastructure.adater.out.api;

import com.inditex.album.domain.mapper.AlbumMapper;
import com.inditex.album.domain.model.AlbumApiDTO;
import com.inditex.album.domain.port.out.AlbumApiPort;
import com.inditex.album.infrastructure.api.AlbumApiRest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service
public class AlbumApiAdapter implements AlbumApiPort {
    private final AlbumApiRest albumApiRest;

    private final AlbumMapper albumMapper;
    @Override
    public Flux<AlbumApiDTO> getAlbums() {
        return this.albumApiRest.getAlbums().map(albumMapper::apiToApiDTO);
    }


}
