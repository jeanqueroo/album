package com.inditex.album.infrastructure.config;

import io.netty.channel.ChannelOption;
import io.netty.handler.timeout.ReadTimeoutHandler;
import io.netty.handler.timeout.WriteTimeoutHandler;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.netty.http.client.HttpClient;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Configuration
public class WebClientConfig {

    private final ApiProperties apiProperties;

    @Bean
    public WebClient webClient() {

        HttpClient httpClient = HttpClient.create()
                                          .option(ChannelOption.CONNECT_TIMEOUT_MILLIS, 500000)
                                          .responseTimeout(Duration.ofMillis(500000))
                                          .doOnConnected(conn ->
                                                  conn.addHandlerLast(new ReadTimeoutHandler(500000, TimeUnit.MILLISECONDS))
                                                      .addHandlerLast(new WriteTimeoutHandler(500000, TimeUnit.MILLISECONDS)));

        return WebClient.builder().baseUrl(apiProperties.getUrl())
                                    .clientConnector(new ReactorClientHttpConnector(httpClient))
                                    .build();

    }

}
