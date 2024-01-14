package com.Inditex.album.infrastructure.config;

import com.Inditex.album.app.services.AlbumCommand;
import com.Inditex.album.domain.port.in.FindAlbumApiUseCase;
import com.Inditex.album.domain.port.in.FindAlbumUseCase;
import com.Inditex.album.domain.port.in.SaveAlbumUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.Inditex.album")
public class AppConfig {

    @Bean
    public AlbumCommand albumCommand(SaveAlbumUseCase saveAlbumUseCase, FindAlbumApiUseCase findAlbumApiUseCase, FindAlbumUseCase findAlbumUseCase) {
        return new AlbumCommand(saveAlbumUseCase, findAlbumApiUseCase, findAlbumUseCase);
    }

}
