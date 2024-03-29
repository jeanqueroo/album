package com.inditex.album.infrastructure.adater.out.persistence;

import com.inditex.album.domain.mapper.AlbumMapper;
import com.inditex.album.domain.mapper.PhotoMapper;
import com.inditex.album.domain.model.AlbumDTO;
import com.inditex.album.domain.port.out.AlbumRepositoryPort;
import com.inditex.album.infrastructure.entities.AlbumEntity;
import com.inditex.album.infrastructure.repositoreis.SpringAlbumRepository;
import com.inditex.album.infrastructure.repositoreis.SpringPhotoRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@Component
@AllArgsConstructor
public class AlbumAdapter implements AlbumRepositoryPort {

    private final SpringAlbumRepository springAlbumRepository;

    private final SpringPhotoRepository springPhotoRepository;

    private final AlbumMapper albumMapper;

    private final PhotoMapper photoMapper;

    @Override
    public Mono<AlbumDTO> saveAlbum(final AlbumDTO albumDTO) {

        return springAlbumRepository.findById(albumDTO.getId())
                                    .switchIfEmpty(
                                            Mono.zip(springAlbumRepository.save(albumMapper.albumDTOToEntity(albumDTO)),
                                                    springPhotoRepository.saveAll(photoMapper.dtoToPhotoEntity(albumDTO.getPhotos())).collectList()).flatMap(tuble -> {
                                                AlbumEntity albumEntity = tuble.getT1();
                                                albumEntity.setPhotoEntities(tuble.getT2());
                                                return Mono.just(albumEntity);
                                            })
                                    ).map(albumMapper::albumEntityToDTO);
    }


    @Override
    public Flux<AlbumDTO> findAlbums() {

        return springAlbumRepository.findAll().flatMap(albumEntity ->
                Flux.combineLatest(
                        Mono.just(albumEntity),
                        springPhotoRepository.findByAlbumId(albumEntity.getId()).collectList(),
                        (album, photos) -> {
                            AlbumDTO albumDTO = albumMapper.albumEntityToDTO(album);
                            albumDTO.setPhotos(photoMapper.photoEntityToDto(photos));
                            return albumDTO;
                        }
                )
        );
    }


}
