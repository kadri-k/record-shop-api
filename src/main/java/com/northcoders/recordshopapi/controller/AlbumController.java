package com.northcoders.recordshopapi.controller;

import com.northcoders.recordshopapi.model.AlbumModel;
import com.northcoders.recordshopapi.service.AlbumService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api/albums")
public class AlbumController {

    private final AlbumService albumService;

    public AlbumController(AlbumService albumService) {
        this.albumService = albumService;
    }

    @GetMapping    // fetches and returns all albums as JSON list
    public ResponseEntity<List<AlbumModel>> getAllAlbums() {
        List<AlbumModel> albums = albumService.getAllAlbums();
        return ResponseEntity.ok(albums);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlbumModel> getAlbumById(@PathVariable Long id) {
        return albumService.getAlbumById(id)
                .map(album -> ResponseEntity.ok(album))
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @PostMapping
    public ResponseEntity<?> createAlbum(@RequestBody AlbumModel album) {
        if (album.getTitle() == null || album.getTitle().isBlank() ||
                album.getArtist() == null || album.getArtist().isBlank() ||
                album.getStock() < 0 || album.getPrice() < 0) {
            return ResponseEntity.badRequest().body("Incorrect album data");
        }

        AlbumModel createdAlbum = albumService.createAlbum(album);
        return new ResponseEntity<>(createdAlbum, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlbumModel> updateAlbum(@PathVariable Long id, @RequestBody AlbumModel updatedAlbum) {
    AlbumModel updated = albumService.updateAlbum(id, updatedAlbum);
    return ResponseEntity.ok(updated);
    }
}


