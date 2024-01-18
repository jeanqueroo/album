package com.inditex.album.infrastructure.api;

import com.inditex.album.infrastructure.config.ApiProperties;
import com.inditex.album.infrastructure.model.PhotoApi;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

@AllArgsConstructor
@Service
public class PhotoApiRest {

    private final ApiProperties apiProperties;
    private final WebClient webClient;


    public Flux<PhotoApi> getPhotosByAlbumId(Integer albumId) {
        return this.webClient.get().uri(uriBuilder -> uriBuilder
                .path(apiProperties.getPhotosEndpoint())
                .queryParam("albumId", albumId).build()).retrieve().bodyToFlux(PhotoApi.class);
    }
}
