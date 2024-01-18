package com.inditex.album.app.usescases;

import com.inditex.album.domain.model.AlbumDTO;
import com.inditex.album.domain.port.in.FindAlbumUseCase;
import com.inditex.album.domain.port.out.AlbumRepositoryPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service
public class FindAlbumUseCaseImpl implements FindAlbumUseCase {

    private final AlbumRepositoryPort albumRepositoryPort;

    @Override
    public Flux<AlbumDTO> findAlbums() {
        return albumRepositoryPort.findAlbums();
    }

}
