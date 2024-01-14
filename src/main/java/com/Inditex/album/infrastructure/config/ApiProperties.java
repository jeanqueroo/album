package com.Inditex.album.infrastructure.config;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@NoArgsConstructor
@Configuration
@ConfigurationProperties(prefix = "api")
@Data
public class ApiProperties {

    /**
     *  API URL
     */
    private String url;

    /**
     * Albums Endpoint
     */
    private String albumsEndpoint;

    /**
     * Photos Endpoint
     */
    private String PhotosEndpoint;

}
