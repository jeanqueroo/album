package com.inditex.album.infrastructure.repositoreis;

import com.inditex.album.infrastructure.entities.AlbumEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SpringAlbumRepository extends ReactiveCrudRepository<AlbumEntity, Integer> {

}
