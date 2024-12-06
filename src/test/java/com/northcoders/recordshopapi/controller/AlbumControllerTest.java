package com.northcoders.recordshopapi.controller;

import com.northcoders.recordshopapi.config.AlbumControllerTestConfig;
import com.northcoders.recordshopapi.model.AlbumModel;
import com.northcoders.recordshopapi.model.Genre;
import com.northcoders.recordshopapi.service.AlbumService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.Mockito.when;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@Import(AlbumControllerTestConfig.class)
public class AlbumControllerTest {

    private MockMvc mockMvc;

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumController albumController;

    @Test
    public void getAllAlbums_ReturnListOfAlbums() throws Exception {
        // Initialize MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();

        // Arrange: Mock the service response
        List<AlbumModel> mockAlbums = List.of(
                new AlbumModel("Strange Days", "The Doors", Genre.ROCK, 10, 16.00),
                new AlbumModel("Kind of Blue", "Miles Davis", Genre.JAZZ, 5, 18.90) // Ensure title matches assertion
        );
        when(albumService.getAllAlbums()).thenReturn(mockAlbums);

        // Act & Assert: Call the endpoint and verify the response
        mockMvc.perform(get("/api/albums"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andExpect(jsonPath("$[0].title").value("Strange Days"))
                .andExpect(jsonPath("$[1].title").value("Kind of Blue")); // Ensure title matches mock data
    }

    @Test
    public void getAlbumById_ReturnAlbumWhenIdExists() throws Exception{
        // Initialize MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();

        // Arrange: Mock the service response
        AlbumModel mockAlbum = new AlbumModel("Strange Days", "The Doors", Genre.ROCK, 10, 15.99);
        mockAlbum.setId(1L);

        when(albumService.getAlbumById(1L)).thenReturn(Optional.of(mockAlbum));

        // Act & Assert: Call the endpoint and verify the response
        mockMvc.perform(get("/api/albums/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.title").value("Strange Days"))
                .andExpect(jsonPath("$.artist").value("The Doors"));
    }

    @Test void getAlbumById_Return404WhenDoesNotExist() throws Exception {
        // Initialize MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();

        // Arrange: Mock the service response
        when(albumService.getAlbumById(99L)).thenReturn(Optional.empty());

        // Act & Assert: Call the endpoint and verify the response
        mockMvc.perform(get("/api/albums/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void createAlbum_ReturnsCreatedAlbum() throws Exception {
        // Initialize MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();

        // Arrange: Mock the service response
        AlbumModel mockAlbum = new AlbumModel("The Wall", "Pink Floyd", Genre.ROCK, 5, 20.00);
        when(albumService.createAlbum(any(AlbumModel.class))).thenReturn(mockAlbum);

        // Act & Assert ; perform the post request
        mockMvc.perform(post("/api/albums")
                .contentType(MediaType.APPLICATION_JSON)
                .content(""" 
                        { "title": "The Wall", "artist": "Pink Floyd", "genre": "ROCK", "stock": 5, "price": 20.00}"""))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("The Wall"))
                .andExpect(jsonPath("$.artist").value("Pink Floyd"))
                .andExpect(jsonPath("$.genre").value("ROCK"))
                .andExpect(jsonPath("$.stock").value(5))
                .andExpect(jsonPath("$.price").value(20));
    }

    @Test
    public void createAlbum_Returns400WhenInvalidInput() throws Exception {
        // Initialize MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();

        // Act & Assert: Perform the POST request with invalid data
        mockMvc.perform(post("/api/albums")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                                  { "title": "", "artist": "", "genre": "ROCK", "stock": -1, "price": -5.00 }
                                  """))
                        .andExpect(status().isBadRequest());
    }

    @Test
    public void updateAlbum_ReturnUpdatedAlbum() throws Exception {
        // Initialize MockMvc
        mockMvc = MockMvcBuilders.standaloneSetup(albumController).build();

        // Arrange: Mock the service response
        AlbumModel mockUpdatedAlbum = new AlbumModel("Dark Side of the Moon", "Pink Floyd", Genre.ROCK, 8, 25.00);
        when(albumService.updateAlbum(eq(1L), any(AlbumModel.class))).thenReturn(mockUpdatedAlbum);// eq - creates a matcher that says true only when the object is exactly equal to that one

        // Act & Assert: Perform the PUT request
        mockMvc.perform(put("/api/albums/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content("""
                         { "title": "The Wall", "artist": "Pink Floyd", "genre": "ROCK", "stock": 8, "price": 25.00 }
                         } """))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.title").value("The Wall"))
                .andExpect(jsonPath("$.artist").value("Pink Floyd"))
                .andExpect(jsonPath("$.genre").value("ROCK"))
                .andExpect(jsonPath("$.stock").value(8))
                .andExpect(jsonPath("$.price").value(25));


     }
}