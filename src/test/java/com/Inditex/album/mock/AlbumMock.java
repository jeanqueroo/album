package com.inditex.album.mock;

import com.inditex.album.domain.model.AlbumApiDTO;
import com.inditex.album.domain.model.AlbumDTO;
import com.inditex.album.domain.model.PhotoApiDTO;
import com.inditex.album.infrastructure.entities.PhotoEntity;
import com.inditex.album.infrastructure.model.AlbumApi;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.io.InputStream;

public class AlbumMock {

    public final static Integer ID = 1;
    // Album title.
    public final static String TITLE = "quidem molestiae enim";
    public final static Integer USER_ID = 1;



    private static final ObjectMapper mapper = new ObjectMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
            .configure(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE, false)
            .registerModule(new JavaTimeModule());


    public static AlbumApiDTO getAlbumApiDTO() throws IOException {
        InputStream inputStream = AlbumMock.class.getClassLoader().getResourceAsStream("json/album.json");
        return mapper.readValue(inputStream, AlbumApiDTO.class);
    }


    public static AlbumApiDTO getAlbumApiDTOWithPhotos() throws IOException {
        InputStream inputStream = AlbumMock.class.getClassLoader().getResourceAsStream("json/albumWithPhotos.json");
        return mapper.readValue(inputStream, AlbumApiDTO.class);
    }

    public static AlbumDTO getAlbumDTO() throws IOException {
        InputStream inputStream = AlbumMock.class.getClassLoader().getResourceAsStream("json/album.json");
        return mapper.readValue(inputStream, AlbumDTO.class);
    }

    public static AlbumDTO getAlbumDTOWithPhotos() throws IOException {
        InputStream inputStream = AlbumMock.class.getClassLoader().getResourceAsStream("json/albumWithPhotos.json");
        return mapper.readValue(inputStream, AlbumDTO.class);
    }

    public static AlbumApiDTO getAlbumApiDTO2() throws IOException {
        InputStream inputStream = AlbumMock.class.getClassLoader().getResourceAsStream("json/album2.json");
        return mapper.readValue(inputStream, AlbumApiDTO.class);
    }

    public static AlbumApi getAlbumApi() throws IOException {
        InputStream inputStream = AlbumMock.class.getClassLoader().getResourceAsStream("json/album.json");
        return mapper.readValue(inputStream, AlbumApi.class);
    }

    public static AlbumApi getAlbumApi2() throws IOException {
        InputStream inputStream = AlbumMock.class.getClassLoader().getResourceAsStream("json/album2.json");
        return mapper.readValue(inputStream, AlbumApi.class);
    }



    public static PhotoApiDTO getPhotoApiDTO() throws IOException {
        InputStream inputStream = AlbumMock.class.getClassLoader().getResourceAsStream("json/photo.json");
        return mapper.readValue(inputStream, PhotoApiDTO.class);
    }

    public static PhotoApiDTO getPhotoApiDTO2() throws IOException {
        InputStream inputStream = AlbumMock.class.getClassLoader().getResourceAsStream("json/photo2.json");
        return mapper.readValue(inputStream, PhotoApiDTO.class);
    }



}
