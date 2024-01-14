package com.Inditex.album.infrastructure.adater.out.persistence;

import com.Inditex.album.domain.mapper.AlbumMapper;
import com.Inditex.album.domain.mapper.AlbumMapperImpl;
import com.Inditex.album.domain.mapper.PhotoMapper;
import com.Inditex.album.domain.mapper.PhotoMapperImpl;
import com.Inditex.album.domain.model.AlbumDTO;
import com.Inditex.album.infrastructure.entities.AlbumEntity;
import com.Inditex.album.infrastructure.repositoreis.SpringAlbumRepository;
import com.Inditex.album.infrastructure.repositoreis.SpringPhotoRepository;
import com.Inditex.album.mock.AlbumMock;
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
import java.time.LocalDateTime;

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
        when(springAlbumRepository.save(albumMapper.AlbumDTOToEntity(albumDTO)))
                .thenReturn(Mono.just(AlbumEntity.builder().id(AlbumMock.ID).userId(AlbumMock.USER_ID)
                                                 .build()));
        when(springPhotoRepository.saveAll(photoMapper.dtoToPhotoEntity(albumDTO.getPhotos())))
                .thenReturn(Flux.just());

        StepVerifier
                .create(albumAdapter.saveAlbum(albumDTO)).consumeNextWith(album -> {
                    assertEquals(album.getId(),AlbumMock.ID );
                    assertEquals(album.getUserId(), AlbumMock.USER_ID);
                })
                .verifyComplete();

        verify(springAlbumRepository).findById(albumDTO.getId());
        verify(springAlbumRepository).save(albumMapper.AlbumDTOToEntity(albumDTO));
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
                    assertEquals(album.getId(),AlbumMock.ID );
                    assertEquals(album.getUserId(), AlbumMock.USER_ID);
                    assertEquals(album.getTitle(), AlbumMock.TITLE);
                    assertEquals(album.getPhotos().size(), 0);
                })
                .verifyComplete();
        verify(springAlbumRepository).findAll();
        verify(springPhotoRepository).findByAlbumId(AlbumMock.ID);
    }
}