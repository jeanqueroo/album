package com.inditex.album.infrastructure.api;

import com.inditex.album.infrastructure.config.ApiProperties;
import com.inditex.album.infrastructure.model.AlbumApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service
public class AlbumApiRest {

    private final ApiProperties apiProperties;

    private final WebClient webClient;


    public Flux<AlbumApi> getAlbums() {
        return this.webClient.get().uri(apiProperties.getAlbumsEndpoint()).retrieve().bodyToFlux(AlbumApi.class);
    }

}
