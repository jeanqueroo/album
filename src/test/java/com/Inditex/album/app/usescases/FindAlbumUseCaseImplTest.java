package com.inditex.album.app.usescases;

import com.inditex.album.domain.port.out.AlbumRepositoryPort;
import com.inditex.album.mock.AlbumMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class FindAlbumUseCaseImplTest {

    @Mock
    private AlbumRepositoryPort albumRepositoryPort;

    @InjectMocks
    private FindAlbumUseCaseImpl findAlbumUseCase;

    @Test
    void testFindAlbums() throws IOException {

        when(albumRepositoryPort.findAlbums())
                .thenReturn(Flux.just(AlbumMock.getAlbumDTO(),AlbumMock.getAlbumDTOWithPhotos()));

        StepVerifier
                .create(findAlbumUseCase.findAlbums())
                .expectNext(AlbumMock.getAlbumDTO(),AlbumMock.getAlbumDTOWithPhotos())
                .verifyComplete();

        verify(albumRepositoryPort).findAlbums();

    }
}