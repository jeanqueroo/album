package com.Inditex.album.infrastructure.config;

import com.Inditex.album.app.services.AlbumCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ContextConfiguration(classes = AppConfig.class)
class AppConfigTest {

    @Autowired
    private AlbumCommand albumCommand;

    @Test
    void albumCommand() {
        assertNotNull(albumCommand);
    }
}