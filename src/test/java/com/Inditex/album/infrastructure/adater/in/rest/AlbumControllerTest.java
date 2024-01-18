package com.inditex.album.infrastructure.adater.in.rest;

import com.inditex.album.AlbumApplication;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;


@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(classes = AlbumApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@ActiveProfiles("integration")
class AlbumControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @Test
    @Order(3)
    void saveAlbum() {
        webTestClient.post().uri("/api/v1/album")
                     .accept(MediaType.APPLICATION_JSON)
                     .exchange()
                     .expectStatus().isOk();
    }

    @Test
    @Order(1)
    void findAlbumsFromApi() {
        webTestClient.get().uri("/api/v1/album/api")
                     .accept(MediaType.APPLICATION_JSON)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$.length()").isEqualTo(100)
                     .jsonPath("$[0].length()").isEqualTo(4);
    }

    @Test()
    @Order(2)
    void findAlbumsEmpty() {
        webTestClient.get().uri("/api/v1/album")
                     .accept(MediaType.APPLICATION_JSON)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$.length()").isEqualTo(0);
    }

    @Test
    @Order(4)
    void findAlbums() {
        webTestClient.get().uri("/api/v1/album")
                     .accept(MediaType.APPLICATION_JSON)
                     .exchange()
                     .expectStatus().isOk()
                     .expectBody()
                     .consumeWith(System.out::println)
                     .jsonPath("$[0].id").isEqualTo(1)
                     .jsonPath("$[0].userId").isEqualTo(1)
                     .jsonPath("$[0].title").isEqualTo("quidem molestiae enim");
    }
}