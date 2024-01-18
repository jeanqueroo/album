package com.inditex.album.app.services;

import com.inditex.album.domain.model.AlbumApiDTO;
import com.inditex.album.domain.model.AlbumDTO;
import com.inditex.album.domain.port.in.FindAlbumApiUseCase;
import com.inditex.album.domain.port.in.FindAlbumUseCase;
import com.inditex.album.domain.port.in.SaveAlbumUseCase;
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
