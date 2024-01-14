package com.Inditex.album.infrastructure.adater.out.api;

import com.Inditex.album.domain.mapper.AlbumMapper;
import com.Inditex.album.domain.model.AlbumApiDTO;
import com.Inditex.album.domain.port.out.AlbumApiPort;
import com.Inditex.album.infrastructure.api.AlbumApiRest;
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
