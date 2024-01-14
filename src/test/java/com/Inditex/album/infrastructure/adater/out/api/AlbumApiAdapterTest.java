package com.Inditex.album.infrastructure.adater.out.api;

import com.Inditex.album.domain.mapper.AlbumMapper;
import com.Inditex.album.domain.mapper.AlbumMapperImpl;
import com.Inditex.album.domain.model.AlbumApiDTO;
import com.Inditex.album.infrastructure.api.AlbumApiRest;
import com.Inditex.album.mock.AlbumMock;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class AlbumApiAdapterTest {

    @Mock
    private AlbumApiRest albumApiRest;

    @Spy
    private AlbumMapper albumMapper = new AlbumMapperImpl();

    @InjectMocks
    private AlbumApiAdapter albumApiAdapter;

    @Test
    void testGetAlbumsOneAlbum() throws IOException {
        when(albumApiRest.getAlbums()).thenReturn(Flux.just(AlbumMock.getAlbumApi()));
        StepVerifier
                .create(albumApiAdapter.getAlbums()).consumeNextWith(album -> {
                    assertEquals(album.getId(), AlbumMock.ID );
                    assertEquals(album.getUserId(), AlbumMock.USER_ID);
                })
                .verifyComplete();
        verify(albumApiRest).getAlbums();
    }

    @Test
    void testGetAlbumsTowAlbum() throws IOException {
        when(albumApiRest.getAlbums()).thenReturn(Flux.just(AlbumMock.getAlbumApi(), AlbumMock.getAlbumApi2()));
        AlbumApiDTO albumnApiDTO = AlbumMock.getAlbumApiDTO();
        AlbumApiDTO albumnApiDTO2 = AlbumMock.getAlbumApiDTO2();
        albumnApiDTO.setPhotos(null);
        albumnApiDTO2.setPhotos(null);
        StepVerifier
                .create(albumApiAdapter.getAlbums())
                .expectNext(albumnApiDTO,albumnApiDTO2)
                .verifyComplete();
        verify(albumApiRest).getAlbums();
    }
}