package com.Inditex.album.app.usescases;

import com.Inditex.album.app.exception.NotFoundException;
import com.Inditex.album.domain.mapper.AlbumMapper;
import com.Inditex.album.domain.mapper.AlbumMapperImpl;
import com.Inditex.album.domain.port.out.AlbumApiPort;
import com.Inditex.album.domain.port.out.AlbumRepositoryPort;
import com.Inditex.album.domain.port.out.PhotoApiPort;
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


import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class SaveAlbumUseCaseImplTest {

    @Mock
    private  AlbumApiPort albumApiPort;
    @Mock
    private  PhotoApiPort photoApiPort;
    @Mock
    private  AlbumRepositoryPort albumRepositoryPort;
    @Spy
    private  AlbumMapper albumMapper = new AlbumMapperImpl();

    @InjectMocks
    private SaveAlbumUseCaseImpl saveAlbumUseCase;

    @Test
    void testSaveAlbumApiPortNotFoundAlbum()  {

        when(albumApiPort.getAlbums())
                .thenReturn(Flux.empty());

        StepVerifier
                .create(saveAlbumUseCase.saveAlbum())
                .expectErrorMatches(throwable -> throwable instanceof NotFoundException)
                .verify();

        verify(albumApiPort).getAlbums();
    }



    @Test
    void testSaveAlbum() throws IOException {

        when(albumApiPort.getAlbums())
                .thenReturn(Flux.just(AlbumMock.getAlbumApiDTO(),AlbumMock.getAlbumApiDTO2()));
        when(photoApiPort.getPhotosByAlbumId(2))
                .thenReturn(Flux.just(AlbumMock.getPhotoApiDTO(),AlbumMock.getPhotoApiDTO2()));
        when(photoApiPort.getPhotosByAlbumId(1))
                .thenReturn(Flux.empty());
        when(albumRepositoryPort.saveAlbum(albumMapper.AlbumApiToAlbumDto(AlbumMock.getAlbumApiDTO()))).thenReturn(Mono.just(AlbumMock.getAlbumDTO()));
        when(albumRepositoryPort.saveAlbum(albumMapper.AlbumApiToAlbumDto(AlbumMock.getAlbumApiDTOWithPhotos()))).thenReturn(Mono.just(AlbumMock.getAlbumDTO()));

        StepVerifier
                .create(saveAlbumUseCase.saveAlbum())
                .verifyComplete();

        verify(albumApiPort).getAlbums();
        verify(photoApiPort).getPhotosByAlbumId(2);
        verify(photoApiPort).getPhotosByAlbumId(1);
        verify(albumRepositoryPort).saveAlbum(albumMapper.AlbumApiToAlbumDto(AlbumMock.getAlbumApiDTO()));
        verify(albumRepositoryPort).saveAlbum(albumMapper.AlbumApiToAlbumDto(AlbumMock.getAlbumApiDTOWithPhotos()));
    }
}