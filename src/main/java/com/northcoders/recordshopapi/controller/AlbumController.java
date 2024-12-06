package com.northcoders.recordshopapi.controller;

import com.northcoders.recordshopapi.model.AlbumModel;
import com.northcoders.recordshopapi.service.AlbumService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

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
}


