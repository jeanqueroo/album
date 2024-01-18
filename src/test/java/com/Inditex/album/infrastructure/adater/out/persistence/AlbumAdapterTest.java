package com.inditex.album.infrastructure.adater.out.persistence;

import com.inditex.album.domain.mapper.AlbumMapper;
import com.inditex.album.domain.mapper.AlbumMapperImpl;
import com.inditex.album.domain.mapper.PhotoMapper;
import com.inditex.album.domain.mapper.PhotoMapperImpl;
import com.inditex.album.domain.model.AlbumDTO;
import com.inditex.album.infrastructure.entities.AlbumEntity;
import com.inditex.album.infrastructure.repositoreis.SpringAlbumRepository;
import com.inditex.album.infrastructure.repositoreis.SpringPhotoRepository;
import com.inditex.album.mock.AlbumMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AlbumAdapterTest {

    @Mock
    private SpringAlbumRepository springAlbumRepository;

    @Mock
    private SpringPhotoRepository springPhotoRepository;

    @Spy
    private AlbumMapper albumMapper = new AlbumMapperImpl();

    @Spy
    private PhotoMapper photoMapper = new PhotoMapperImpl();

    @InjectMocks
    private AlbumAdapter albumAdapter;

    @Test
    void testSaveAlbum() throws IOException {
        AlbumDTO albumDTO = AlbumMock.getAlbumDTOWithPhotos();
        when(springAlbumRepository.findById(albumDTO.getId()))
                .thenReturn(Mono.empty());
        when(springAlbumRepository.save(albumMapper.albumDTOToEntity(albumDTO)))
                .thenReturn(Mono.just(AlbumEntity.builder().id(AlbumMock.ID).userId(AlbumMock.USER_ID)
                                                 .build()));
        when(springPhotoRepository.saveAll(photoMapper.dtoToPhotoEntity(albumDTO.getPhotos())))
                .thenReturn(Flux.just());

        StepVerifier
                .create(albumAdapter.saveAlbum(albumDTO)).consumeNextWith(album -> {
                    assertEquals(AlbumMock.ID, album.getId() );
                    assertEquals(AlbumMock.USER_ID, album.getUserId());
                })
                .verifyComplete();

        verify(springAlbumRepository).findById(albumDTO.getId());
        verify(springAlbumRepository).save(albumMapper.albumDTOToEntity(albumDTO));
        verify(springPhotoRepository).saveAll(photoMapper.dtoToPhotoEntity(albumDTO.getPhotos()));
    }

    @Test
    void testFindAlbums()  {
        when(springAlbumRepository.findAll()).thenReturn(Flux.just(AlbumEntity.builder().id(AlbumMock.ID).userId(AlbumMock.USER_ID)
                                                                              .title(AlbumMock.TITLE)
                                                                              .build()));
        when(springPhotoRepository.findByAlbumId(AlbumMock.ID)).thenReturn(Flux.just());

        StepVerifier
                .create(albumAdapter.findAlbums()).consumeNextWith(album -> {
                    assertEquals(AlbumMock.ID, album.getId() );
                    assertEquals(AlbumMock.USER_ID, album.getUserId());
                    assertEquals(AlbumMock.TITLE, album.getTitle());
                    assertEquals(0, album.getPhotos()
                                         .size());
                })
                .verifyComplete();
        verify(springAlbumRepository).findAll();
        verify(springPhotoRepository).findByAlbumId(AlbumMock.ID);
    }
}