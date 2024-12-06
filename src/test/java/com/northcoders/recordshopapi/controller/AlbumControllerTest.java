package com.northcoders.recordshopapi.controller;

import com.northcoders.recordshopapi.model.AlbumModel;
import com.northcoders.recordshopapi.model.Genre;
import com.northcoders.recordshopapi.service.AlbumService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Bean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;

public class AlbumControllerTest {

    private MockMvc mockMvc;
    @Mock
    private AlbumService albumService;

    @InjectMocks
    private AlbumController albumController;

    @Test
    public void getAllAlbums_ReturnListOfAlbums() throws Exception {
        // initialise mocks nad mockmvc
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();

        // arrange - mock the service response
        List<AlbumModel> mockAlbums = List.of(
                new AlbumModel("Strange Days", "The Doors", Genre.ROCK, 10, 16.00),
                new AlbumModel("Kind Of Blue", "Miles Davis", Genre.JAZZ, 5, 18.90));
        when(albumService.getAllAlbums()).thenReturn(mockAlbums);

        //act & assert, calling the endpoint and verifying the reply
        mockMvc.perform(get("/api/albums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Strange Days"))
                .andExpect(jsonPath("$[0].artist").value("The Doors"))
                .andExpect(jsonPath("$[1].title").value("Kind Of Blue"))
                .andExpect(jsonPath("$[1].artist").value("Miles Davis"));
    }

    @Test
    public void getAlbumById_ReturnAlbumWhenIdExists() throws Exception{
        // initialise mocks and mockmvc
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();

        //arrange - mock the service response
        AlbumModel mockAlbum = new AlbumModel("Strange Days", "The Doors", Genre.ROCK, 10, 15.99);
        mockAlbum.setId(1L);
        when(albumService.getAlbumById(1L)).thenReturn(Optional.of(mockAlbum));

        // act & assert, calling the endpoint and verify the response
        mockMvc.perform(get("/api/albums/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Strange Days"))
                .andExpect(jsonPath("$.artist").value("The Doors"));
    }

    @Test void getAlbumById_Return404WhenDoesNotExist() throws Exception {
        // initialise mocks and mockmvc
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();

        // Arrange - mock the service response
        when(albumService.getAlbumById(99L)).thenReturn(Optional.empty());

        // act & assert, calling the endpoint and verify the response
        mockMvc.perform(get("/api/albums/99"))
                .andExpect(status().isNotFound());
    }
}
