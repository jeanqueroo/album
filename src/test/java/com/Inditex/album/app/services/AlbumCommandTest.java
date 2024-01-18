package com.inditex.album.app.services;

import com.inditex.album.domain.port.in.FindAlbumApiUseCase;
import com.inditex.album.domain.port.in.FindAlbumUseCase;
import com.inditex.album.domain.port.in.SaveAlbumUseCase;
import com.inditex.album.mock.AlbumMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AlbumCommandTest {

    @Mock
    private SaveAlbumUseCase saveAlbumUseCase;

    @Mock
    private FindAlbumApiUseCase findAlbumApiUseCase;

    @Mock
    private FindAlbumUseCase findAlbumUseCase;

    @InjectMocks
    private AlbumCommand albumCommand;

    @Test
    void testSaveAlbum() {
        when(saveAlbumUseCase.saveAlbum()).thenReturn(Mono.empty());
        StepVerifier
                .create(albumCommand.saveAlbum())
                .expectComplete()
                .verify();
        verify(saveAlbumUseCase).saveAlbum();
    }

    @Test
    void testFindAlbumsFromApi() throws IOException {
        when(findAlbumApiUseCase.findAlbums()).thenReturn(Flux.just(AlbumMock.getAlbumApiDTO()));
        StepVerifier
                .create(albumCommand.findAlbumsFromApi())
                .consumeNextWith(album -> {
                    assertEquals(AlbumMock.ID, album.getId() );
                    assertEquals(AlbumMock.TITLE, album.getTitle());
                    assertEquals(AlbumMock.USER_ID, album.getUserId());

                })
                .verifyComplete();
        verify(findAlbumApiUseCase).findAlbums();
    }

    @Test
    void testFindAlbums() throws IOException {
        when(findAlbumUseCase.findAlbums()).thenReturn(Flux.just(AlbumMock.getAlbumDTO()));
        StepVerifier
                .create(albumCommand.findAlbums())
                .consumeNextWith(album -> {
                    assertEquals(AlbumMock.ID, album.getId() );
                    assertEquals(AlbumMock.TITLE, album.getTitle());
                    assertEquals(AlbumMock.USER_ID, album.getUserId());

                })
                .verifyComplete();
        verify(findAlbumUseCase).findAlbums();
    }
}