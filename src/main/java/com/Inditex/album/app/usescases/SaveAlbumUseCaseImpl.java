package com.inditex.album.app.usescases;

import com.inditex.album.app.exception.NotFoundException;
import com.inditex.album.domain.mapper.AlbumMapper;
import com.inditex.album.domain.port.in.SaveAlbumUseCase;

import com.inditex.album.domain.port.out.AlbumApiPort;
import com.inditex.album.domain.port.out.AlbumRepositoryPort;

import com.inditex.album.domain.port.out.PhotoApiPort;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class SaveAlbumUseCaseImpl implements SaveAlbumUseCase {

    private final AlbumApiPort albumApiPort;
    private final PhotoApiPort photoApiPort;
    private final AlbumRepositoryPort albumRepositoryPort;
    private final AlbumMapper albumMapper;


    @Override
    @Transactional
    public Mono<Void> saveAlbum() {
        return Flux.from(albumApiPort.getAlbums()
                                     .switchIfEmpty(Mono.error(new NotFoundException("albums not found.")))
                                     .flatMap(albumApiDTO -> Flux.zip(Mono.just(albumApiDTO), photoApiPort.getPhotosByAlbumId(albumApiDTO.getId())
                                                                                                          .collectList())
                                                                 .flatMap(tuple -> {
                                                                     albumApiDTO.setPhotos(tuple.getT2());
                                                                     return Mono.just(albumApiDTO);
                                                                 })))
                   .flatMap(albumApiDTO ->
                           albumRepositoryPort.saveAlbum(albumMapper.albumApiToAlbumDto(albumApiDTO))
                   ).then();

    }
}
