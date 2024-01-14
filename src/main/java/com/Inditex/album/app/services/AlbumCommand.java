package com.Inditex.album.app.services;

import com.Inditex.album.domain.model.AlbumApiDTO;
import com.Inditex.album.domain.model.AlbumDTO;
import com.Inditex.album.domain.port.in.FindAlbumApiUseCase;
import com.Inditex.album.domain.port.in.FindAlbumUseCase;
import com.Inditex.album.domain.port.in.SaveAlbumUseCase;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public record AlbumCommand(SaveAlbumUseCase saveAlbumUseCase,FindAlbumApiUseCase findAlbumApiUseCase, FindAlbumUseCase findAlbumUseCase) {

    public Mono<Void> saveAlbum(){
      return saveAlbumUseCase.saveAlbum();
    }
    public Flux<AlbumApiDTO> findAlbumsFromApi(){
        return findAlbumApiUseCase.findAlbums();
    }
    public Flux<AlbumDTO> findAlbums(){
        return findAlbumUseCase.findAlbums();
    }
}
