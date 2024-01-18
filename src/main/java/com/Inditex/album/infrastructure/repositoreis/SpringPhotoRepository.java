package com.inditex.album.infrastructure.repositoreis;

import com.inditex.album.infrastructure.entities.PhotoEntity;
import org.springframework.data.repository.query.Param;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SpringPhotoRepository extends ReactiveCrudRepository<PhotoEntity, Integer> {
    Flux<PhotoEntity> findByAlbumId(@Param("albumId") Integer albumId);
}
