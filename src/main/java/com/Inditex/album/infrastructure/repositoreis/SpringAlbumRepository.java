package com.Inditex.album.infrastructure.repositoreis;

import com.Inditex.album.infrastructure.entities.AlbumEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SpringAlbumRepository extends ReactiveCrudRepository<AlbumEntity, Integer> {

}
