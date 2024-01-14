package com.Inditex.album.app.usescases;

import com.Inditex.album.app.exception.NotFoundException;
import com.Inditex.album.domain.model.AlbumApiDTO;
import com.Inditex.album.domain.port.in.FindAlbumApiUseCase;
import com.Inditex.album.domain.port.out.AlbumApiPort;
import com.Inditex.album.domain.port.out.PhotoApiPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class FindAlbumApiUseCaseImpl implements FindAlbumApiUseCase {

    private final AlbumApiPort albumApiPort;
    private final PhotoApiPort photoApiPort;

    @Override
    public Flux<AlbumApiDTO> findAlbums() {
        return albumApiPort.getAlbums().switchIfEmpty(Mono.error(new NotFoundException("albums not found.")))
                           .flatMap(album -> Flux
                .combineLatest(Mono.just(album), photoApiPort.getPhotosByAlbumId(album.getId()).collectList(), (albumApiDTO, photoApiDTOS) -> {
                    albumApiDTO.setPhotos(photoApiDTOS);
                    return albumApiDTO;
                }));
    }
}
