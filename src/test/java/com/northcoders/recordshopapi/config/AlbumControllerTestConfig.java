package com.northcoders.recordshopapi.config;

import com.northcoders.recordshopapi.service.AlbumService;
import org.mockito.Mockito;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@TestConfiguration
public class AlbumControllerTestConfig {
    @Bean
    public AlbumService albumService() {
        // mock the AlbumService for testing
        return Mockito.mock(AlbumService.class);
    }
}

