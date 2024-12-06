package com.northcoders.recordshopapi.service;

import org.springframework.stereotype.Service;
import com.northcoders.recordshopapi.repository.AlbumRepository;
import com.northcoders.recordshopapi.model.AlbumModel;

import java.util.List;
import java.util.Optional;

@Service
public class AlbumServiceImpl implements AlbumService {

    private final AlbumRepository albumRepository;

    public AlbumServiceImpl(AlbumRepository albumRepository) {
        this.albumRepository = albumRepository;
    }

    @Override
    public List<AlbumModel> getAllAlbums() {
        return albumRepository.findAll();
    }

    @Override
    public Optional<AlbumModel> getAlbumById(Long id) {
        return albumRepository.findById(id);
    }

    @Override
    public AlbumModel createdAlbum(AlbumModel album) {
        return null;
    }

    @Override
    public AlbumModel updateAlbum(Long Id, AlbumModel album) {
        return null;
    }


//    @Override
//    // if existing album is found update the existing album fields with data
//    // from updated album using save() on albumRepository, return the updated album
//    // Else -> throw except.
//    public AlbumModel updateAlbum (Long id, AlbumModel updatedAlbum) {
//        Optional<AlbumModel> existingAlbum = albumRepository.findById(id);
//        if (existingAlbum.isPresent()) {
//            AlbumModel album = existingAlbum.get();
//            album.setTitle(updatedAlbum.getTitle());
//            album.setArtist(updatedAlbum.getArtist());
//            album.setGenre(updatedAlbum.getGenre());
//            album.setStock(updatedAlbum.getStock());
//            album.setPrice(updatedAlbum.getPrice());
//        return albumRepository.save(album);
//        } else {
//            throw new RuntimeException("Album with Id " + id +" not found. ");
//        }
//    }
    @Override
    public void deleteAlbum(Long id) {
        albumRepository.deleteById(id);
    }
}

