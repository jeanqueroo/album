package com.inditex.album.infrastructure.config;

import com.inditex.album.app.services.AlbumCommand;
import com.inditex.album.domain.port.in.FindAlbumApiUseCase;
import com.inditex.album.domain.port.in.FindAlbumUseCase;
import com.inditex.album.domain.port.in.SaveAlbumUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.inditex.album")
public class AppConfig {

    @Bean
    public AlbumCommand albumCommand(SaveAlbumUseCase saveAlbumUseCase, FindAlbumApiUseCase findAlbumApiUseCase, FindAlbumUseCase findAlbumUseCase) {
        return new AlbumCommand(saveAlbumUseCase, findAlbumApiUseCase, findAlbumUseCase);
    }

}
