package com.northcoders.recordshopapi.service;


import com.northcoders.recordshopapi.model.AlbumModel;

import java.util.List;
import java.util.Optional;

public interface AlbumService {
    List<AlbumModel> getAllAlbums();
    Optional<AlbumModel> getAlbumsId(Long Id);
    AlbumModel createdAlbum(AlbumModel album);
    AlbumModel updateAlbum(Long Id, AlbumModel album);
    void deleteAlbum(Long id);
}
