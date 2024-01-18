package com.inditex.album.app.usescases;

import com.inditex.album.app.exception.NotFoundException;
import com.inditex.album.domain.port.out.AlbumApiPort;
import com.inditex.album.domain.port.out.PhotoApiPort;
import com.inditex.album.mock.AlbumMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class FindAlbumApiUseCaseImplTest {

    @Mock
    private AlbumApiPort albumApiPort;
    @Mock
    private PhotoApiPort photoApiPort;

    @InjectMocks
    private FindAlbumApiUseCaseImpl findAlbumApiUseCase;


    @Test
    void testFindAlbumsNotFoundAlbum() {
        when(albumApiPort.getAlbums())
                .thenReturn(Flux.empty());

        StepVerifier
                .create(findAlbumApiUseCase.findAlbums())
                .expectErrorMatches(throwable -> throwable instanceof NotFoundException)
                .verify();

        verify(albumApiPort).getAlbums();
    }

    @Test
    void testFindAlbumsApiAlbumReturnOneValue() throws IOException {
        when(albumApiPort.getAlbums())
                .thenReturn(Flux.just(AlbumMock.getAlbumApiDTO2()));
        when(photoApiPort.getPhotosByAlbumId(2))
                .thenReturn(Flux.just(AlbumMock.getPhotoApiDTO(),AlbumMock.getPhotoApiDTO2()));


        StepVerifier
                .create(findAlbumApiUseCase.findAlbums())
                .expectNext(AlbumMock.getAlbumApiDTOWithPhotos())
                .verifyComplete();

        verify(albumApiPort).getAlbums();
        verify(photoApiPort).getPhotosByAlbumId(2);

    }

    @Test
    void testFindAlbumsApiAlbumReturnTowValue() throws IOException {
        when(albumApiPort.getAlbums())
                .thenReturn(Flux.just(AlbumMock.getAlbumApiDTO(),AlbumMock.getAlbumApiDTO2()));
        when(photoApiPort.getPhotosByAlbumId(2))
                .thenReturn(Flux.just(AlbumMock.getPhotoApiDTO(),AlbumMock.getPhotoApiDTO2()));
        when(photoApiPort.getPhotosByAlbumId(1))
                .thenReturn(Flux.empty());


        StepVerifier
                .create(findAlbumApiUseCase.findAlbums())
                .expectNext(AlbumMock.getAlbumApiDTO(),AlbumMock.getAlbumApiDTOWithPhotos())
                .verifyComplete();

        verify(albumApiPort).getAlbums();
        verify(photoApiPort).getPhotosByAlbumId(2);
        verify(photoApiPort).getPhotosByAlbumId(1);
    }
}